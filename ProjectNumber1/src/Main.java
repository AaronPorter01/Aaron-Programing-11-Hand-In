import java.util.Scanner;
public class Main {
    //Code your solution to problem number one here
    static int problemOne(String s){

        // declare variables
        char[] vowels = new char[5];
        int vowelCount = 0;

        // set the vowel at each position in the vowels array
        vowels[0] = 'a';
        vowels[1] = 'e';
        vowels[2] = 'i';
        vowels[3] = 'o';
        vowels[4] = 'u';

        // compare each letter in the string with each vowel
        for (int i = 0; i < s.length(); i++)
        {
            for (int j = 0; j < vowels.length; j++)
            {
                // if the letter is equal to one of the vowels increment the vowelCount
                if (s.charAt(i) == vowels[j])
                {
                    vowelCount++;
                }
            }
        }

        return vowelCount;
    }
    //Code you problem number two here
    static int problemTwo(String s){

        // declare variables
        int bobCount = 0;

        // start ahead to make sure the program doesn't get an out of bounds error
        // check if the current 3 letters are equal to bob
        for (int i = 3; i <= s.length(); i++)
        {
            // if the current letters equal bob then increment bobCount
            if (s.substring(i - 3, i).equals("bob"))
            {
                bobCount++;
            }
        }

        return bobCount;
    }
    //Code your solution to problem number 3 here
    static String problemThree(String s){

        // declare variables
        String longest = "";
        String hold = "";
        char lastChar = s.charAt(0);

        // set hold to start with first letter of s
        hold += s.charAt(0);
        // compare letters
        for (int i = 1; i < s.length(); i++)
        {
            // if the current letter is greater than or equal to the last, add it on to hold
            // set the lastChar to the new char being added on
            if (s.charAt(i) >= lastChar)
            {
                hold += s.charAt(i);
                lastChar = s.charAt(i);
                // if the current substring is longer than the longest one set the current one as the longest one
                if (hold.length() > longest.length())
                    longest = hold;
            }
            else
            {
                // set the last char to be the current one
                // set hold to start with the new lastChar
                lastChar = s.charAt(i);
                hold = "";
                hold += lastChar;
            }
        }

        // if longest is still nothing then set longest to the first letter of s
        if (longest.equals(""))
            longest += s.charAt(0);

        return longest;
    }
    public static void main(String[] args) {

        String s = "azcbobobegghakl";

        System.out.println("Longest substring in alphabetical order is: " + problemThree(s));

        /*
        Set s to a string and run your method using s as the parameter
        Run your method in a println statement to determine what the output was
        Once you think you have it working try running the tests.
        The tests will put your method through several different Strings to test
        all possible cases.  If you have 100% success then there is no bugs in your methods.
         */
    }
}
