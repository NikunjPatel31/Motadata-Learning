package org.example.Model;

import motadata.CommanUtils;

import java.io.Serializable;
import java.util.regex.Pattern;

public class DiscoveryProfile implements Serializable
{
    String IPAddress;

    String discoveryName;

    DiscoveryProfile()
    {

    }

    DiscoveryProfile(String IPAddress, String discoveryName) throws Exception
    {

        this.IPAddress = IPAddress;

        this.discoveryName = discoveryName;

        if (validateIP())
        {
            this.IPAddress = null;

            throw new Exception();
        }
    }

    @Override
    public String toString()
    {

        return this.discoveryName + " -> " + this.IPAddress;

    }

    @Override
    public boolean equals(Object obj)
    {

        return this.IPAddress.equals(((DiscoveryProfile) obj).IPAddress) && this.discoveryName.equals(((DiscoveryProfile) obj).discoveryName);

    }

    public String getIPAddress()
    {

        return IPAddress;
    }

    public void setIPAddress(String IPAddress)
    {

        this.IPAddress = IPAddress;
    }

    public String getDiscoveryName()
    {

        return discoveryName;
    }

    public void setDiscoveryName(String discoveryName)
    {

        this.discoveryName = discoveryName;
    }

    private boolean validateIP()
    {

        try
        {
            return this.IPAddress.split(".").length == 4 && !Pattern.matches("^" + CommanUtils.ipAddOctetRegex + CommanUtils.ipAddOctetRegex + CommanUtils.ipAddOctetRegex + CommanUtils.ipAddOctetRegex + "$", this.IPAddress);
        } catch (Exception exception)
        {
            System.out.println(exception);
        }

        return false;
    }
}
