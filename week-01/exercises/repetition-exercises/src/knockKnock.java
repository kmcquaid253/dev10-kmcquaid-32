import java.util.Scanner;

public class knockKnock {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        String whosThere;
        do{
            System.out.println("Knock knock.");
            whosThere = console.nextLine();
        }while(!whosThere.equals("who's there?"));

        String yeahWho;

        do {
            System.out.println("Yeah.");
            yeahWho = console.nextLine();
        } while (!yeahWho.equals("yeah who?"));

        System.out.println("Sorry, I use Google.");









        String whoseThere;

        do {
            System.out.println("Knock knock.");
            whoseThere = console.nextLine();
        }while(!whoseThere.equals("whose there"));

        String mooWho;

        do {
            System.out.println("Moo");
            mooWho = console.nextLine();
        } while(!mooWho.equals("moo who?"));

        System.out.println("Mooyeah!");
    }

}
