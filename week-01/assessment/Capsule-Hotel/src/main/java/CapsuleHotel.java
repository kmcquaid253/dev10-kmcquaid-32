import java.util.Scanner;
public class CapsuleHotel {
    public static void main(String[] args) {
        displayWelcome();

        Scanner console = new Scanner(System.in);

        int sizeOfArray;
        //String userMenuChoice;


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
                    System.out.println("You selected check in!");
                } else if (userMenuChoice.equals("2")) {
                    System.out.println("You selected check out!");
                } else if (userMenuChoice.equals("3")) {
                    System.out.println("You selected view Guests!");
                } else if (userMenuChoice.equals("4")){
                    System.out.println("goodbye!");
                } else {
                    System.out.println("I don't understand that option.");
                }

            }while(!userMenuChoice.equals("4"));



    }

    public static void displayWelcome(){
        System.out.println("Welcome to Capsule-Capsule.");
        System.out.println("=============================");
    }

    public static void displayMenu(){
        System.out.println("\nGuest Menu");
        System.out.println("======================");
        System.out.println("1. Check In");
        System.out.println("2. Check Out");
        System.out.println("3. View Guests");
        System.out.println("4. Exit");
        System.out.print("Choose on option [1-4]");
    }
}
