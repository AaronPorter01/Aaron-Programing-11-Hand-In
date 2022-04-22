public class Player
{
    // declare variables
    private int totalMoney;
    private int bet;
    private boolean guessedHigher;

    // constructor
    Player()
    {
        this.totalMoney = 500;
        this.bet = 0;
        this.guessedHigher = false;
    }

    // variable modify functions

    public int increaseBet()
    {
        this.bet += 50;
        return bet;
    }
    public int decreaseBet()
    {
        this.bet -= 50;
        return bet;
    }
    public int getBet()
    {
        return bet;
    }
    public void setBet(int bet)
    {
        this.bet = bet;
    }

    public int getTotalMoney()
    {
        return totalMoney;
    }

    public void addMoney()
    {
        totalMoney += bet;
    }

    public void removeMoney()
    {
        totalMoney -= bet;
    }

    public boolean getGuessedHigher()
    {
        return guessedHigher;
    }
    public void setGuessedHigher(boolean guessedHigher)
    {
        this.guessedHigher = guessedHigher;
    }
}
