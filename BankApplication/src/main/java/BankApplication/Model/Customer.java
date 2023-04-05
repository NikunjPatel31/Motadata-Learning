package BankApplication.Model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Serializable
{
    private static AtomicInteger customerIDCount;
    private String password, name, email, address;

    int customerID;

    private long contact;

    static
    {
        customerIDCount = new AtomicInteger();
    }

    public Customer() {
    }

    public Customer(String name, String email, String address, int customerID, int contact, String password)
    {
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.customerID = customerID;
        this.contact = contact;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setCustomerID()
    {
        this.customerID = incrementCustomerIDCount();
    }

    public void setContact(long contact)
    {
        this.contact = contact;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAddress()
    {
        return address;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public long getContact()
    {
        return contact;
    }

    public AtomicInteger getCustomerIDCount()
    {
        return customerIDCount;
    }

    public void setCustomerIDCount(AtomicInteger customerIDCount)
    {
        this.customerIDCount = customerIDCount;
    }

    public int incrementCustomerIDCount()
    {
        return customerIDCount.incrementAndGet();
    }

    @Override
    public String toString()
    {
        return "Name: "+name+
                "Email: "+email+
                "Contact: "+contact+
                "Address: "+address+
                "Password: "+password+
                "CustomerID: "+customerID;
    }
}
