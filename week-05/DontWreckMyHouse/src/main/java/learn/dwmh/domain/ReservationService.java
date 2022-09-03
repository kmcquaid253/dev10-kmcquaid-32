package learn.dwmh.domain;

import learn.dwmh.data.DataException;
import learn.dwmh.data.GuestRepository;
import learn.dwmh.data.HostRepository;
import learn.dwmh.data.ReservationRepository;
import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.math.BigDecimal;
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
        Map<Integer, Guest> guestMap = guestRepository.findAll().stream()
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

    Result<BigDecimal> calculateTotal(Reservation reservation){
        //host & dates are required
        //start must be before end date
        //host must exist

        Result <BigDecimal> toReturn = new Result<>();

        BigDecimal pricePer = forage.getItem().getDollarPerKilogram();
        Double totalKilo = forage.getKilograms();
        BigDecimal convertedNum = BigDecimal.valueOf(totalKilo);
        BigDecimal totalPrice = pricePer.multiply(convertedNum);

        return totalPrice;
    }

}
