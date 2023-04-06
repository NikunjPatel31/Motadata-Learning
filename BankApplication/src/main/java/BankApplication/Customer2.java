package BankApplication;


import BankApplication.db.BankDB;
import org.json.JSONObject;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Customer2
{
    BankDB bankDB;

    {
        bankDB = new BankDB();
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
    {
        Customer2 Customer2 = new Customer2();
        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.REQ))
        {
            socket.connect("tcp://localhost:6001");

            while (true)
            {
                long accountID = -1;
                try
                {
                    System.out.println("Enter a choice: ");

                    System.out.println("1. Login");

                    System.out.println("2. Create account");

                    System.out.print("Choice: ");

                    int choice = Integer.parseInt(reader.readLine());

                    switch (choice)
                    {
                        case 1:
                            // login...
                        {
                            JSONObject request = Customer2.login();

                            // send request to server for login...
                            socket.send(request.toString());

                            // response for login from server...
                            JSONObject response = new JSONObject(new String(socket.recv()));

//                            System.out.println(response);

                            int customerID = Integer.parseInt(response.get("CustomerID").toString());

                            if (response.get("Status").toString().equals("ok"))
                            {
                                System.out.println("Login successful");

                                while (true)
                                {
                                    System.out.println("Select Operation: ");

                                    System.out.println("1. Withdraw");

                                    System.out.println("2. Deposit");

                                    System.out.println("3. Check Balance");

                                    System.out.println("4. Exit");

                                    System.out.print("Choice: ");

                                    int operation = Integer.parseInt(reader.readLine());

                                    if (operation == 4) break;

                                    switch (operation)
                                    {
                                        case 1:
                                            // withdraw
                                            break;
                                        case 2:
                                            // deposit
                                            break;
                                        case 3:
                                            // check balance

                                            request = Customer2.getBalance(accountID);

                                            //send request to server...
                                            socket.send(request.toString());

                                            // response from server...
                                            response = new JSONObject(new String(socket.recv()));

                                            System.out.println(response);

                                            break;
                                        default:
                                            System.out.println("Invalid choice ");
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("Login failed");
                            }
                        }

                        break;
                        case 2:
                            // create account...

                            JSONObject customer = Customer2.getUserDetails();

                            if (customer != null)
                            {
                                // socket.send("createCustomer");

                                socket.send(customer.toString());

                                JSONObject response = new JSONObject(new String(socket.recv()));

                                System.out.println(response.get("Message"));

                                JSONObject jsonObject = Customer2.getAccountDetails();

                                jsonObject.put("CustomerID", response.get("CustomerID"));

                                socket.send(jsonObject.toString().getBytes());

                                response = new JSONObject(new String(socket.recv()));

                                // we will need this accountID when will need to check for balance...
                                accountID = Long.parseLong(response.get("AccountID").toString());

                                System.out.println(response.get("Message"));

                            } else
                            {
                                // exception thrown...
                            }

                            if (customer != null)
                            {
                                while (true)
                                {
                                    System.out.println("Select Operation: ");

                                    System.out.println("1. Withdraw");

                                    System.out.println("2. Deposit");

                                    System.out.println("3. Check Balance");

                                    System.out.println("4. Exit");

                                    System.out.print("Choice: ");

                                    int operation = Integer.parseInt(reader.readLine());

                                    if (operation == 4) break;

                                    switch (operation)
                                    {
                                        case 1:
                                            // withdraw
                                            break;
                                        case 2:
                                            // deposit
                                            break;
                                        case 3:
                                            // check balance

                                            var request = Customer2.getBalance(accountID);

                                            //send request to server...
                                            socket.send(request.toString());

                                            // response from server...
                                            var response = new JSONObject(new String(socket.recv()));

                                            System.out.println("Current Balance: "+response.get("Balance"));

                                            break;
                                        default:
                                            System.out.println("Invalid choice ");
                                    }
                                }
                            }

                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                } catch (Exception exception)
                {
                    System.out.println("Exception: " + exception);

                    exception.printStackTrace();
                }
            }

        } catch (Exception exception)
        {
            System.out.println("Exception: " + exception);

            exception.printStackTrace();
        }
    }

    public JSONObject login()
    {
        try
        {
            JSONObject request = new JSONObject();

            {
                System.out.print("CustomerID: ");

                request.put("CustomerID", reader.readLine());

                System.out.print("Password: ");

                request.put("Password", reader.readLine());

                request.put("Operation", "login");

                return request;
            }
        }
        catch (Exception exception)
        {
            System.out.println("Exception: " + exception);

            exception.printStackTrace();
        }
        return null;
    }

    public JSONObject getUserDetails()
    {
        try
        {
            JSONObject jsonObject = new JSONObject();

            {
                System.out.print("Name: ");

                jsonObject.put("Name", reader.readLine());

                System.out.print("Email: ");

                jsonObject.put("Email", reader.readLine());

                System.out.print("Contact: ");

                jsonObject.put("Contact", reader.readLine());

                System.out.print("Address: ");

                jsonObject.put("Address", reader.readLine());

                System.out.print("Password: ");

                jsonObject.put("Password", reader.readLine());

                jsonObject.put("Operation", "createCustomer");

            }
            return jsonObject;

            /*
            System.out.println("<----------------------------------------------->");

            System.out.print("Enter initial balance: ");

            Saving account = new Saving();

            account.setBalance(Long.parseLong(reader.readLine()));

            account.setCustomerID(customer.getCustomerID());

            account.setAccountID();*/

//            System.out.println("Select type of account");

        } catch (Exception exception)
        {
            System.out.println("Exception: " + exception);

            exception.printStackTrace();
        }

        return null;
    }

    public JSONObject getAccountDetails()
    {
        try
        {
            System.out.println("-------------------------------------------------");

            System.out.print("Enter initial balance: ");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("balance", reader.readLine());

            jsonObject.put("Operation", "createAccount");

            return jsonObject;

//            System.out.println("Select type of account");
        } catch (Exception exception)
        {
            System.out.println("Exception: " + exception);

            exception.printStackTrace();
        }

        return null;
    }

    public JSONObject getBalance(long accountID)
    {
        try
        {
            var request = new JSONObject();

            request.put("Operation", "check balance");

            request.put("AccountID", accountID);

            return request;
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

        return null;
    }
}