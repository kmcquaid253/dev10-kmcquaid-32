public class Exercise06 {

    // 1. Create a method.
    // Name: isBetween
    // Inputs: int, int, int
    // Output: boolean
    // Description: return true if the first parameter is between the second and third parameter.
    // Otherwise, returns false.

    public static boolean isBetween(int a, int b, int c){

        boolean b1 = true;

        if(a > b && a < c){
            return b1;
        } else{
            return !b1;
       }
    }


    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.

        System.out.println(isBetween(3,1 ,4 ));
        System.out.println(isBetween(10,0 ,20 ));
        System.out.println(isBetween(16, 8, 32));
        System.out.println(isBetween(100, 200, 300));
        System.out.println(isBetween(100, 50, 200));

    }
}
