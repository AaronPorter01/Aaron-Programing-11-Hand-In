import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller
{
    // gui variables
    public Button betIncreaseBtn;
    public Button betDecreaseBtn;
    public Button newGameBtn;
    public Text totalMoneyTxt;
    public Text betTxt;
    public Button guessHigherBtn;
    public Button guessLowerBtn;
    public Text guessTxt;
    public Button rollBtn;
    public Button startRoundBtn;
    public Text rollDisplayTxt;
    public Text winnerDisplayedTxt;

    // custom variables
    private Player player = new Player();
    private int roll1 = 0;
    private int roll2 = 0;
    private boolean hasGuessed = false;

    // when player makes a new game
    public void newGame(ActionEvent actionEvent)
    {
        player = new Player();
        hasGuessed = false;

        // set other buttons to correct state
        startRoundBtn.setDisable(false);
        betDecreaseBtn.setDisable(true);
        betIncreaseBtn.setDisable(true);
        guessHigherBtn.setDisable(true);
        guessLowerBtn.setDisable(true);
        rollBtn.setDisable(true);

        // set correct text
        winnerDisplayedTxt.setText("Good Luck!");
        rollDisplayTxt.setText("Start The Round");
        totalMoneyTxt.setText("Total Money: $" + player.getTotalMoney());
        betTxt.setText("Bet: $" + player.getBet());
        guessTxt.setText("Guess:");
    }

    // when player adds to their bet
    public void betIncrease(ActionEvent actionEvent)
    {
        // check that player isn't going over money limit
        if (player.getBet() < player.getTotalMoney())
        {
            player.increaseBet();
            betTxt.setText("Bet: $" + player.getBet());

            // check if player can still increase bet
            // if they can't disable button
            if (player.getBet() == player.getTotalMoney())
            {
                betIncreaseBtn.setDisable(true);
            }
            if (player.getBet() != 0)
            {
                betDecreaseBtn.setDisable(false);
            }

            canRoll();
        }
    }

    // when player removes from their bet
    public void betDecrease(ActionEvent actionEvent)
    {
        // check player bet isn't going under 0
        if (player.getBet() != 0)
        {
            player.decreaseBet();
            betTxt.setText("Bet: $" + player.getBet());

            // check if player can still decrease bet
            // if they can't disable button
            if (player.getBet() == 0)
            {
                betDecreaseBtn.setDisable(true);
            }
            if (player.getBet() != player.getTotalMoney())
            {
                betIncreaseBtn.setDisable(false);
            }

            canRoll();
        }
    }

    // when player guesses the next roll will be higher
    public void guessHigher(ActionEvent actionEvent)
    {
        player.setGuessedHigher(true);
        guessTxt.setText("Guess: Higher");
        guessHigherBtn.setDisable(true);
        guessLowerBtn.setDisable(false);

        hasGuessed = true;
        canRoll();
    }

    // when player guesses the next roll will be lower
    public void guessLower(ActionEvent actionEvent)
    {
        player.setGuessedHigher(false);
        guessTxt.setText("Guess: Lower");
        guessHigherBtn.setDisable(false);
        guessLowerBtn.setDisable(true);

        hasGuessed = true;
        canRoll();
    }

    // when player starts a new round
    public void startRound(ActionEvent actionEvent)
    {
        // check player still has money
        if (player.getTotalMoney() == 0)
        {
            rollDisplayTxt.setText("Out of Money!");
            winnerDisplayedTxt.setText("Start a New Game");
            return;
        }

        winnerDisplayedTxt.setText("Round In Progress");

        // set buttons to correct state
        betDecreaseBtn.setDisable(true);
        betIncreaseBtn.setDisable(false);
        guessHigherBtn.setDisable(false);
        guessLowerBtn.setDisable(false);

        // reset values from last round
        player.setBet(0);
        betTxt.setText("Bet: $" + player.getBet());
        hasGuessed = false;
        guessTxt.setText("Guess:");

        // do first roll
        // do until the program gets a number between 0 and 20
        do
        {
            double random = Math.random() * 100;
            roll1 = (int)random;
            System.out.println(roll1);
        } while (roll1 > 20 || roll1 < 0);

        // display roll
        rollDisplayTxt.setText("Roll: " + roll1);
        startRoundBtn.setDisable(true);
    }

    // when player starts second roll
    public void roll(ActionEvent actionEvent)
    {
        // set button states
        betDecreaseBtn.setDisable(true);
        betIncreaseBtn.setDisable(true);
        guessHigherBtn.setDisable(true);
        guessLowerBtn.setDisable(true);

        // do second roll
        // do until the program gets a number between 0 and 20
        do
        {
            double random = Math.random() * 100;
            roll2 = (int)random;
            System.out.println(roll2);
        } while (roll2 > 20 || roll2 < 0);

        // set display texts and button states
        rollDisplayTxt.setText("Roll: " + roll2);
        startRoundBtn.setDisable(false);
        rollBtn.setDisable(true);
        betTxt.setText("Bet: $0");
        guessTxt.setText("Guess: ");

        hasWon();
    }

    // checks if player can trigger next roll or not
     public void canRoll()
     {
         if (player.getBet() != 0 && hasGuessed)
         {
             rollBtn.setDisable(false);
         }
         else
         {
             rollBtn.setDisable(true);
         }
     }

     // checks if player has won the round
     public void hasWon()
     {
         boolean higherWon;

         // compares both rolls
         if (roll1 == roll2)
         {
             higherWon = false;
         }
         else if (roll1 < roll2)
         {
             higherWon = true;
         }
         else
         {
             higherWon = false;
         }

         // checks whether the players guess was correct
         // displays correct message and adds or removes money
         if (higherWon)
         {
             if (player.getGuessedHigher())
             {
                 winnerDisplayedTxt.setText("You Won");
                 player.addMoney();
                 totalMoneyTxt.setText("Total Money: $" + player.getTotalMoney());

                 System.out.println("Player Won");
             }
             else
             {
                 winnerDisplayedTxt.setText("You Lost");
                 player.removeMoney();
                 totalMoneyTxt.setText("Total Money: $" + player.getTotalMoney());

                 System.out.println("Player Lost");
             }
         }
         else
         {
             if (player.getGuessedHigher())
             {
                 winnerDisplayedTxt.setText("You Lost");
                 player.removeMoney();
                 totalMoneyTxt.setText("Total Money: $" + player.getTotalMoney());

                 System.out.println("Player Lost");
             }
             else
             {
                 winnerDisplayedTxt.setText("You Won");
                 player.addMoney();
                 totalMoneyTxt.setText("Total Money: $" + player.getTotalMoney());

                 System.out.println("Player Won");
             }
         }
     }
}
