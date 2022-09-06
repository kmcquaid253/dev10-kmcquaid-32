package learn.dwmh.domain;

import learn.dwmh.data.DataException;
import learn.dwmh.data.GuestRepositoryDouble;
import learn.dwmh.data.HostRepositoryDouble;
import learn.dwmh.data.ReservationRepositoryDouble;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceTest {
    final LocalDate start = LocalDate.of(2022, 12, 26);
    final LocalDate end = LocalDate.of(2022, 12, 28);
    final LocalDate startTwo = LocalDate.of(2020, 12, 26);
    final LocalDate endTwo = LocalDate.of(2020, 12, 28);

    ReservationService service = new ReservationService(
            new ReservationRepositoryDouble(),
            new HostRepositoryDouble(),
            new GuestRepositoryDouble());

    @Test
    void shouldAddNewReservation() throws DataException {

        Reservation reservation = new Reservation();

        /*
            private int id;
            private LocalDate start;
            private LocalDate end;
            private BigDecimal total;
            private Guest guest;
            private Host host;
         */

        reservation.setId(2);
        reservation.setStart(start);
        reservation.setEnd(end);
        reservation.setTotal(new BigDecimal(300));
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setHost(HostRepositoryDouble.HOST);


        Result<Reservation> result = service.add(reservation);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddReservationWithNullHost() throws DataException {

        Reservation reservation = new Reservation();

        /*
            private int id;
            private LocalDate start;
            private LocalDate end;
            private BigDecimal total;
            private Guest guest;
            private Host host;
         */

        reservation.setId(2);
        reservation.setStart(start);
        reservation.setEnd(end);
        reservation.setTotal(new BigDecimal(300));
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setHost(null);


        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddReservationWithNullGuest() throws DataException {

        Reservation reservation = new Reservation();

        /*
            private int id;
            private LocalDate start;
            private LocalDate end;
            private BigDecimal total;
            private Guest guest;
            private Host host;
         */

        reservation.setId(2);
        reservation.setStart(start);
        reservation.setEnd(end);
        reservation.setTotal(new BigDecimal(300));
        reservation.setGuest(null);
        reservation.setHost(HostRepositoryDouble.HOST);


        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddReservationWithPastDate() throws DataException {

        Reservation reservation = new Reservation();

        /*
            private int id;
            private LocalDate start;
            private LocalDate end;
            private BigDecimal total;
            private Guest guest;
            private Host host;
         */

        reservation.setId(2);
        reservation.setStart(startTwo);
        reservation.setEnd(endTwo);
        reservation.setTotal(new BigDecimal(300));
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setHost(HostRepositoryDouble.HOST);


        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddReservationWithStartDateBeforeEndDate() throws DataException {

        Reservation reservation = new Reservation();

        /*
            private int id;
            private LocalDate start;
            private LocalDate end;
            private BigDecimal total;
            private Guest guest;
            private Host host;
         */

        reservation.setId(2);
        reservation.setStart(end);
        reservation.setEnd(start);
        reservation.setTotal(new BigDecimal(300));
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setHost(HostRepositoryDouble.HOST);


        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldDelete() throws DataException{
        Result updateResult = service.deleteById(HostRepositoryDouble.HOST, GuestRepositoryDouble.GUEST.getId());
        assertTrue( updateResult.isSuccess() );
    }

}
