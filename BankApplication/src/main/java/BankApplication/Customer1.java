package BankApplication;

import BankApplication.db.BankDB;
import org.json.JSONObject;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Customer1
{
    BankDB bankDB;

    {
        bankDB = new BankDB();
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)
    {
        Customer1 customer1 = new Customer1();
        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.PAIR))
        {
            socket.connect("tcp://localhost:6001");

            while (true)
            {
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
                            customer1.login();
                            break;
                        case 2:
                            // create account...

                            // can we use flag here instead of message...

                            JSONObject customer = customer1.getUserDetails();

                            if (customer != null)
                            {
                                socket.send("createCustomer");

                                socket.send(customer.toString());

                                JSONObject response = new JSONObject(new String(socket.recv()));

                                System.out.println(response.get("Message"));

                                JSONObject jsonObject = customer1.getAccountDetails();

                                jsonObject.put("CustomerID", response.get("CustomerID"));

                                socket.send("createAccount");

                                socket.send(jsonObject.toString().getBytes());

                                //System.out.println(new String(socket.recv()));
                            }
                            else {
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
                }
                catch (Exception exception)
                {
                    System.out.println("Exception: "+exception);

                    exception.printStackTrace();
                }
            }

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }

    public void login()
    {

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

            return jsonObject;

//            System.out.println("Select type of account");
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

        return null;
    }
}
