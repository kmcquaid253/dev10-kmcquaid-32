package learn.dwmh.data;

import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.util.List;

public interface ReservationRepository {


    List<Reservation> findByHost(Host host);
    Reservation add(Reservation reservation);
    boolean update(Reservation reservation);
    boolean delete(Host host, int id);

}
