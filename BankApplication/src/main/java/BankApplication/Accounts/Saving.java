package BankApplication.Accounts;

import BankApplication.Model.Account;

import java.util.concurrent.atomic.AtomicLong;

public class Saving implements Account
{
    private static AtomicLong accountNumber;
    float interestRate;
    long minReqBalance = 2000;
    long minWithdrawLimit;
    long balance;
    int customerID;

    long accountID;

    static
    {
        accountNumber = new AtomicLong();
    }

    public Saving()
    {

    }

    @Override
    public long getBalance()
    {
        return this.balance;
    }

    @Override
    public void setBalance(long amount)
    {
        this.balance = amount;
    }

    @Override
    public int getCustomerID()
    {
        return this.customerID;
    }

    @Override
    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    @Override
    public long getMinReqBalance()
    {
        return this.minReqBalance;
    }

    @Override
    public long getMaxWithDrawAmount()
    {
        return this.minWithdrawLimit;
    }

    @Override
    public long getAccountNumber()
    {
        return this.accountID;
    }

    private long incrementAccountIDCount()
    {
        return this.accountNumber.incrementAndGet();
    }

    public void setAccountID()
    {
        this.accountID = incrementAccountIDCount();
    }

    @Override
    public String toString()
    {
        return "Balance: " + balance +
                ", AccountID: " + accountID +
                ", CustomerID: " + customerID;
    }

    @Override
    public synchronized long withdraw(long amount)
    {
        if ((balance - amount) >= minReqBalance)
        {
            balance -= amount;

            return balance;
        }

        return -1;
    }

    @Override
    public synchronized long deposit(long amount)
    {
        return balance += amount;
    }

    @Override
    public synchronized long transfer(long recipientAccID, long amount)
    {
        var updatedBal = withdraw(amount);

        if (updatedBal != -1)
        {

        }
        return -1;
    }
}
