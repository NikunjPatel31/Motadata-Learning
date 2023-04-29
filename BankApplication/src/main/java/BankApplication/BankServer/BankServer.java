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

    public static void start() throws Exception
    {
        BankServer server = new BankServer();

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.REP))
        {
            socket.bind("tcp://10.20.40.197:6001");

            while (!Thread.currentThread().isInterrupted())
            {
                JSONObject request = new JSONObject(new String(socket.recv()));

                switch (request.get("Operation").toString())
                {
                    case "login":
                    {
                        JSONObject response = new JSONObject();

                        try
                        {
                            if (server.bankDB.isAccountActive(Integer.parseInt(request.get("CustomerID").toString())))
                            {
                                response.put("Status", "error");

                                response.put("Message", "User already logged in");
                            }
                            else
                            {
                                System.out.println("Val: "+server.bankDB.isAccountActive(Integer.parseInt(request.get("CustomerID").toString())));
                                if (server.bankDB.checkCredential(Integer.parseInt(request.get("CustomerID").toString()),
                                        request.get("Password").toString()))
                                {
                                    // login successfully
                                    var customer = server.bankDB.getCustomer(Integer.parseInt(request.get("CustomerID").toString()));

                                    if (customer != null)
                                    {
                                        server.bankDB.addActiveAccount(customer.getCustomerID());

                                        response.put("CustomerID", customer.getCustomerID());

                                        response.put("Message", "Login successful");

                                        response.put("AccountID", customer.getAccountID());

                                        response.put("Status","ok");
                                    }
                                    else {
                                        // error
                                        // show appropriate message

                                        response.clear();

                                        response.put("Message", "Error in login");

                                        response.put("Status", "error");
                                    }

                                }
                                else
                                {
                                    // credential are wrong
                                    response.clear();

                                    response.put("Message", "Invalid credentials");

                                    response.put("Status", "error");
                                }
                            }
                        }
                        catch (Exception exception)
                        {
                            exception.printStackTrace();

                            response.clear();

                            response.put("Message", "Error in login");

                            response.put("Status", "error");
                        }
                        finally
                        {
                            socket.send(response.toString());
                        }
                    }
                        break;
                    case "createCustomer":
                    {
                        Customer customer = new Customer();

                        JSONObject response = new JSONObject();

                        try
                        {
                            customer.setName(request.get("Name").toString().trim());

                            customer.setEmail(request.get("Email").toString().trim());

                            customer.setContact(Long.parseLong(request.get("Contact").toString().trim()));

                            customer.setAddress(request.get("Address").toString().trim());

                            customer.setPassword(request.get("Password").toString().trim());

                            customer.setCustomerID();

                            // adding customer to db
                            server.bankDB.addCustomer(customer);

                            response.put("Message", "Customer added successfully");

                            response.put("CustomerID", customer.getCustomerID());

                            response.put("Status", "ok");
                        }
                        catch (Exception exception)
                        {
                            exception.printStackTrace();

                            response.clear();

                            response.put("Message", "Error in registering new customer");

                            response.put("Status", "error");
                        }
                        finally
                        {
                            socket.send(response.toString());
                        }

                    }
                        break;
                    case "createAccount":
                    {
                        JSONObject response = new JSONObject();

                        try
                        {
                            Saving saving = new Saving();

                            saving.setBalance(Long.parseLong(request.get("balance").toString()));

                            saving.setCustomerID(Integer.parseInt(request.get("CustomerID").toString()));

                            saving.setAccountID();

                            // adding new account to db
                            server.bankDB.addAccount(saving);

                            // adding account number to customer entry in db
                            server.bankDB.getCustomer(Integer.parseInt(request.get("CustomerID").toString()))
                                    .setAccountID(saving.getAccountNumber());

                            response.put("Message", "Account created successfully");

                            response.put("AccountID", saving.getAccountNumber());

                            response.put("Status", "ok");

                        }
                        catch (Exception exception)
                        {
                            exception.printStackTrace();

                            response.clear();

                            response.put("Message", "Error in creating new account");

                            response.put("Status", "error");
                        }
                        finally
                        {
                            socket.send(response.toString());
                        }
                    }
                        break;
                    case "check balance":
                    {
                        JSONObject response = new JSONObject();
                        try
                        {
                            var currentBalance = server.bankDB.getBalance(Long.parseLong(request.get("AccountID").toString()));

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
                        }
                        catch (Exception exception)
                        {
                            System.out.println("Error in checking balance");

                            response.clear();

                            response.put("Status", "error");

                            response.put("Message", "Error in checking balance");
                        }
                        finally
                        {
                            socket.send(response.toString());
                        }
                    }
                        break;
                    case "withdraw":
                    {
                        var response = new JSONObject();
                        try
                        {
                            var updatedBal = server.bankDB.getAccount(Long.parseLong(request.get("AccountID").toString()))
                                    .withdraw(Long.parseLong(request.get("Amount").toString()));

                            if (updatedBal == -1)
                            {
                                // insufficient balance
                                response.put("Status","error");

                                response.put("Message", "Insufficient Balance");
                            }
                            else
                            {
                                response.put("Status", "ok");

                                response.put("Message", "Account withdraw complete");

                                response.put("Updated Balance", updatedBal);
                            }
                        }
                        catch (Exception exception)
                        {
                            System.out.println("Error in withdraw amount");

                            response.clear();

                            response.put("Status", "error");

                            response.put("Message", "Error in withdraw");
                        }
                        finally
                        {
                            socket.send(response.toString());
                        }
                    }
                        break;
                    case "deposit":
                    {
                        var response = new JSONObject();
                        try
                        {
                            var updatedBal = server.bankDB.getAccount(Long.parseLong(request.get("AccountID").toString()))
                                    .deposit(Long.parseLong(request.get("Amount").toString()));

                            response.put("Status", "ok");

                            response.put("Message","Deposit completed");

                            response.put("Updated Balance", updatedBal);
                        }
                        catch (Exception exception)
                        {
                            System.out.println("Error in depositing");

                            response.clear();

                            response.put("Status", "error");

                            response.put("Message", "Error in depositing");
                        }
                        finally
                        {
                            socket.send(response.toString());
                        }
                    }
                        break;
                    case "Transfer":
                    {
                        var response = new JSONObject();

                        try
                        {
                            var updatedBal = server.bankDB.getAccount(Long.parseLong(request.get("AccountID").toString()))
                                    .withdraw(Long.parseLong(request.get("Amount").toString()));

                            if (updatedBal != -1)
                            {
                                try
                                {
                                    server.bankDB.getAccount(Long.parseLong(request.get("Recipient AccountID").toString()))
                                            .deposit(Long.parseLong(request.get("Amount").toString()));

                                    response.put("Status", "ok");

                                    response.put("Message", "Transfer completed");

                                    response.put("Updated Balance", updatedBal);
                                }
                                catch (Exception exception)
                                {
                                    System.out.println(exception.getMessage());

                                    response.put("Status", "error");

                                    response.put("Message", exception.getMessage());
                                }

                            }
                            else
                            {
                                response.put("Status", "error");

                                response.put("Message", "Transfer not completed, insufficient balance");
                            }
                        }
                        catch (Exception exception)
                        {
                            System.out.println("Error in transferring");

                            response.clear();

                            response.put("Status", "error");

                            response.put("Message", "Error in transferring");
                        }
                        finally
                        {
                            socket.send(response.toString());
                        }
                    }
                        break;
                    case "logout":
                    {
                        var response = new JSONObject();

                        try
                        {
                            if (server.bankDB.removeActiveAcc(Integer.parseInt(request.get("CustomerID").toString())))
                            {
                                response.put("Status", "ok");

                                response.put("Message", "Log out complete");
                            }
                            else
                            {
                                response.put("Status", "error");

                                response.put("Message", "Error in logging out");
                            }
                        }
                        catch (Exception exception)
                        {
                            response.put("Status", "error");

                            response.put("Message", "Error in logging out");
                        }
                        finally
                        {
                            socket.send(response.toString());
                        }
                    }
                    break;
                    default:
                        System.out.println("Invalid request");
                }
            }

        }
        catch (Exception exception)
        {
            System.out.println("Some error occurred...");

            exception.printStackTrace();
        }
    }
}