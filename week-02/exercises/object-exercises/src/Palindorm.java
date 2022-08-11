public class Palindorm{

    public static boolean isPalindrome(String string)
    {

        String reverseString = "";

        // Initializing a boolean
        boolean answer = false;

        //reverses the string by character
        for (int i = string.length() - 1; i >= 0; i--) {
            reverseString = reverseString + string.charAt(i);
        }

        // Check in to see if the strings are equal
        if (string.equals(reverseString)) {
            answer = true;
        }
        return answer;
    }

    public static void main(String[] args)
    {
        // Input string
        String string = "Taco cat";

        // Converting the string to lowercase and replaces all the empty spaces
        string = string.toLowerCase().replaceAll("\\s+","");
        boolean A = isPalindrome(string);
        System.out.println(A);
    }

    //replaceAll("\\s+",""))

}

