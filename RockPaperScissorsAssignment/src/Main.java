import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
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

        // get the users choice and set userChoice variable
        System.out.println("Choose rock, paper, or scissors. 'r' for rock, 'p' for paper, 's' for scissors.");
        userChoice = scan.next();
        // check if user entered valid choice
        // if they didn't enter a valid choice stop program
        for (int i = 0; i < validChoices.length; i++)
        {
            // if the choice is valid leave the loop
            if (userChoice.equals(validChoices[i]))
                break;
            // if the choice is not valid, and it's been checked against every option, end the program
            else if (i == validChoices.length - 1)
            {
                System.out.println("Invalid selection. Please play again.");
                return;
            }
        }

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
        }
        else
        {
            // if user didn't win print "You Lose!"
            System.out.println("You Lose!");
        }

        // print player and computer choices
        System.out.println("Computer Choice: " + computerPrintChoice + "\tPlayer Choice: " + userPrintChoice);
    }
}
