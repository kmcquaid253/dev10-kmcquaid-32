package learn.dwmh.ui;

public enum MainMenuChoice {
    EXIT(0, "Exit", false),
    VIEW_RESERVATION_FOR_HOST(1, "View Reservations For Host", false),
    MAKE_A_RESERVATION(2, "Make a Reservation", false),
    EDIT_A_RESERVATION(3, "Edit a Reservation", false),
    CANCEL_A_RESERVATION(4, "Cancel a Reservation", false);

    private int value;
    private String message;
    private boolean hidden;

    private MainMenuChoice(int value, String message, boolean hidden) {
        this.value = value;
        this.message = message;
        this.hidden = hidden;
    }

    public static MainMenuChoice fromValue(int value) {
        for (MainMenuChoice option : MainMenuChoice.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return EXIT;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public boolean isHidden() {
        return hidden;
    }
}
