package learn.dwmh.domain;

import learn.dwmh.data.DataException;
import learn.dwmh.data.GuestRepository;
import learn.dwmh.data.HostRepository;
import learn.dwmh.data.ReservationRepository;
import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
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

        toReturn.setPayload(reservationRepository.findByHost(matchingHost.getId()));

        //New work starts here
        Map<Integer, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));

        Map<String, Host> hostMap = hostRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));


        List<Reservation> result = reservationRepository.findByHost(matchingHost.getId());
        for (Reservation reservation : result) {
            reservation.setGuest(guestMap.get(reservation.getGuest().getId()));//1. id gets passed into the map get
                                                                               //2. map goes from an id to the fully hydrated object
                                                                               //3. map.get gives us back the fully hydrated object
                                                                               //4. and then passing that into the setter
            reservation.setHost(hostMap.get(reservation.getHost().getId()));
        }

        return toReturn;
    }



    public Result<Reservation> add(Reservation reservation) throws DataException {
        Result<Reservation> result = validate(reservation);

        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(reservationRepository.add(reservation));

        return result;

    }

    public Result<Reservation> delete(int reservationId, String hostId) throws DataException {
        Result<Reservation> result = new Result<>();
        //Reservation reservation = new Reservation();

        // validate future dates
//        if(reservation.getStart().isBefore(LocalDate.now())){
//            result.addErrorMessage("Reservation date has to be in the future.");
//            return result;
//        }

        if (!reservationRepository.delete(reservationId, hostId)) {
            String message = String.format("Reservation %s-%s not found", hostId, reservationId);
            result.addErrorMessage(message);
        }
        return result;
    }

    public Result<Reservation> update(Reservation updated) throws DataException {
        Result<Reservation> result = validate( updated );

        if( !result.isSuccess() ){
            return result;
        }

//        if( result.isSuccess() ){
//            boolean success = reservationRepository.update( updated );
//            if( success ){
//                result.setPayload( updated );
//            } else {
//                result.addErrorMessage( "Could not find matching Reservation.");
//            }
//        }

        if (result.isSuccess()) {
            if (reservationRepository.update(updated)) {
                result.setPayload(updated);
            } else {
                result.addErrorMessage("Could not find matching reservation");
            }
        }

        return result;
    }

    public BigDecimal calculateTotal(Reservation reservation){

        List<LocalDate> days = new ArrayList<>();

        for(LocalDate date = reservation.getStart(); //iteration begins at start date
            date.isBefore(reservation.getEnd()); //checks if the end date is before the specific date ?isAfter
            date = date.plusDays(1)){ // +1 days to the reservation

            days.add(date);
        }

        BigDecimal totalVal = BigDecimal.ZERO; //Set initial totalVal to 0
        //for loop with a LocalDate variable that you advance one day every iteration
        //LocalDate objects can give you a day of week enum
        for(LocalDate day : days){
            if(day.getDayOfWeek() == DayOfWeek.FRIDAY ||
                    day.getDayOfWeek() == DayOfWeek.SATURDAY){
                totalVal = totalVal.add(reservation.getHost().getWeekendRate());
            }else{
                totalVal = totalVal.add(reservation.getHost().getStandardRate());
            }
        }
        totalVal = totalVal.setScale(2, RoundingMode.HALF_UP);
        return totalVal;
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

            //Validate dates don't overlap
            List<Reservation> findResult = reservationRepository.findByHost(reservation.getHost().getId());

                for(Reservation r : findResult){
                    if((!reservation.getStart().isBefore(r.getStart())
                    && reservation.getEnd().isBefore(r.getStart())) ||
                            (!reservation.getStart().isAfter(r.getEnd()) &&
                                    !reservation.getEnd().isAfter(r.getEnd()))){
                        result.addErrorMessage("Dates cannot overlap with existing dates!");
                    }
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
