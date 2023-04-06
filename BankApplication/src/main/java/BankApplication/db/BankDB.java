package BankApplication.db;

import BankApplication.Model.Account;
import BankApplication.Model.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class BankDB
{
    Map<Integer, Customer> customerMap;

    Map<Long, Account> accountMap;

    public BankDB()
    {
        customerMap = new HashMap<>();

        accountMap = new HashMap<>();
    }

    public BankDB(Map<Integer, Customer> customerMap, Map<Long, Account> accountMap)
    {
        this.customerMap = customerMap;
        this.accountMap = accountMap;
    }

    public void setCustomerMap(Map<Integer, Customer> customerMap)
    {
        this.customerMap = customerMap;
    }

    public void setAccountMap(Map<Long, Account> accountMap)
    {
        this.accountMap = accountMap;
    }

    public Map<Integer, Customer> getCustomerMap()
    {
        return customerMap;
    }

    public Map<Long, Account> getAccountMap()
    {
        return accountMap;
    }

    public void addCustomer(Customer customer)
    {
        customerMap.put(customer.getCustomerID(), customer);
    }

    public void addAccount(Account account)
    {
        accountMap.put(account.getAccountNumber(), account);
    }

    public boolean checkCredential(int customerID, String password)
    {
        return customerMap.containsKey(customerID) &&
                customerMap.get(customerID).getPassword().equals(password);
    }

    public Customer getCustomer(int customerID)
    {
        return customerMap.get(customerID);
    }

    public long getBalance(long accountID)
    {
        try
        {
            return accountMap.get(accountID).getBalance();
        }
        catch (Exception exception)
        {
            System.out.println("Exception->getBalance: "+exception);

            exception.printStackTrace();
        }
        return -1;
    }

    public Account getAccount(long accountID)
    {
        return accountMap.get(accountID);
    }

}
