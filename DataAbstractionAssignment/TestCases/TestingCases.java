import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestingCases
{
    Customer customer;
    Deposit deposit;
    Withdraw withdraw;
    Date date;

    @Before
    public void setUp()
    {
        customer = new Customer("John", 1234, 500, 300);
        date = new Date();
    }

    @Test
    public void testDepositToString()
    {
        deposit = new Deposit(100.50, date, Customer.CHECKING);
        assertEquals(deposit.toString(), "Deposit of: $100.5 Date: " + date + " into account: Checking");

        deposit = new Deposit(200.75, date, Customer.SAVING);
        assertEquals(deposit.toString(), "Deposit of: $200.75 Date: " + date + " into account: Saving");
    }

    @Test
    public void testWithdrawToString()
    {
        withdraw = new Withdraw(100.5, date, Customer.CHECKING);
        assertEquals(withdraw.toString(), "Withdraw of: $100.5 Date: " + date + " from account: Checking");

        withdraw = new Withdraw(200.75, date, Customer.SAVING);
        assertEquals(withdraw.toString(), "Withdraw of: $200.75 Date: " + date + " from account: Saving");
    }

    @Test
    public void testDeposit()
    {
        // create a new checking deposit and check that the deposits size grows
        // then check that the deposit has been added to the checking balance
        // then display the deposits
        customer.deposit(100, date, Customer.CHECKING);
        assertEquals(customer.getDeposits().size(),3);
        assertEquals(customer.getCheckBalance(), 600, 0.00001);
        customer.displayDeposits();
        System.out.println("=============================================================================");

        // create a new saving deposit and check that the deposits size grows
        // then check that the deposit has been added to the saving balance
        // then display the deposits
        customer.deposit(200, date, Customer.SAVING);
        assertEquals(customer.getDeposits().size(),4);
        assertEquals(customer.getSavingBalance(), 500, 0.00001);
        customer.displayDeposits();
    }

    @Test
    public void testWithdraw()
    {
        // create a new checking withdraw and check that the withdrawals size grows
        // then check that the withdrawal has been taken from the checking balance
        // then display the withdrawals
        customer.withdraw(100, date, Customer.CHECKING);
        assertEquals(customer.getWithdraws().size(),1);
        assertEquals(customer.getCheckBalance(), 400, 0.00001);
        customer.displayWithdraws();
        System.out.println("=============================================================================");

        // create a new saving withdraw and check that the withdrawals size grows
        // then check that the withdrawal has been taken from the saving balance
        // then display the withdrawals
        customer.withdraw(200, date, Customer.SAVING);
        assertEquals(customer.getWithdraws().size(),2);
        assertEquals(customer.getSavingBalance(), 100, 0.00001);
        customer.displayWithdraws();
    }
}
