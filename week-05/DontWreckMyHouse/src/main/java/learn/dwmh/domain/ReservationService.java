package learn.dwmh.domain;

import learn.dwmh.data.DataException;
import learn.dwmh.data.HostRepository;
import learn.dwmh.data.ReservationRepository;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
    }

    public List<Reservation> findByHost(Host email) throws DataException {

        Map<String, Host> foragerMap = hostRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getEmail(), i -> i));

        List<Reservation> result = reservationRepository.findByHost(email);
        for (Reservation reservation : result) {
            reservation.setHost(foragerMap.get(reservation.getHost().getEmail()));
        }

        return result;
    }


//    public Result<List<Reservation>> findByHost(Host host) {
//
//        Map<Object, Reservation> foragerMap = reservationRepository.findByHost(host).stream()
//                .collect(Collectors.toMap(i -> i.getHost().getId(), i -> i));
//
//        List<Host> result = hostRepository.findByEmail(String.valueOf(host));
//        for (Host hosts : result) {
//            hosts.set(foragerMap.get(reservationRepository.findByHost(hosts));
//            host.setEmail(itemMap.get(forage.getItem().getId()));
//        }
//
//        return result;
//    }

}
