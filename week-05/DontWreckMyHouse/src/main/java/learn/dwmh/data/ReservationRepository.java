package learn.dwmh.data;

import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {


    List<Reservation> findByHost(Host host);

    boolean update(Reservation updated) throws DataException;

    Reservation add(Reservation toAdd) throws DataException;

    boolean delete(int id, Host host) throws DataException;
}
