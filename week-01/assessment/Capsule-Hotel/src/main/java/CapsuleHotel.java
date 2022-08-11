import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
public class CapsuleHotel {
    public static void main(String[] args) {
        //Welcome Message
        displayWelcome();

        Scanner console = new Scanner(System.in);

        int sizeOfArray;

        //Loops until user enters a positive value for the # of capsules available
        do {

            System.out.print("Enter the number of capsules available: ");
            sizeOfArray = Integer.parseInt(console.nextLine());

        } while (sizeOfArray <= 0);

        System.out.println("There are " + sizeOfArray + " unoccupied capsules ready to be booked.");

        //Rooms array
        String[] rooms = new String[sizeOfArray];

        String userMenuChoice = "";

        //Loops through menu until user inputs 4 (exit)
        do {
            displayMenu();
            userMenuChoice = console.nextLine();

            if (userMenuChoice.equals("1")) {               // "1" = Check In
                checkIn(console, rooms, sizeOfArray);
            } else if (userMenuChoice.equals("2")) {        // "2" = Check Out
                checkOut(console, rooms, sizeOfArray);
            } else if (userMenuChoice.equals("3")) {        // "3" = View Guests
                viewGuests(rooms, console, sizeOfArray);
            } else if (userMenuChoice.equals("4")) {        // "4" = Exit
                exit();
            } else {
                System.out.println("I don't understand that option.");
            }
        } while (!userMenuChoice.equals("4"));

    }


    //Welcome message
    public static void displayWelcome() {
        System.out.println("Welcome to Capsule-Capsule.");
        System.out.println("=============================");
    }

    //Menu
    public static void displayMenu() {
        System.out.println("\nGuest Menu");
        System.out.println("======================");
        System.out.println("1. Check In");
        System.out.println("2. Check Out");
        System.out.println("3. View Guests");
        System.out.println("4. Exit");
        System.out.print("Choose on option [1-4]: ");
    }

    //Check In
    public static void checkIn(Scanner console, String[] rooms, int sizeOfArray) {
        System.out.println("\nGuest Check In");
        System.out.println("===================");

        System.out.print("Guest Name: ");
        String name = console.nextLine();

        int roomNumber;

        //Loops through room number and exits once room number equals null
        do {
            System.out.print("Capsule #[1-" + sizeOfArray + "]: ");
            roomNumber = Integer.parseInt(console.nextLine()) - 1;

            if (roomNumber < 0 || roomNumber >= rooms.length) {
                return;
            }

        } while (rooms[roomNumber] != null);

        //Apply name to the room number (index) selected
        rooms[roomNumber] = name;

        System.out.println("Success :)\n" + name + " is booked in capsule #" + (roomNumber + 1));
    }

    //Check Out
    public static void checkOut(Scanner console, String[] rooms, int sizeOfArray) {
        System.out.println("\nGuest Check Out");
        System.out.println("===================");

        int roomNumber;

        //Loops until room number is not equal to null
        do {
            System.out.print("Capsule #[1-" + sizeOfArray + "]: ");
            roomNumber = Integer.parseInt(console.nextLine()) - 1;

            if (roomNumber < 0 || roomNumber >= rooms.length) {
                return;
            }

        } while (rooms[roomNumber] == null);

        //Sets room number (index) selected to null
        rooms[roomNumber] = null;

        System.out.println("Success :)\nCapsule #" + (roomNumber + 1) + " has been checked out");
    }

    //View Guests
    public static void viewGuests(String[] rooms, Scanner console, int sizeOfArray) {

        int roomNumber;
        System.out.println("\nView Guests");
        System.out.println("=================");

        System.out.print("Capsule #[1-" + sizeOfArray + "]: ");
        roomNumber = Integer.parseInt(console.nextLine()) - 1;


        //Minimum Variable = room number(index) - 5,
        int minVariable = roomNumber - 5;

        //Maximum Variable = room number(index) + 6,
        int maxVariable = roomNumber + 6;

        //Checks to see if the minVariable is too low
        if (minVariable < 0) {
            //if so, cap it at 0
            minVariable = 0;
        }

        //Checks to see if the maxVariable is too high
        if (maxVariable > rooms.length) {
            //if so, cap it at the length of the array
            maxVariable = rooms.length;
        }

        //Loop that goes from min to max and print out the room info for whatever rooms that loop hits
        for (int i = minVariable; i < maxVariable; i++) {

            System.out.printf("Capsule #%s: %s%n", i + 1, rooms[i] == null ? "[unoccupied]" : rooms[i]);

        }
    }

    //Exit
    public static void exit() {

        System.out.println("\nExit");
        System.out.println("=========\n");
        System.out.println("Goodbye! :)");

    }
}
