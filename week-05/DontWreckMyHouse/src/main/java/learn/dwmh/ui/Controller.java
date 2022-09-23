package learn.dwmh.ui;

import learn.dwmh.data.DataException;
import learn.dwmh.domain.GuestService;
import learn.dwmh.domain.HostService;
import learn.dwmh.domain.ReservationService;
import learn.dwmh.domain.Result;
import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class Controller {
    private final View view;
    private final HostService hostService;
    private final GuestService guestService;
    private final ReservationService reservationService;
    private final ConsoleIO io;

    @Autowired
    public Controller(View view, HostService hostService, ReservationService reservationService, GuestService guestService, ConsoleIO io) {
        this.view = view;
        this.hostService = hostService;
        this.reservationService = reservationService;
        this.guestService = guestService;
        this.io = io;
    }

    public void run() {
        view.displayHeader("Welcome to Don't Wreck My House");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    private void runAppLoop() throws DataException {
        MainMenuChoice option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_RESERVATION_FOR_HOST:
                    viewByHost();
                    break;
                case MAKE_A_RESERVATION:
                    addReservation();
                    break;
                case EDIT_A_RESERVATION:
                    updateReservation();
                    break;
                case CANCEL_A_RESERVATION:
                    cancelReservation();
                    break;
            }
        } while (option != MainMenuChoice.EXIT);
    }

    private void viewByHost() throws DataException {
        view.displayHeader(MainMenuChoice.VIEW_RESERVATION_FOR_HOST.getMessage());

        String email  = view.getHostEmail();

        Result<List<Reservation>> reservations = reservationService.findByHostEmail(email);

        view.displayReservations(reservations.getPayload());
        //kdeclerkdc@sitemeter.com
    }

    private void addReservation() throws DataException {
        view.displayHeader(MainMenuChoice.MAKE_A_RESERVATION.getMessage());

        Guest guest = getGuest();
        if (guest == null) {
            return;
        }

        Host host = getHost();
        if (host == null) {
            return;
        }


        Reservation reservation = view.makeReservation(host, guest);

        BigDecimal total = reservationService.calculateTotal(reservation);

        reservation.setTotal(total);

        boolean reservationCheck = view.reservationSummary(reservation);

        if(!reservationCheck){
            return;
        }

        Result<Reservation> result = reservationService.add(reservation);

        if (!result.isSuccess()) {
            view.displayStatus(false, result.getErrorMessages());
        } else {
            String successMessage = String.format("Reservation %s created.", result.getPayload().getId());
            view.displayStatus(true, successMessage);
        }
    }

    private void updateReservation() throws DataException {
        Guest guest = getGuest();
        if (guest == null) {
            return;
        }

        Host host = getHost();
        if (host == null) {
            return;
        }

        Result <List<Reservation>> findResult = reservationService.findByHostEmail(host.getEmail());
        if(!findResult.isSuccess()){
            view.displayStatus(false, findResult.getErrorMessages());
            return;
        }

        //The way we pull data out of an object we are calling a getter
        List<Reservation> reservations = findResult.getPayload();

        reservations = reservations.stream().filter( res -> res.getGuest().getId() == guest.getId()).collect(Collectors.toList());

        if(reservations.isEmpty()){
            view.displayStatus(false, "No reservations for selected host and guest");
            return;
        }

        Reservation selected = view.chooseReservationById(reservations);

        selected = view.editReservation(selected);

        BigDecimal total = reservationService.calculateTotal(selected);

        selected.setTotal(total);

        if(view.reservationSummary(selected)) {
            Result<Reservation> updateResult = reservationService.update(selected);

            if (!updateResult.isSuccess()) {
                view.displayStatus(false, updateResult.getErrorMessages());
            } else {
                String successMessage = String.format("Reservation %s created.", updateResult.getPayload().getId());
                view.displayStatus(true, successMessage);
            }
        }
    }

    private void cancelReservation() throws DataException {
        view.displayHeader(MainMenuChoice.CANCEL_A_RESERVATION.getMessage());

        Guest guest = getGuest();
        if (guest == null) {
            return;
        }

        Host host = getHost();
        if (host == null) {
            return;
        }

        Result <List<Reservation>> findResult = reservationService.findByHostEmail(host.getEmail());

        if(!findResult.isSuccess()){
            view.displayStatus(false, findResult.getErrorMessages());
            return;
        }

        //The way we pull data out of an object we are calling a getter
        List<Reservation> reservations = findResult.getPayload();

        reservations = reservations.stream().filter( res -> res.getGuest().getId() == guest.getId()).collect(Collectors.toList());

        if(reservations.isEmpty()){
            view.displayStatus(false, "No reservations for selected host and guest");
            return;
        }

        Reservation selected = view.chooseReservationById(reservations);

        Result result = reservationService.deleteById(host, selected.getId());

        if (result.isSuccess()) {

            String successMessage = String.format("Reservation deleted!");
            view.displayStatus(true, successMessage);
        } else {
            view.displayStatus(false, result.getErrorMessages());
        }
//
//        //customer email: dlynessy@icio.us
//        //host email: eyearnes0@sfgate.com
//        //reservation id:2
    }

   //support methods
    private Host getHost(){
        String email = view.getHostEmail();

        Result<List<Reservation>> reservations = reservationService.findByHostEmail(email);

        List<Host> host = hostService.findByEmail(email);

        view.displayReservations(reservations.getPayload());

        System.out.println();
        return view.chooseHost(host);
    }

    private Guest getGuest(){
        String email = view.getGuestEmail();
        List<Guest> guests = guestService.findByEmail(email);
        return view.chooseGuest(guests);
    }

}
