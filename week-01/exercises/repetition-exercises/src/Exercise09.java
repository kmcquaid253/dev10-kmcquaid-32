public class Exercise09 {

    public static void main(String[] args) {
        // 1. Write a loop to sum all numbers between 7 and 16.
        // 2. Print the result.


        int sum = 0;

        for(int num = 7; num < 16; num++){
            sum = sum + num;
        }
        System.out.println(sum);
    }
}
