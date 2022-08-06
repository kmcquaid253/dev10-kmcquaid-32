import java.util.Scanner;
public class CapsuleHotel {
    public static void main(String[] args) {
        displayWelcome();

        Scanner console = new Scanner(System.in);

        int sizeOfArray;


        do {
            System.out.print("Enter the number of capsules available: ");
            sizeOfArray = Integer.parseInt(console.nextLine());


            //String[] rooms = new String[sizeOfArray];
        } while (sizeOfArray < 0);

        System.out.println("There are " + sizeOfArray + " unoccupied capsules ready to be booked.");

        String[] rooms = new String[sizeOfArray];

        String userMenuChoice = "";

        do {
            displayMenu();
            userMenuChoice = console.nextLine();

            if (userMenuChoice.equals("1")) {
                checkIn(console, rooms, sizeOfArray);
            } else if (userMenuChoice.equals("2")) {
                checkOut(console, rooms, sizeOfArray);
            } else if (userMenuChoice.equals("3")) {
                viewGuests(rooms);
            } else if (userMenuChoice.equals("4")) {
                //System.out.println("goodbye!");
                exit(console);
            } else {
                System.out.println("I don't understand that option.");
            }

        } while (!userMenuChoice.equals("4"));


    }

    public static void displayWelcome() {
        System.out.println("Welcome to Capsule-Capsule.");
        System.out.println("=============================");
    }

    public static void displayMenu() {
        System.out.println("\nGuest Menu");
        System.out.println("======================");
        System.out.println("1. Check In");
        System.out.println("2. Check Out");
        System.out.println("3. View Guests");
        System.out.println("4. Exit");
        System.out.print("Choose on option [1-4]: ");
    }

    public static void checkIn(Scanner console, String[] rooms, int sizeOfArray) {
        System.out.println("\nGuest Check In");
        System.out.println("===================");

        System.out.print("Guest Name: ");
        String name = console.nextLine();

        int roomNumber;

        do {
            System.out.print("Capsule #[1-" + sizeOfArray + "]: ");
            roomNumber = Integer.parseInt(console.nextLine()) - 1;

            if (roomNumber < 0 || roomNumber >= rooms.length) {
                return;
            }
            //TO DO:
            //check if array index is taken display error and re ask index value
        }while (rooms[roomNumber] != null) ;

            rooms[roomNumber] = name;

        System.out.println("Success :)\n" + name + " is booked in capsule #" + (roomNumber + 1));
    }

    public static void checkOut(Scanner console, String[] rooms, int sizeOfArray) {
        System.out.println("\nGuest Check Out");
        System.out.println("===================");

        int roomNumber;

        do {
            System.out.print("Capsule #[1-" + sizeOfArray  + "]: ");
            roomNumber = Integer.parseInt(console.nextLine()) - 1;

            if (roomNumber < 0 || roomNumber >= rooms.length) {
                return;
            }
            //TO DO:
            //check if array index is untaken
        }while (rooms[roomNumber] == null) ;

        rooms[roomNumber] = null;


        System.out.println("Success :)\nCapsule#" + (roomNumber + 1) +" has been checked out");
    }














    static void viewGuests(String[] rooms) {
        System.out.println("\nView Guests");
        System.out.println("=================");
        for (int i = 0; i < rooms.length; i++) {
            System.out.printf("Capsule #%s: %s%n",
                    i + 1, rooms[i] == null ? "[unoccupied]" : rooms[i]);
        }
    }








    

    public static void exit(Scanner console){
        System.out.println("\nExit");
        System.out.println("=========");

        System.out.println("Goodbye!");
        }
    }