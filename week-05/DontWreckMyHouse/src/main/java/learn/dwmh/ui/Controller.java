package learn.dwmh.ui;

import learn.dwmh.data.DataException;
import learn.dwmh.domain.HostService;
import learn.dwmh.domain.ReservationService;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private final View view;
    private final HostService hostService;
    private final ReservationService reservationService;

    public Controller(View view, HostService hostService, ReservationService reservationService) {
        this.view = view;
        this.hostService = hostService;
        this.reservationService = reservationService;
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
                    viewReservationsForHost();
                    break;
                case MAKE_A_RESERVATION:
                    view.displayStatus(false, "NOT IMPLEMENTED: ADD");
                    view.enterToContinue();
                    break;
                case EDIT_A_RESERVATION:
                    view.displayStatus(false, "NOT IMPLEMENTED: EDIT");
                    view.enterToContinue();
                    break;
                case CANCEL_A_RESERVATION:
                    view.displayStatus(false, "NOT IMPLEMENTED: CANCEL");
                    view.enterToContinue();
                    break;
            }
        } while (option != MainMenuChoice.EXIT);
    }

    private void viewReservationsForHost() throws DataException {
//        view.displayHeader(MainMenuChoice.VIEW_RESERVATION_FOR_HOST.getMessage());
//        String hostEmail = view.getHostEmail();
//        //esadliero@boston.com
//        List<Reservation> reservations = reservationService.findByHost(hostEmail);
//        view.displayReservations(reservations);
//        view.enterToContinue();

    }
}
