package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class Main
{

    // Fixed Project name constant string
    public static final String PROJECT_NAME = "Network Monitoring System";

    // IP Address Octet regex - (0-255)
    public static final String ipAddOctetRegex = "(?:1?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";

    // Converted HashMap -> ConcurrentHashMap: while iterating for polling and adding profile to provision list - concurrent update error occurred
    // Map for profiles added for discovery
    private static final ConcurrentHashMap<String, String> discoverProfiles = new ConcurrentHashMap<>();

    // Map for polling profiles
    private static final ConcurrentHashMap<String, String> provisionedProfile = new ConcurrentHashMap<>();

    // Reader to read user Input
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Constant file extension for all log file
    final static String ext = ".log";

    // Backend - constant polling to provisioned profiles
    private static void startPolling()
    {
        while (true)
        {

            Set<Map.Entry<String, String>> entries = provisionedProfile.entrySet();

            entries.forEach((item) -> {

                // Pinging to each provisioned profiles - ping -c 1 <ip-address>
                ArrayList<String> provisionOutput = buildProcess("ping", "-c", "1", item.getValue());

                // Checking if ping request valid or not - if not connected to Wi-Fi
                if(provisionOutput.size()>2)
                {
                    // Extracting transmitted packets from ping output
                    String transmittedPackets = provisionOutput.get(provisionOutput.size() - 2).split(",")[0].split(" ")[0];

                    // Extracting received packets from ping output
                    String receivedPackets = provisionOutput.get(provisionOutput.size() - 2).split(",")[1].split(" ")[1];

                    // Input to file for each provisioned profile
                    String inputToFile = item.getKey() + "[" + item.getValue() + "] "
                            + (transmittedPackets.equals(receivedPackets) ? "(UP)" : "(DOWN)") + " - "
                            + provisionOutput.get(provisionOutput.size() - 2) + provisionOutput.get(provisionOutput.size() - 1) + "\n";

                    try
                    {
                        // Writing to file
                        FileWriter fw = new FileWriter( item.getKey() + ext, true);

                        fw.write(inputToFile);

                        fw.close();

                    }
                    catch (IOException ioe)
                    {
                        System.out.println(ioe.getMessage());
                    }
                }else{
                    System.out.println("Error occurred while polling.");
                    System.exit(0);
                }
            });
            try
            {
                // waiting for 10 seconds before making next ping request
                Thread.sleep(1000 * 10);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.getMessage());
            }
        }
    }

    // Destroying all the files created for provisioned profile
    private static boolean destroyDanglingFiles()
    {
        // Flag to check if all deleted
        boolean allDeleted = true;

        // Deleting all the provisioned files created
        Set<String> profiles = provisionedProfile.keySet();
        for (String profile : profiles)
        {
            File file = new File(profile + ext);
            if (!file.delete())
            {
                allDeleted = false;
            }
        }
        return allDeleted;
    }

    // Processing Operating System Commands
    private static ArrayList<String> buildProcess(String... commands)
    {
        ArrayList<String> processOutput = new ArrayList<>();
        try
        {
            // Running command using process builder
            ProcessBuilder processBuilder = new ProcessBuilder(commands);

            Process process = processBuilder.start();

            BufferedReader readProcessOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Collecting output of ping
            String str;
            while ((str = readProcessOutput.readLine()) != null)
            {
                processOutput.add(str);
            }

        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
        return processOutput;
    }

    // Check ping response and provision if allowed by the user
    private static void checkAndProvision(String packetStats, String discoveryName, String ipAddress)
    {
        // Extracting transmitted packets from ping output
        String transmittedPackets = packetStats.split(",")[0].split(" ")[0];

        // Extracting transmitted packets from ping output
        String receivedPackets = packetStats.split(",")[1].split(" ")[1];

        System.out.println("Transmitted packets : " + transmittedPackets + "\nReceived packets : " + receivedPackets);

        // Checking if device is discoverable or not
        if (transmittedPackets.equals(receivedPackets))
        {

            // Asking for adding discovery profile to provision profile list
            System.out.println(discoveryName + "[" + ipAddress + "] : UP");
            System.out.print("Would you like to provision?(yes/no): ");

            try
            {
                if (br.readLine().equals("yes"))
                {
                    provisionedProfile.put(discoveryName, ipAddress);
                    System.out.println(discoveryName + "[" + ipAddress + "] added to provision list");
                }
            }
            catch (IOException ioe)
            {
                System.out.println(ioe.getMessage());
            }

        }
        else
        {
            System.out.println(discoveryName + "[" + ipAddress + "] : DOWN");
        }
    }

    // Making discovery for new profile
    public static void discover()
    {
        try
        {
            while (true)
            {
                // taking discovery name and ip address to discover the device
                System.out.print("----------------------------------------------\nEnter your discovery name(should be unique): ");
                String discoveryName = br.readLine();

                // Flag to check if device is discovered - reachable or unreachable.
                boolean discovered = false;

                if (!discoveryName.equals("") && discoverProfiles.get(discoveryName) == null)
                {
                    String ipAddress;
                    while (true)
                    {

                        System.out.print("Enter IP address (ipv4): ");

                        ipAddress = br.readLine();

                        // regex check for IP address 0.0.0.0 - 255.255.255.255
                        if (ipAddress.split(".").length != 4 && !Pattern.matches("^" + ipAddOctetRegex + "." + ipAddOctetRegex + "." + ipAddOctetRegex + "." + ipAddOctetRegex + "$", ipAddress))
                        {
                            System.out.println("Invalid ip address format");
                        }
                        else
                        {

                            // Adding profile for discovery
                            discoverProfiles.put(discoveryName, ipAddress);

                            System.out.println("Profile with discovery name " + discoveryName +
                                    " has been added successfully.\nStarting to discover the machine...");


                            // pinging to discovery ip address
                            ArrayList<String> outputOfProcess = buildProcess("ping", "-c", "2", ipAddress);

                            // Extracting ping output statistics
                            String packetStats = outputOfProcess.get(outputOfProcess.size() - 2);

                            // Check ping statistics - if sent packet = received packet
                            // Ask for profile provision
                            checkAndProvision(packetStats, discoveryName, ipAddress);

                            discovered = true;
                        }
                        if(discovered){
                            break;
                        }
                    }
                }
                else
                {
                    System.out.println("Profile with same discovery name already exits, please enter different discovery name.");
                }
                if (discovered)
                {
                    break;
                }
            }
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
    }

    // polling provisioned profiles
    private static void poll()
    {
        // manual polling to provisioned profile
        Set<Map.Entry<String, String>> profiles = provisionedProfile.entrySet();

        System.out.println("Available profiles to poll");

        profiles.forEach(item -> {
            System.out.print(item.getKey() + " [" + item.getValue() + "]\t");
        });

        System.out.print("\nEnter profile name to poll: ");
        try
        {
            // Printing log file for provisioned profile
            FileReader fr = new FileReader(br.readLine()+ext);

            int i;

            while ((i = fr.read()) != -1)
            {
                System.out.print((char) i);
            }
            fr.close();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
    }

    public static void main(String[] args)
    {
        // Creating a thread for backend polling of provisioned profiles
        Thread t1 = new Thread(Main::startPolling);

        t1.start();

        System.out.println("Welcome to " + PROJECT_NAME);
        try
        {
            while (true)
            {
                // taking users input for menu
                System.out.print("------------------------------------\n0. Exit\n1. Discover\n2. Poll\nEnter your choice: ");

                switch (br.readLine())
                {
                    case "0" ->
                    {
                        System.out.println("================================================");
                        System.out.println("Thank you for using " + PROJECT_NAME);
                        System.out.println(destroyDanglingFiles() ? "All resources are closed" : "Error occurred: unable to close all resources");
                        System.exit(0);
                    }

                    case "1" ->{
                        System.out.println("================================================");
                        discover();
                    }

                    case "2" ->{
                        System.out.println("================================================");
                        poll();
                    }

                    default -> System.out.println("wrong input please enter again");
                }
            }
        }
        catch (IOException ioe)
        {
            System.out.println("Error occurred\n" + ioe.getMessage());
        }
    }
}