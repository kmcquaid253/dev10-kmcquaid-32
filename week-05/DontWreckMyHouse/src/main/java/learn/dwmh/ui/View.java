package learn.dwmh.ui;

import learn.dwmh.domain.GuestService;
import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class View {
    private final ConsoleIO io;

    public View(ConsoleIO io) {
        this.io = io;
    }

    public MainMenuChoice selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MainMenuChoice option : MainMenuChoice.values()) {
            if (!option.isHidden()) {
                io.printf("%s. %s%n", option.getValue(), option.getMessage());
            }
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }

        String message = String.format("Select [%s-%s]: ", min, max);
        return MainMenuChoice.fromValue(io.readInt(message, min, max));
    }

    // display only
    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void enterToContinue() {
        io.readString("Press [Enter] to continue.");
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public void displayException(Exception ex) {
        displayHeader("A critical error occurred:");
        io.println(ex.getMessage());
    }

    public String getHostEmail() {
        return io.readRequiredString("Host Email: ");
    }

    public String getGuestEmail() {
        return io.readRequiredString("Guest Email: ");
    }

    public Host displayReservations(List<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No reservations found.");
            return null;
        }
        for (Reservation reservation : reservations) {

            /*
                    Reservation fields:

                    private int id;
                    private LocalDate start;
                    private LocalDate end;
                    private BigDecimal total;
                    private Guest guest;
                    private Host host;

                    fields in reservation file:
                    int id <- reservation
                    LocalDate start_date
                    LocalDate_endDate
                    int id <- Guest
                    BigDecimal total
             */
            io.printf("ID:%s , %s - %s, Guest: %s, %s,  Email: %s%n",
                    reservation.getId(),
                    reservation.getStart(),
                    reservation.getEnd(),
                    reservation.getGuest().getLastName(),
                    reservation.getGuest().getFirstName(),
                    reservation.getGuest().getEmail()
            );
        }
        return null;
    }
    public Host chooseHost(List<Host> hosts) {
        if (hosts.size() == 0) {
            io.println("No Host found");
            return null;
        }
        int index = 1;

        for (Host host : hosts) {
            io.printf("%s: %s %s%n", index++, host.getFirstName(), host.getLastName());
        }
        index--;

        io.println("0: Exit");
        String message = String.format("Select a host by their index [0-%s]: ", index);

        index = io.readInt(message, 0, index);

        if (index <= 0) {
            return null;
        }
        return hosts.get(index - 1);
    }

    public Guest chooseGuest(List<Guest> guests) {
        if (guests.size() == 0) {
            io.println("No Guest found");
            return null;
        }
        int index = 1;

        for (Guest guest : guests) {
            io.printf("%s: %s %s%n", index++, guest.getFirstName(), guest.getLastName());
        }
        index--;

        io.println("0: Exit");
        String message = String.format("Select a guest by their index [0-%s]: ", index);

        index = io.readInt(message, 0, index);

        if (index <= 0) {
            return null;
        }
        return guests.get(index - 1);
    }

    public Reservation makeReservation(Host host, Guest guest) {
        Reservation reservation = new Reservation();

        reservation.setHost(host);
        reservation.setGuest(guest);

        reservation.setStart(io.readLocalDate("Start [MM/dd/yyyy]: "));
        reservation.setEnd(io.readLocalDate("End [MM/dd/yyyy]: "));

        return reservation;
    }

    public Reservation getReservation() {
        Reservation toBuild = new Reservation();
        String email = io.readRequiredString("Host Email: ");

        Guest guest = getReservation().getGuest();
        Host host = getReservation().getHost();
        int id = io.readInt("Reservation ID: ");

        toBuild.setGuest(guest);
        toBuild.setHost(host);
        toBuild.setId(id);

        return toBuild;
    }


}
