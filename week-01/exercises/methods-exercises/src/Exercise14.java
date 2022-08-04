import java.util.Scanner;
public class Exercise14 {
    /* SHORT SURVEY

    Write a program that asks a user four questions and prints the results:
    - What is your first name?
    - What is your last name?
    - How many towns/cities have you lived in?
    - How many musical instruments can you play?

    Store each answer in a variable with an appropriate type.
    Print the results after the user has answered all four questions.

    Use methods to break the program into reusable blocks of code.

    public static String Name ()


     */
    public static String firstName (String prompt){
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);

        return console.nextLine();
    }

    public static String lastName (String prompt){
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);

        return console.nextLine();
    }

    public static int cities(){
        Scanner console = new Scanner(System.in);
        System.out.print("How many cities have you lived in?");
        int input = input = Integer.parseInt(console.nextLine());

        return input;
    }

    public static int instruments(){
        Scanner console = new Scanner(System.in);
        System.out.print("How many musical instruments can you play?");
        int input = input = Integer.parseInt(console.nextLine());

        return input;
    }


    public static void main(String[] args) {
        String firstName1 = null;
        firstName1 = firstName("What is your first name?: ");

        String lastName1 = null;
        lastName1 = lastName("What is your last name?");

        int cities1 = cities();

        int instrument1 = instruments();

        System.out.println("\n------------------------------------------------------\n");

        System.out.println("\nRESULTS:\n");

        System.out.println("Name: " + firstName1);
        System.out.println("Last Name: " + lastName1);
        System.out.println("Total towns/cities lived in: " + cities1);
        System.out.println("Instruments you can play: " + instrument1);



        //int cities = null;
        //cities = cities("How many towns/cities have you lived in?");
    }


}
