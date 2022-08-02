public class Exercise07 {

    public static void main(String[] args) {
        // 1. Calculate the number of offices in a 23 story building
        // where each floor has 15 "rows" and 8 "columns" of offices.
        // 2. Use whatever approach you think is best.
        // 3. Print the result.

        int floors = 23;
        int rows = 15;
        int columns = 8;

        int officesInOneFloor = columns * rows;

        int totalOffices = officesInOneFloor * floors;
        System.out.println(totalOffices);
    }
}
