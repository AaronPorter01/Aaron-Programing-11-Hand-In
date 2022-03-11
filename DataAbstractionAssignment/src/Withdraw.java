import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;

    Withdraw(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    // Modifies: this
    // Effects: returns a string that shows the amount withdrawn, the date it was withdrawn on, and the account it was withdrawn from
    public String toString(){
        return "Withdraw of: $" + amount + " Date: " + date + " from account: " + account;
    }
}
