public class Exercise16 {

    public static void main(String[] args) {
        // BORDER BOX
        // 1. Use nested loops to print a box in the console with a different character for the border.
        // One loop should represent rows and the other should represent columns.
        // The border character should be different from the internal box character.
        // 2. Change the row and column limit to change the shape of the box.

        // Expected Output (5X5)
        // *****
        // *###*
        // *###*
        // *###*
        // *****

        // (3X4)
        // ****
        // *##*
        // ****

        // (2X2)
        // **
        // **

        int width = 5;
        int height = 5;

        //We're looping through all rows
        //we're calling first row 1 (could be 0 if we wanted)
        //and the last row height (could be the height -1, if we are doing 0 indexing)

        for(int row = 1; row <= height; row ++){

            for(int col = 1; col <= width; col++){

                if(row == 1 || row == height || col ==1 || col == width) {

                    System.out.print("*");
                } else {
                    System.out.println("#");
                }
            }
            System.out.println();
        }
    }
}
