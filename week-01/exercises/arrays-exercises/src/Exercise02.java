public class Exercise02 {

    public static void main(String[] args) {

        String[] tenFavoriteFoods = new String[10]; // space for 10 favorite foods

        tenFavoriteFoods[5] = "squid ink";
        System.out.println(tenFavoriteFoods[5]);
        System.out.println(tenFavoriteFoods[6]);

        // 1. Set your 10 favorite foods. (It's okay to replace squid ink.)
        // 2. Print your top, 6th, and last favorite from tenFavoriteFoods.

        tenFavoriteFoods[0] = "Tacos";
        tenFavoriteFoods[1] = "Tamales";
        tenFavoriteFoods[2] = "Birria";
        tenFavoriteFoods[3] = "Enchiladas";
        tenFavoriteFoods[4] = "Taquitos";
        tenFavoriteFoods[5] = "Ramen";
        tenFavoriteFoods[6] = "Burritos"; //least favorite starts here
        tenFavoriteFoods[7] = "Quesadillas";
        tenFavoriteFoods[8] = "Chilaquiles";
        tenFavoriteFoods[9] = "Pasta";

        System.out.println("\nTop 6 favorite foods:" );
        System.out.println(tenFavoriteFoods[0]);
        System.out.println(tenFavoriteFoods[1]);
        System.out.println(tenFavoriteFoods[2]);
        System.out.println(tenFavoriteFoods[3]);
        System.out.println(tenFavoriteFoods[4]);
        System.out.println(tenFavoriteFoods[5]);

        System.out.println("\nLeast favorite:");
        System.out.println(tenFavoriteFoods[6]);
        System.out.println(tenFavoriteFoods[7]);
        System.out.println(tenFavoriteFoods[8]);
        System.out.println(tenFavoriteFoods[9]);
    }
}
