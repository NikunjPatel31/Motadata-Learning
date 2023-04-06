package BankApplication;

import BankApplication.BankServer.BankServer;

public class Bootstrap
{
    public static void main(String[] args)
    {
        try
        {
            BankServer.start();
        }
        catch (Exception exception)
        {
            System.out.println("Error in server. Server is shutting down");
        }
    }
}
