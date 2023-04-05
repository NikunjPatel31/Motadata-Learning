package BankApplication.BankServer;

import BankApplication.Accounts.Saving;
import BankApplication.Model.Customer;
import BankApplication.db.BankDB;
import org.json.JSONObject;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BankServer
{
    BankDB bankDB;

    static BufferedReader reader;

    {
        bankDB = new BankDB();

        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void start()
    {
        BankServer server = new BankServer();

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.REP))
        {
            socket.bind("tcp://*:6001");

            while (!Thread.currentThread().isInterrupted())
            {
                //var reqStr = new String(socket.recv());

                JSONObject request = new JSONObject(new String(socket.recv()));

                switch (request.get("Operation").toString())
                {
                    case "login":
                    {
                        JSONObject response = null;

                        if (server.bankDB.checkCredential(Integer.parseInt(request.get("CustomerID").toString()),
                                request.get("Password").toString()))
                        {
                            // login successfully

                            response = new JSONObject();

                            var customer = server.bankDB.getCustomer(Integer.parseInt(request.get("CustomerID").toString()));

                            if (customer != null)
                            {
                                response.put("CustomerID", customer.getCustomerID());

                                response.put("Message", "Login successful");

                                response.put("AccountID", customer.getAccountID());

                                response.put("Status","ok");
                            }
                            else {
                                // error
                                // show appropriate message

                                response.put("Message", "Error in login");

                                response.put("Status", "error");
                            }

                            socket.send(response.toString());

                        } else
                        {
                            // credential are wrong
                        }
                    }
                    break;
                    case "createCustomer":

                        Customer customer = new Customer();

                    {
                        customer.setName(request.get("Name").toString());

                        customer.setEmail(request.get("Email").toString());

                        customer.setContact(Long.parseLong(request.get("Contact").toString()));

                        customer.setAddress(request.get("Address").toString());

                        customer.setPassword(request.get("Password").toString());

                        customer.setCustomerID();

                        System.out.println("Customer: " + customer);

                        server.bankDB.addCustomer(customer);

                        JSONObject response = new JSONObject();

                        response.put("Message", "Customer added successfully");

                        response.put("CustomerID", customer.getCustomerID());

                        socket.send(response.toString());

                    }
                    break;
                    case "createAccount":

                    {
                        System.out.println("Value: " + request.get("balance"));

                        Saving saving = new Saving();

                        saving.setBalance(Long.parseLong(request.get("balance").toString()));

                        saving.setCustomerID(Integer.parseInt(request.get("CustomerID").toString()));

                        saving.setAccountID();

                        server.bankDB.addAccount(saving);

                        server.bankDB.getCustomer(Integer.parseInt(request.get("CustomerID").toString()))
                                        .setAccountID(saving.getAccountNumber());

                        System.out.println("Account: " + saving);

                        JSONObject response = new JSONObject();

                        response.put("Message", "Account created successfully");

                        response.put("AccountID", saving.getAccountNumber());

                        socket.send(response.toString());

                        System.out.println("Response send: " + response);
                    }
                        break;
                    case "check balance":
                    {
                        var currentBalance = server.bankDB.getBalance(Long.parseLong(request.get("AccountID").toString()));

                        JSONObject response = new JSONObject();

                        if (currentBalance == -1)
                        {
                            // error
                            response.put("Status", "error");

                            System.out.println("AccountID: "+request.get("AccountID"));
                        }
                        else {
                            response.put("Status", "ok");

                            response.put("Balance", currentBalance);
                        }

                        socket.send(response.toString());
                    }
                        break;
                    default:
                        System.out.println("Invalid request");
                }
            }

        } catch (Exception exception)
        {
            System.out.println("Exception: " + exception);

            exception.printStackTrace();
        }
    }
}
