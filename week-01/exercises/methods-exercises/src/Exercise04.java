public class Exercise04 {

    public static void main(String[] args) {
        System.out.println(getFirstVowel("magnificent")); // Expected: a
        System.out.println(getFirstVowel("winsome")); // Expected: i
        System.out.println(getFirstVowel("xxx")); // Expected:

        // 2. Call getFirstVowel at least one more time.
    }

    // getFirstVowel returns the first vowel in a string as a char.
    // 1. Complete getFirstVowel.
    // If no vowel is found, return 0. (As a char, 0 represents the NULL value.)

    public static char getFirstVowel(String value) {

        for (int index = 0; index < value.length(); index++) {

            char letter = value.charAt(index);

            boolean isVowel = false;
            switch (letter) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    return letter;
            }


        }

        return 0;
    }
}




//    public static char getFirstVowel(String value) {
//
//        //at some point we want to call value/getCharAt() to pull out the first vowel. However getCharAt() needs an index
//        char firstVowel = 0;
//        //how do we find the index of the first vowel?
//        int vowelIndex = 1;
//
//        int aIndex = value.indexOf('a');
//        int eIndex = value.indexOf('e');
//        int iIndex = value.indexOf('i');
//        int oIndex = value.indexOf('o');
//        int uIndex = value.indexOf('u');
//
//
//        if(vowelIndex == -1 || (aIndex != -1 && aIndex < vowelIndex)){
//            vowelIndex = aIndex;
//        }
//
//        if(vowelIndex == -1 || (eIndex != -1 && eIndex < vowelIndex)){
//            vowelIndex = eIndex;
//        }
//
//        if(vowelIndex == -1 || (iIndex != -1 && iIndex < vowelIndex)){
//            vowelIndex = iIndex;
//        }
//
//        if(vowelIndex == -1 || (oIndex != -1 && oIndex < vowelIndex)){
//            vowelIndex = oIndex;
//        }
//
//        if(vowelIndex == -1 || (uIndex != -1 && uIndex < vowelIndex)){
//            vowelIndex = uIndex;
//        }
//
//        if(vowelIndex != -1){
//            firstVowel = value.charAt(vowelIndex);
//        }
//
//        return firstVowel;
//    }

