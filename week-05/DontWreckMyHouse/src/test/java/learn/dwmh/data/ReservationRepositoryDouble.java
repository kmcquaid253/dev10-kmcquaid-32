package learn.dwmh.data;

import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepositoryDouble implements ReservationRepository{
    private ArrayList<Reservation> reservation = new ArrayList<>();

    final LocalDate startOne = LocalDate.of(2022, 9, 26);
    final LocalDate endOne = LocalDate.of(2022, 9, 28);

    public ReservationRepositoryDouble() {
        /*
            private int id;
            private LocalDate start;
            private LocalDate end;
            private BigDecimal total;
            private Guest guest;
            private Host host;
         */

        Reservation reservationOne = new Reservation();

        reservationOne.setId(1);
        reservationOne.setStart(startOne);
        reservationOne.setEnd(endOne);
        reservationOne.setTotal(new BigDecimal(120));
        reservationOne.setGuest(GuestRepositoryDouble.GUEST);
        reservationOne.setHost(HostRepositoryDouble.HOST);

        reservation.add(reservationOne);

    }
    @Override
    public List<Reservation> findByHost(Host host) {
        return reservation.stream()
                .filter(i -> i.getHost().equals(host))
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(Reservation updated) throws DataException {
        Reservation existingReservation = null;
        for (Reservation res : reservation) {
            if (res.getId() == updated.getId()) {
                existingReservation = res;
            }
        }
        return existingReservation != null;
    }

    @Override
    public Reservation add(Reservation toAdd) throws DataException {
        reservation.add(toAdd);
        return toAdd;
    }

    @Override
    public boolean delete(Host host, int id) throws DataException {
        List<Reservation> all = findByHost(host);
        for (int i = 0; i < all.size(); i++){
            if (all.get(i).getId() == id &&
                    all.get(i).getHost().equals(host)){
                all.remove(i);
                return true;
            }
        }
        return false;
    }
}

