import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // on boolean that tells the loop whether the game is still on or not
        boolean on = true;

        // score counters
        // declared up here to avoid resetting them to 0 every round
        int playerWins = 0;
        int playerLosses = 0;

        // print instructions
        System.out.println("Choose rock, paper, or scissors. 'r' for rock, 'p' for paper, 's' for scissors, 'x' to end game.");

        // game loop
        do
        {
            // declare variables
            String userChoice;
            String computerChoice;
            int random;
            String userPrintChoice;
            String computerPrintChoice;
            String[] validChoices = new String[3];

            // set validChoices at each position
            // by using an array you can use a for loop to check each option instead of writing out long if statements
            validChoices[0] = "r";
            validChoices[1] = "p";
            validChoices[2] = "s";

            // create scanner to get user input
            Scanner scan = new Scanner(System.in);

            // print start of round text
            System.out.println("****************************************************");
            System.out.println("Wins: " + playerWins + "\tLosses: " + playerLosses);
            System.out.println("Player Choice:");

            // get the users choice and set userChoice variable
            userChoice = scan.next();

            // check if user entered valid choice
            // if they didn't enter a valid choice stop program
            for (int i = 0; i < validChoices.length; i++)
            {
                // if userChoice was "x" stop the game
                if (userChoice.equals("x"))
                {
                    on = false;
                    System.out.println("Thank you for playing!");
                    break;
                }
                // if the choice is valid leave the loop
                else if (userChoice.equals(validChoices[i]))
                {
                    break;
                }
                // if the choice is not valid, and it's been checked against every option, end the program
                else if (i == validChoices.length - 1)
                {
                    System.out.println("Invalid selection. Please play again.");
                    return;
                }
            }

            // if on still continue with game
            if (on)
            {
                // randomly get computer choice
                random = (int)(Math.random() * 3);
                // turn random choice into either rock, paper, or scissors
                // this is easy because I have all the valid choices stored in a variable
                // it's also easier to compare the userChoice and the computerChoice if they're both strings
                computerChoice = validChoices[random];

                // set userPrintChoice to print correct option
                if (userChoice.equals(validChoices[0]))
                    userPrintChoice = "Rock";
                else if (userChoice.equals(validChoices[1]))
                    userPrintChoice = "Paper";
                else
                    userPrintChoice = "Scissors";

                // set computerPrintChoices to print correct option
                if (computerChoice.equals(validChoices[0]))
                    computerPrintChoice = "Rock";
                else if (computerChoice.equals(validChoices[1]))
                    computerPrintChoice = "Paper";
                else
                    computerPrintChoice = "Scissors";

                // compare userChoice and computerChoice
                if (userChoice.equals(computerChoice))
                {
                    // if the user and computer chose the same print "Draw!"
                    System.out.println("Draw!");
                }
                else if (userChoice.equals(validChoices[0]) && computerChoice.equals(validChoices[2]) ||
                        userChoice.equals(validChoices[2]) && computerChoice.equals(validChoices[1]) ||
                        userChoice.equals(validChoices[1]) && computerChoice.equals(validChoices[0]))
                {
                    // if user won print "You Win!"
                    System.out.println("You Win!");
                    // add one point to playerWins
                    playerWins++;
                }
                else
                {
                    // if user didn't win print "You Lose!"
                    System.out.println("You Lose!");
                    // add one point to playerLosses
                    playerLosses++;
                }

                // print player and computer choices
                System.out.println("Computer Choice: " + computerPrintChoice + "\tPlayer Choice: " + userPrintChoice);
            }

        } while (on);
    }
}
