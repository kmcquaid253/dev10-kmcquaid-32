public class Exercise05 {

    public static void main(String[] args) {
        // 1. Declare an array to hold the names of the world's continents.
        // Do not use array literal notation. Allocate space for 6 continents and then set each value by index.

        String [] worldContinents = new String[7];

        worldContinents[0] = "North America";
        worldContinents[1] = "South America";
        worldContinents[2] = "Africa";
        worldContinents[3] = "Europe";
        worldContinents[4] = "Asia";
        worldContinents[5] = "Australia";
        worldContinents[6] = "Antarctica";

        // 2. Loop over each element and print it.

        for(int i = 0; i < worldContinents.length; i++){
            System.out.println(worldContinents[i]);
        }
    }
}
