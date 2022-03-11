import java.util.Date;

public class Deposit {
    private double amount;
    private Date date;
    private String account;

    Deposit(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    // Modifies: this
    // Effects: returns a string that shows the amount deposited, the date it was deposited on, and the account it was deposited into
    public String toString(){
        return "Deposit of: $" + amount + " Date: " + date + " into account: " + account;
    }
}
