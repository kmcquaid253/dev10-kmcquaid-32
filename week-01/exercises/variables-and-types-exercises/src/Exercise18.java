public class Exercise18 {

    public static void main(String[] args) {

        int gemCount = 12;
        double velocity = 33.654;
        boolean isShiny = true;

        // 1. Print the variable values above with String labels. Use concatenation.

        // Expected Output:
        // Gem Count: 12
        // Velocity: 33.654
        // Is Shiny: true

        String firstOutput = "Gem Count: " + gemCount;
        String secondOutput = "Velocity: " + velocity;
        String thirdOutput = "Is Shiny: " + isShiny;

        System.out.println(firstOutput);
        System.out.println(secondOutput);
        System.out.println(thirdOutput);
    }
}
