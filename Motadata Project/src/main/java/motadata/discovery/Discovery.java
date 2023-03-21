package motadata.discovery;

import motadata.CommanUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Discovery
{

    Map<String, String> discoveryMap;

    public Discovery()
    {

        discoveryMap = new HashMap<>();
    }

    public boolean isDiscoverable(String discoveryName, String IPAddress) throws IOException
    {

        if (discoveryMap.containsKey(discoveryName))
        {
            System.out.println("Please give unique name");
        }

        discoveryMap.put(discoveryName, IPAddress);

        BufferedReader reader = null;

        Process process = null;

        try
        {

            process = new ProcessBuilder(List.of("fping", "-q", "-c", "3", IPAddress)).start();

            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            var result = reader.readLine();

            if (result == null || result.isEmpty())
            {

                System.out.println("Unable to get device status" + result);

                return false;

            }

            result = result.split(":")[1].trim().split("=")[1].trim();  //regexxx

            return result.charAt(0) == result.charAt(2) && result.charAt(4) == '0';

        }
        catch (Exception exception)
        {
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

        return false;
    }

    public boolean isUniqueDiscoveryName(String discoveryName)
    {

        return discoveryMap.containsKey(discoveryName);
    }

    public boolean validateIP(String IPAddress)
    {

        try
        {
            return IPAddress.trim().split("\\.").length == 4 && Pattern.matches("^" + CommanUtils.ipAddOctetRegex + "." + CommanUtils.ipAddOctetRegex + "." + CommanUtils.ipAddOctetRegex + "." + CommanUtils.ipAddOctetRegex + "$", IPAddress);
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }

        return false;
    }
}
