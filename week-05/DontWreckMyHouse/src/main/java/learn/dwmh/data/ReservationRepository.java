package learn.dwmh.data;

import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {


    List<Reservation> findByHost(String hostId);

    boolean update(Reservation updated) throws DataException;

    Reservation add(Reservation toAdd) throws DataException;

    boolean delete(int id, String hostId) throws DataException;
}
