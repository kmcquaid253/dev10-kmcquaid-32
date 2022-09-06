package learn.dwmh.ui;

import learn.dwmh.data.DataException;
import learn.dwmh.domain.HostService;
import learn.dwmh.domain.ReservationService;
import learn.dwmh.domain.Result;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class Controller {
    private final View view;
    private final HostService hostService;
    private final ReservationService reservationService;

    @Autowired
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
                    cancelReservation();
                    view.enterToContinue();
                    break;
            }
        } while (option != MainMenuChoice.EXIT);
    }

    private void cancelReservation() {
    }

    private void viewByHost() throws DataException {
        view.displayHeader(MainMenuChoice.VIEW_RESERVATION_FOR_HOST.getMessage());

        String email  = view.getHostEmail();

        Result<List<Reservation>> reservations = reservationService.findByHostEmail(email);
        view.displayReservations(reservations.getPayload());
        //kdeclerkdc@sitemeter.com
   }
   
   
}
