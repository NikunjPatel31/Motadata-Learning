package BankApplication.Model;

public interface Account
{
    public long getBalance();

    public void setBalance(long amount);

    public int getCustomerID();

    public void setCustomerID(int customerID);

    public long getMinReqBalance();

    public long getAccountNumber();

    public long withdraw(long amount);

    public long deposit(long amount);
}
