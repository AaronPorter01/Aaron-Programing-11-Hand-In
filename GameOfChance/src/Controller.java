import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller
{
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

    private Player player = new Player();
    private int roll1 = 0;
    private int roll2 = 0;
    private boolean hasGuessed = false;

    public void newGame(ActionEvent actionEvent)
    {
        player = new Player();
        hasGuessed = false;

        startRoundBtn.setDisable(false);
        betDecreaseBtn.setDisable(true);
        betIncreaseBtn.setDisable(true);
        guessHigherBtn.setDisable(true);
        guessLowerBtn.setDisable(true);
        rollBtn.setDisable(true);

        winnerDisplayedTxt.setText("Good Luck!");
        rollDisplayTxt.setText("Start The Round");
        totalMoneyTxt.setText("Total Money: $" + player.getTotalMoney());
        betTxt.setText("Bet: $" + player.getBet());
        guessTxt.setText("Guess:");
    }

    public void betIncrease(ActionEvent actionEvent)
    {
        if (player.getBet() < player.getTotalMoney())
        {
            player.increaseBet();
            betTxt.setText("Bet: $" + player.getBet());

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

    public void betDecrease(ActionEvent actionEvent)
    {
        if (player.getBet() != 0)
        {
            player.decreaseBet();
            betTxt.setText("Bet: $" + player.getBet());

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

    public void guessHigher(ActionEvent actionEvent)
    {
        player.setGuessedHigher(true);
        guessTxt.setText("Guess: Higher");
        guessHigherBtn.setDisable(true);
        guessLowerBtn.setDisable(false);

        hasGuessed = true;
        canRoll();
    }

    public void guessLower(ActionEvent actionEvent)
    {
        player.setGuessedHigher(false);
        guessTxt.setText("Guess: Lower");
        guessHigherBtn.setDisable(false);
        guessLowerBtn.setDisable(true);

        hasGuessed = true;
        canRoll();
    }

    public void startRound(ActionEvent actionEvent)
    {
        if (player.getTotalMoney() == 0)
        {
            rollDisplayTxt.setText("Out of Money!");
            winnerDisplayedTxt.setText("Start a New Game");
            return;
        }

        winnerDisplayedTxt.setText("Round In Progress");

        betDecreaseBtn.setDisable(true);
        betIncreaseBtn.setDisable(false);
        guessHigherBtn.setDisable(false);
        guessLowerBtn.setDisable(false);

        player.setBet(0);
        betTxt.setText("Bet: $" + player.getBet());
        hasGuessed = false;
        guessTxt.setText("Guess:");

        do
        {
            double random = Math.random() * 100;
            roll1 = (int)random;
            System.out.println(roll1);
        } while (roll1 > 20 || roll1 < 0);

        rollDisplayTxt.setText("Roll: " + roll1);
        startRoundBtn.setDisable(true);
    }

    public void roll(ActionEvent actionEvent)
    {
        betDecreaseBtn.setDisable(true);
        betIncreaseBtn.setDisable(true);
        guessHigherBtn.setDisable(true);
        guessLowerBtn.setDisable(true);

        do
        {
            double random = Math.random() * 100;
            roll2 = (int)random;
            System.out.println(roll2);
        } while (roll2 > 20 || roll2 < 0);

        rollDisplayTxt.setText("Roll: " + roll2);
        startRoundBtn.setDisable(false);
        rollBtn.setDisable(true);
        betTxt.setText("Bet: $0");
        guessTxt.setText("Guess: ");

        hasWon();
    }

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

     public void hasWon()
     {
         boolean higherWon;

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
