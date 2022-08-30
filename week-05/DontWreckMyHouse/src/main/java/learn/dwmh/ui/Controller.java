package learn.dwmh.ui;

import learn.dwmh.data.DataException;
import learn.dwmh.domain.HostService;
import learn.dwmh.models.Host;

import java.time.LocalDate;
import java.util.List;

public class Controller {

    private final HostService hostService;
    private final View view;

    public Controller(View view, HostService hostService) {
        this.hostService = hostService;
        this.view = view;
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

    private void viewReservationsForHost() {
        view.displayHeader(MainMenuChoice.VIEW_RESERVATION_FOR_HOST.getMessage());
        String host = view.getHostEmail();
        List<Host> hostEmail = hostService.findByEmail(host);

        view.displayHeader("Hosts Reservation");

//          fori = view.(foragersName);
//        view.displayForagers(fori);
    }
}
