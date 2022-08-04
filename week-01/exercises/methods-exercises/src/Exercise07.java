public class Exercise07 {

    // 1. Create a method.
    // Name: areInOrder
    // Inputs: int, int, int, int
    // Output: boolean
    // Description: return true if the four parameters are in ascending order.
    // Otherwise, returns false.

    public static boolean areInOrder(int a, int b, int c, int d){

        /*
            //My code

        //smallest to greatest

        boolean b1 = true;

        if (a <= b && b <= c && c <= d ){
            return b1;
        } else {
            return !b1;
        }

         */

        // Davids code

        boolean inOrder = true;

        if (a > b || b > c || c > d){
            inOrder = false;
        }

        return inOrder;
    }

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        System.out.println(areInOrder(1, 2, 3, 4));
        System.out.println(areInOrder(1,2,4, 3));
        System.out.println(areInOrder(1, 3, 2, 4));
        System.out.println(areInOrder(3, 4, 5, 6));
    }
}
