package learn.dwmh.ui;

import learn.dwmh.data.DataException;
import learn.dwmh.domain.HostService;
import learn.dwmh.domain.ReservationService;
import learn.dwmh.domain.Result;
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
                    viewByHost();
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

    private void viewByHost() throws DataException {
        view.displayHeader(MainMenuChoice.VIEW_RESERVATION_FOR_HOST.getMessage());

//        Host email  = view.getHostEmail();
//        Result<List<Reservation>> reservations = reservationService.findByHost(email);
//        //esadliero@boston.com

//        Host email = view.getByHost();
//        Result<List<Reservation>> reservations = reservationService.findByHost(email);


    }
}
