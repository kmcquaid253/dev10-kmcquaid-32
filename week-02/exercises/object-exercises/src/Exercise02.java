public class Exercise02 {

    public static void main(String[] args) {

        // 1. Add a getter for the rating field in Musician.

        Musician ocean = new Musician("Frank Ocean", 10);
        System.out.print(ocean.getName());
        // 2. Uncomment the line below and insure that it compiles and runs.
        System.out.println("\t\t\t\t\t\tRating: " + ocean.getRating());

        // 3. Instantiate two musicians and assign them to new variables.
        // 4. Print each musicians' name and rating on a single line.
        Musician lamb = new Musician("Lamb Of God", 5);
        System.out.println("Name:" + lamb.getName() + "\t\t\t\tRating:" + lamb.getRating());

        Musician prada = new Musician("The Devil Wears Prada", 1);
        System.out.println("Name: " + prada.getName() + "\t\tRating: " + prada.getRating());
    }
}
