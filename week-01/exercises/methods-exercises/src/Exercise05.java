public class Exercise05 {

    // 1. Create a method.
    // Name: gallonsToCups
    // Inputs: int (number of gallons)
    // Output: int (number of cups)
    // Description: converts gallons to cups. (Common measure, not US legal.)

    public static int gallonsToCups(int numberOfGallons){

        /*
        //My code

            int cups;
            //cups = gallons * 16
            cups = numberOfGallons * 16;

            return cups;
         */

        //Davids code
        int numCups = Integer.MIN_VALUE;

        int cupsPerGallon = 16;

        numCups = numberOfGallons * cupsPerGallon;

        return numCups;


    }

    public static void main(String[] args) {
        // 2. Uncomment the following code to test your results.

        System.out.println(gallonsToCups(0)); // Expected: 0
        System.out.println(gallonsToCups(1)); // Expected: 16
        System.out.println(gallonsToCups(2)); // Expected: 32

    }


}
