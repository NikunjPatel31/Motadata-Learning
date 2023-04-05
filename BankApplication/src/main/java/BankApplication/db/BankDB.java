package BankApplication.db;

import BankApplication.Model.Account;
import BankApplication.Model.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class BankDB
{
    Map<Integer, Customer> customerMap;

    Map<AtomicLong, Account> accountMap;

    public BankDB()
    {
        customerMap = new HashMap<>();

        accountMap = new HashMap<>();
    }

    public BankDB(Map<Integer, Customer> customerMap, Map<AtomicLong, Account> accountMap)
    {
        this.customerMap = customerMap;
        this.accountMap = accountMap;
    }

    public void setCustomerMap(Map<Integer, Customer> customerMap)
    {
        this.customerMap = customerMap;
    }

    public void setAccountMap(Map<AtomicLong, Account> accountMap)
    {
        this.accountMap = accountMap;
    }

    public Map<Integer, Customer> getCustomerMap()
    {
        return customerMap;
    }

    public Map<AtomicLong, Account> getAccountMap()
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

}
