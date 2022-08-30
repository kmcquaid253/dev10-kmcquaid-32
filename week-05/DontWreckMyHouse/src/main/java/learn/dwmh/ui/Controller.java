package learn.dwmh.ui;

import learn.dwmh.data.DataException;

import java.time.LocalDate;
import java.util.List;

public class Controller {

    private final View view;

    public Controller(View view) {
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
                    view.displayStatus(false, "NOT IMPLEMENTED: VIEW");
                    view.enterToContinue();
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
}
