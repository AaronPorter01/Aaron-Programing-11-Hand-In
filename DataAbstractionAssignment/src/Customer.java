import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        name = "";
        accountNumber = 0;
        checkBalance = 0;
        savingBalance = 0;
        savingRate = 0;
        deposits = new ArrayList<>();
        withdraws = new ArrayList<>();
    }
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        this.name = name;
        this.accountNumber = accountNumber;
        this.checkBalance = 0;
        this.savingBalance = 0;

        deposits = new ArrayList<>();
        withdraws = new ArrayList<>();

        deposit(checkDeposit, new Date(), CHECKING);
        deposit(savingDeposit, new Date(), SAVING);

        this.savingRate = savingBalance/checkBalance;
    }

    // Requires: double, Date, String
    // Modifies: this
    // Effects: creates new deposit and adds it to deposit list, then adds the amount to either checking or saving
    public double deposit(double amt, Date date, String account){
        Deposit deposit = new Deposit(amt, date, account);
        deposits.add(deposit);
        if (account.equals(CHECKING))
        {
            checkBalance += amt;
            savingRate = savingBalance/checkBalance;
            return checkBalance;
        }
        else
        {
            savingBalance += amt;
            savingRate = savingBalance/checkBalance;
            return savingBalance;
        }
    }

    // Requires: double, Date, String
    // Modifies: this
    // Effects: checks overdraft, then creates a new withdraw and adds it to withdraw list,
    //          then removes that amount from either checking or saving
    public double withdraw(double amt, Date date, String account){
        if (checkOverdraft(amt, account))
        {
            System.out.println("error: insufficient funds");
            return 0;
        }
        else
        {
            Withdraw withdraw = new Withdraw(amt, date, account);
            withdraws.add(withdraw);
            if (account.equals(CHECKING))
            {
                checkBalance -= amt;
                savingRate = savingBalance/checkBalance;
                return checkBalance;
            }
            else
            {
                savingBalance -= amt;
                savingRate = savingBalance/checkBalance;
                return savingBalance;
            }
        }
    }
    private boolean checkOverdraft(double amt, String account){
        if (account.equals(CHECKING))
        {
            if (checkBalance - amt >= 0)
                return false;
            else
                return true;
        }
        else
        {
            if (savingBalance - amt >= 0)
                return false;
            else
                return true;
        }
    }
    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }

    // getters and setters
    public int getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public ArrayList<Deposit> getDeposits()
    {
        return deposits;
    }

    public void setDeposits(ArrayList<Deposit> deposits)
    {
        this.deposits = deposits;
    }

    public ArrayList<Withdraw> getWithdraws()
    {
        return withdraws;
    }

    public void setWithdraws(ArrayList<Withdraw> withdraws)
    {
        this.withdraws = withdraws;
    }

    public double getCheckBalance()
    {
        return checkBalance;
    }

    public void setCheckBalance(double checkBalance)
    {
        this.checkBalance = checkBalance;
    }

    public double getSavingBalance()
    {
        return savingBalance;
    }

    public void setSavingBalance(double savingBalance)
    {
        this.savingBalance = savingBalance;
    }

    public double getSavingRate()
    {
        return savingRate;
    }

    public void setSavingRate(double savingRate)
    {
        this.savingRate = savingRate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getOVERDRAFT()
    {
        return OVERDRAFT;
    }
}
