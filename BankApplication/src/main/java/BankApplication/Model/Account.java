package BankApplication.Model;

import java.util.concurrent.atomic.AtomicLong;

public interface Account
{
    public long getBalance();

    public void setBalance(long amount);

    public int getCustomerID();

    public void setCustomerID(int customerID);

    public long getMinReqBalance();

    public long getMaxWithDrawAmount();

    public long getAccountNumber();
}
