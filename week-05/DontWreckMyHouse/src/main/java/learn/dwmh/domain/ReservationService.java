package learn.dwmh.domain;

import learn.dwmh.data.DataException;
import learn.dwmh.data.GuestRepository;
import learn.dwmh.data.HostRepository;
import learn.dwmh.data.ReservationRepository;
import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;

    private final GuestRepository guestRepository;



    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }

    public Result<List<Reservation>> findByHostEmail(String email) {
        Result<List<Reservation>> toReturn = new Result<>();

        Host matchingHost = hostRepository.findByEmail(email);

        if(matchingHost == null){
            toReturn.addErrorMessage("Host not found");
            return toReturn;
        }

        toReturn.setPayload(reservationRepository.findByHost(matchingHost));

        //New work starts here
        Map<Object, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));


        List<Reservation> result = reservationRepository.findByHost(matchingHost);
        for (Reservation reservation : result) {
            reservation.setGuest(guestMap.get(reservation.getGuest().getId()));//1. id gets passed into the map get
                                                                               //2. map goes from an id to the fully hydrated object
                                                                               //3. map.get gives us back the fully hydrated object
                                                                               //4. and then passing that into the setter
        }

        return toReturn;
    }

    public Result<BigDecimal> calculateTotal(Reservation reservation){
        Result <BigDecimal> toReturn = new Result<>();

        //host & dates are required
        Host existingHost = reservation.getHost();
        LocalDate startDate = reservation.getStart();
        LocalDate endDate = reservation.getEnd();
        //LocalDate standardRate = existingHost.getStandardRate();
        //BigDecimal weekendRate = reservation.getHost().getWeekendRate();

        //start must be before end date
        boolean isBefore = startDate.isBefore(endDate);

        if(startDate == null || endDate == null){
            toReturn.addErrorMessage("Start Date and End Date are required");
            return toReturn;
        }

        if (!isBefore){
            toReturn.addErrorMessage("Start Date must be before end date");
            return toReturn;
        }

        //host must exist
        if (existingHost == null){
            toReturn.addErrorMessage("Host must exist");
            return toReturn;
        }

        //I recommend a for loop with a LocalDate variable that you advance one day every iteration
        //LocalDate objects can give you a day of week enum

        //for( LocalDate date : )

    }

    public Result<Reservation> add(Reservation reservation) throws DataException {
        Result<Reservation> result = validate(reservation);

        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(reservationRepository.add(reservation));

        return result;

        //cannot overlap with existing reservations for host

    }

    public Result<Reservation> deleteById(Host host, int guestId, Reservation reservation) throws DataException {
        Result<Reservation> result = new Result<>();

        //validate future dates
        if(reservation.getStart().isBefore(LocalDate.now())){
            result.addErrorMessage("Reservation date has to be in the future.");
            return result;
        }

        if (!reservationRepository.delete(host, guestId)) {
            String message = String.format("Reservation %s-%s not found", host, guestId);
            result.addErrorMessage(message);
            return result;
        }
        return result;
    }

    private Result<Reservation> validate(Reservation reservation) {

        Result<Reservation> result = validateNulls(reservation);


        if (!result.isSuccess()) {
            return result;
        }

        validateFields(reservation, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateChildrenExist(reservation, result);

        return result;
    }

    private Result<Reservation> validateNulls(Reservation reservation) {
        Result<Reservation> result = new Result<>();

        //guest, host & dates are required

        if (reservation == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        if (reservation.getStart() == null) {
            result.addErrorMessage("Start date is required.");
            return result;
        }

        if (reservation.getEnd() == null) {
            result.addErrorMessage("End date is required.");
            return result;
        }

        if (reservation.getHost() == null) {
            result.addErrorMessage("Host is required.");
            return result;
        }

        if (reservation.getGuest() == null) {
            result.addErrorMessage("Guest is required.");
            return result;
        }
        return result;
    }

    private void validateFields(Reservation reservation, Result<Reservation> result) {
            //validate future dates
            if (reservation.getStart().isBefore(LocalDate.now())) {
                result.addErrorMessage("Reservation date has to be in the future.");
            }

            //start must be before end date
            if(reservation.getStart().isAfter(reservation.getEnd())){
                result.addErrorMessage("Start date must be before end date.");
            }
    }

    private void validateChildrenExist(Reservation reservation, Result<Reservation> result) {

        if (reservation.getHost().getId().equals(null)) {
            result.addErrorMessage("Host does not exist.");
        }

        if (reservation.getGuest().getId() == 0) {
            result.addErrorMessage("Guest does not exist.");
        }
    }




}
