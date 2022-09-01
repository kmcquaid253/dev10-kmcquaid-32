package learn.dwmh.data;

import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.util.List;

public interface ReservationRepository {


    List<Reservation> findByHost(Host host);
    public Reservation add(Reservation toAdd);

    List<Reservation> findAll();

    boolean update(Reservation updated) throws DataException;
    boolean delete(Host host, int id);

}
