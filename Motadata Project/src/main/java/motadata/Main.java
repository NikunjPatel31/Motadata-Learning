package motadata;

import motadata.discovery.Discovery;
import motadata.polling.Polling;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args)
    {

        Polling.performPolling();

        Discovery discovery = new Discovery();

        try
        {
            while (true)
            {
                System.out.println("Enter your choice: ");

                System.out.println("1. Discovery");

                System.out.println("2. Polling");

                System.out.print("Choice: ");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                switch (bufferedReader.readLine())
                {
                    case "1":
                    {
                        // discovery

                        var discoveryName = "";

                        var IPAddress = "";

                        while (true)
                        {

                            System.out.println("\n--------------------------------------------------");

                            System.out.print("Enter Discovery name address: ");

                            discoveryName = bufferedReader.readLine();

                            if (discovery.isUniqueDiscoveryName(discoveryName))
                            {
                                System.out.println("Please enter unique name");
                                continue;
                            }
                            break;

                        }

                        while (true)
                        {

                            System.out.print("Enter IP Address: ");

                            IPAddress = bufferedReader.readLine();

                            if (discovery.validateIP(IPAddress))
                            {
                                break;

                            } else {
                                // IP address validation failed

                                System.out.println("Please enter valid IP address");
                            }
                        }

                        if (discovery.isDiscoverable(discoveryName, IPAddress))
                        {
                            // discovery is successful

                            System.out.println("\n-------------------------------------------");

                            while (true)
                            {
                                System.out.println("Device is reachable");

                                System.out.println("Do you want to start provision ? (yes/no) ");

                                if (bufferedReader.readLine().trim().toLowerCase().equals("yes"))
                                {
                                    // add for provision

                                    Polling.addToPool(discoveryName, IPAddress);

                                    break;
                                } else if (!bufferedReader.readLine().trim().toLowerCase().equals("no"))
                                {
                                    // invalid

                                    System.out.println("Invalid");
                                } else {
                                    break;
                                }
                            }
                        } else
                        {
                            // discovery unsuccessful
                            System.out.println("Device is not reachable");
                        }

                        break;
                    }

                    default:
                        System.out.println("Invalid choice");
                }

            }
        } catch (Exception exception)
        {
            System.out.println(exception);
        }
    }
}