public class Exercise05 {

    public static void main(String[] args) {
        // 1. Declare an integer variable monthCount.
        // 2. Use literal values and math operators to calculate the number of months in 83726 years.
        // Store the result in monthCount.
        // 3. Print the result.

        int monthCount;
        int years = 83726;
        int monthsInOneYear = 12;

        monthCount = years * monthsInOneYear;

        System.out.println(monthCount);
    }
}
