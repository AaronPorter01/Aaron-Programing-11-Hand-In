import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // create scanner
        Scanner scanner = new Scanner(System.in);

        // declare variables
        char[] encryptedString;
        String inputString;
        String scannerAnswer;
        char[] translatedChar;

        // get input from user
        System.out.println("Enter Word: ");
        inputString = scanner.next();
        // set size of encryptedString array
        encryptedString = new char[inputString.length()];
        // set size of translatedChar array
        translatedChar = new char[encryptedString.length];

        // encrypt word
        // the words are being encrypted by getting their ASCII value and adding 10 onto it
        for (int i = 0; i < inputString.length(); i++)
        {
            // declare variables needed to hold certain values temporarily
            int charNumber;
            int encryptedNumber;

            // set the charNumber of the current character
            charNumber = (int)inputString.charAt(i);

            // check if charNumber goes past the values that can be used and still translated back
            // if it does we take the charNumber and remove the ASCII value of 'p' (112) from it
            // we then add back on the ASCII value of 55 to make sure the number stays in the valid area
            if (charNumber >= 'p')
                encryptedNumber = (charNumber - 'z') + 55;
            else
                encryptedNumber = charNumber;

            encryptedNumber += 10;
            // set the final value of the character in the encryptedString
            encryptedString[i] += encryptedNumber;

            // Debugging code to see original chars and encrypted chars
            //System.out.println(encryptedString[i] + "\t" + inputString.charAt(i));
        }

        // print out encrypted word
        // since it's an array of chars we have to go through a loop to get each character out
        System.out.print("Encrypted Word: ");
        for (int j = 0; j < encryptedString.length; j++)
        {
            System.out.print(encryptedString[j]);
        }

        // move 2 lines down
        System.out.print("\n\n");

        // ask if the user wants to see the original word
        // if they enter an invalid answer the program keeps asking them until they give a valid answer
        do
        {
            System.out.println("Would you like do see the original word? (y = yes, n = no): ");
            scannerAnswer = scanner.next();

        } while (!scannerAnswer.equals("y") && !scannerAnswer.equals("n"));

        // if the user wants to see the word the encrypted word gets translated back to the original word
        if (scannerAnswer.equals("y"))
        {
            // translator
            for (int i = 0; i < encryptedString.length; i++)
            {
                // declare variables needed to hold certain values temporarily
                int originalValue = encryptedString[i];

                // make sure value stays inside limits
                if (originalValue <= 'A')
                {
                    originalValue = (originalValue + 'z') - 55;
                }

                originalValue -= 10;
                // set translatedChar value at each position
                translatedChar[i] = (char) originalValue;

                // Debugging code to see original chars and encrypted chars
                //System.out.println(translatedChar[i] + "\t" + encryptedString[i]);
            }

            // print out original word
            System.out.print("Original Word: ");
            for (int j = 0; j < translatedChar.length; j++)
            {
                System.out.print(translatedChar[j]);
            }
        }
        else
            System.out.println("Goodbye");
    }
}
