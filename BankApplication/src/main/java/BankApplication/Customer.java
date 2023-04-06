package BankApplication;

import BankApplication.db.BankDB;
import org.json.JSONObject;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Customer
{
    BankDB bankDB;

    {
        bankDB = new BankDB();
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
    {
        Customer customer = new Customer();
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
                        case 1 ->
                        // login...
                        {
                            JSONObject request = customer.login();

                            // send request to server for login...
                            socket.send(request.toString());

                            // response for login from server...
                            JSONObject response = new JSONObject(new String(socket.recv()));

                            if (response.get("Status").toString().equals("ok"))
                            {
                                System.out.println("-------------------------------------------------");

                                System.out.println("Login successful");

                                System.out.println("-------------------------------------------------");

                                accountID = Long.parseLong(response.get("AccountID").toString());

                                customer.options(socket, accountID);
                            } else
                            {
                                System.out.println("*************************************************");

                                System.out.println(response.get("Message"));

                                System.out.println("*************************************************");
                            }
                        }
                        case 2 ->
                        {
                            // create account...
                            try
                            {
                                JSONObject cus = customer.getUserDetails();

                                if (cus != null)
                                {
                                    socket.send(cus.toString());

                                    JSONObject response = new JSONObject(new String(socket.recv()));

                                    if (response.get("Status").toString().equals("ok"))
                                    {
                                        // new customer registered
                                        System.out.println("-------------------------------------------------");

                                        System.out.println(response.get("Message"));

                                        System.out.println("Your customerID: "+response.get("CustomerID"));

                                        System.out.println("-------------------------------------------------");

                                        JSONObject account = customer.getAccountDetails();

                                        account.put("CustomerID", response.get("CustomerID"));

                                        account.put("Operation", "createAccount");

                                        // sending account details for opening new account...
                                        socket.send(account.toString().getBytes());

                                        // response from server regarding creating of new account
                                        response = new JSONObject(new String(socket.recv()));

                                        if (response.get("Status").toString().equals("ok"))
                                        {
                                            // we will need this accountID when will need to check for balance...
                                            accountID = Long.parseLong(response.get("AccountID").toString());

                                            System.out.println("-------------------------------------------------");

                                            System.out.println(response.get("Message"));

                                            System.out.println("Your accountID: "+response.get("AccountID"));

                                            System.out.println("-------------------------------------------------");

                                            // show options...
                                            customer.options(socket, accountID);
                                        }
                                        else
                                        {
                                            System.out.println("*************************************************");

                                            System.out.println(response.get("Message"));

                                            System.out.println("*************************************************");
                                        }
                                    } else
                                    {
                                        // error in creating new customer
                                        System.out.println("*************************************************");

                                        System.out.println(response.get("Message"));

                                        System.out.println("*************************************************");
                                    }

                                } else
                                {
                                    // exception thrown...

                                    System.out.println("Error...");
                                }
                            }
                            catch (Exception exception)
                            {
                                exception.printStackTrace();
                            }
                        }
                        default -> System.out.println("Invalid choice");
                    }
                } catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }

        } catch (Exception exception)
        {
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

                String value = "";

                while ((value = reader.readLine()).isBlank())
                {
                    System.out.print("Please enter proper name: ");
                }

                jsonObject.put("Name", value);

                System.out.print("Email: ");

                while ((value = reader.readLine()).isBlank())
                {
                    System.out.print("Please enter proper email: ");
                }

                jsonObject.put("Email", value);

                System.out.print("Contact: ");

                while ((value = reader.readLine()).isBlank()
                        || !Pattern.matches("\\d{10}", value))
                {
                    System.out.print("Please enter proper contact: ");
                }

                jsonObject.put("Contact", value);

                System.out.print("Address: ");

                while ((value = reader.readLine()).isBlank())
                {
                    System.out.print("Please enter proper address: ");
                }

                jsonObject.put("Address", value);

                System.out.print("Password: ");

                while ((value = reader.readLine()).isBlank()
                        || !Pattern.matches("[0-9a-zA-Z@#$%]{8,}", value))
                {
                    System.out.print("Please enter proper password: ");
                }

                jsonObject.put("Password", value);

                jsonObject.put("Operation", "createCustomer");

            }
            return jsonObject;

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return null;
    }

    public JSONObject getAccountDetails()
    {
        try
        {
            System.out.print("Enter initial balance: ");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("balance", reader.readLine());

            jsonObject.put("Operation", "createAccount");

            return jsonObject;

        } catch (Exception exception)
        {
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
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return null;
    }

    public void options(ZMQ.Socket socket, long accountID)
    {
        try
        {
            while (true)
            {
                System.out.println("Select Operation: ");

                System.out.println("1. Withdraw");

                System.out.println("2. Deposit");

                System.out.println("3. Check Balance");

                System.out.println("4. Transfer");

                System.out.println("5. Exit");

                System.out.print("Choice: ");

                int operation = Integer.parseInt(reader.readLine());

                if (operation == 5) break;

                switch (operation)
                {
                    case 1 ->
                    // withdraw
                    {
                        try
                        {
                            var request = new JSONObject();

                            System.out.print("Enter amount: ");

                            request.put("Operation", "withdraw");

                            var value = "";
                            while ((value = reader.readLine()).isBlank()
                                    || !Pattern.matches("\\d+",value))
                            {
                                System.out.print("Please enter proper amount: ");
                            }

                            request.put("Amount", value);

                            request.put("AccountID", accountID);

                            // send request...
                            socket.send(request.toString());

                            // response from server
                            var response = new JSONObject(new String(socket.recv()));

                            if (response.get("Status").toString().equals("ok"))
                            {
                                System.out.println("-------------------------------------------------");

                                System.out.println(response.get("Message"));

                                System.out.println("\nUpdated balance: " + response.get("Updated Balance"));

                                System.out.println("-------------------------------------------------");
                            } else
                            {
                                System.out.println("-------------------------------------------------");

                                System.out.println(response.get("Message"));

                                System.out.println("-------------------------------------------------");
                            }
                        } catch (Exception exception)
                        {
                            exception.printStackTrace();
                        }
                    }
                    case 2 ->
                    // deposit
                    {
                        try
                        {
                            var request = new JSONObject();

                            request.put("Operation", "deposit");

                            System.out.print("Enter amount: ");

                            var value = "";
                            while ((value = reader.readLine()).isBlank()
                                    || !Pattern.matches("\\d+",value))
                            {
                                System.out.print("Please enter proper amount: ");
                            }

                            request.put("Amount", value);

                            request.put("AccountID", accountID);

                            socket.send(request.toString());

                            var response = new JSONObject(new String(socket.recv()));

                            System.out.println("-------------------------------------------------");

                            System.out.println(response.get("Message"));

                            System.out.println("\nUpdated Balance: " + response.get("Updated Balance"));

                            System.out.println("-------------------------------------------------");

                        } catch (Exception exception)
                        {
                            exception.printStackTrace();
                        }
                    }
                    case 3 ->
                    // check balance
                    {
                        try
                        {
                            var request = this.getBalance(accountID);

                            //send request to server...
                            socket.send(request.toString());

                            // response from server...
                            var response = new JSONObject(new String(socket.recv()));

                            System.out.println("-------------------------------------------------");

                            System.out.println("Current Balance: " + response.get("Balance"));

                            System.out.println("-------------------------------------------------");

                        } catch (Exception exception)
                        {
                            exception.printStackTrace();
                        }
                    }
                    case 4 ->
                    // transfer
                    {
                        try
                        {
                            var request = new JSONObject();

                            System.out.print("Enter recipient Account Number: ");

                            var value = "";
                            while ((value = reader.readLine()).isBlank()
                                    || !Pattern.matches("\\d{5}",value))
                            {
                                System.out.print("Please enter proper account number: ");
                            }

                            request.put("Operation", "Transfer");

                            request.put("Recipient AccountID", value);

                            request.put("AccountID", accountID);

                            System.out.print("Enter amount to transfer: ");

                            while ((value = reader.readLine()).isBlank()
                                    || !Pattern.matches("\\d+",value))
                            {
                                System.out.print("Please enter proper amount: ");
                            }

                            request.put("Amount", value);

                            // send transfer request to server
                            socket.send(request.toString());

                            var response = new JSONObject(new String(socket.recv()));

                            if (response.get("Status").toString().equals("ok"))
                            {
                                System.out.println("-------------------------------------------------");

                                System.out.println(response.get("Message"));

                                System.out.println("\nUpdated Balance: " + response.get("Updated Balance"));

                                System.out.println("-------------------------------------------------");
                            } else
                            {
                                System.out.println("-------------------------------------------------");

                                System.out.println(response.get("Message"));

                                System.out.println("-------------------------------------------------");
                            }
                        } catch (Exception exception)
                        {
                            exception.printStackTrace();
                        }
                    }
                    default -> System.out.println("Invalid choice ");
                }
            }
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
