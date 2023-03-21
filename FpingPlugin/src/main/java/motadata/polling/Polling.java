package motadata.polling;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.*;

public class Polling
{
    public static Map<String, String> pollingMap = new HashMap<>();

    private static void poll(List<String> IPAddressList)
    {

        if (IPAddressList.size() == 4)
        {

            return;
        }

        ProcessBuilder processBuilder = null;

        BufferedReader reader = null;

        try
        {

            processBuilder = new ProcessBuilder(IPAddressList);

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            if (process.waitFor() == 0)
            {

                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                for (var result = reader.readLine(); result != null; result = reader.readLine())
                {

                    var IPAddress = result.split(":")[0];

                    try
                    {
                        result = pollingMap.get(IPAddress.trim()) + " - " + IPAddress + result.split(":")[1]+"\n";

                        var fileInput = new FileWriter(pollingMap.get(IPAddress.trim())+".log", true);

                        fileInput.write(result);

                        fileInput.flush();

                        fileInput.close();

                    }
                    catch (Exception exception)
                    {

                        System.out.println("Exception in polling: ");

                        exception.printStackTrace();

                    }
                    finally
                    {
                        if (reader != null)
                        {
                            reader.close();
                        }

                        if (process != null)
                        {
                            process.destroy();
                        }

                    }
                }
            }
        }
        catch (Exception exception)
        {

            System.out.println("Exception Polling: " + exception);

        }
    }

    public static void addToPool(String discoveryName, String IPAddress)
    {

        try
        {
            pollingMap.put(IPAddress, discoveryName );
        }
        catch (Exception exception)
        {
            System.out.println("Exception in polling: " + exception);
        }

    }

    public static void performPolling()
    {
        try
        {

            Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask()
            {
                @Override
                public void run()
                {

                    List<String> IPAddressList = new ArrayList<>();

                    IPAddressList.add("fping");

                    IPAddressList.add("-c");

                    IPAddressList.add("3");

                    IPAddressList.add("-q");

                    IPAddressList.addAll(pollingMap.keySet());

                    poll(IPAddressList);
                }
            }, 0,5000);

        }
        catch (Exception exception)
        {
            System.out.println("Exception in polling: " + exception);
        }
    }
}