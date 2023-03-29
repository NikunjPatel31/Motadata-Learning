package org.example.SocketProgramming;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class URLConnectionExample {
    public static void main(String[] args){
        try{

            InetAddress ip =  Inet4Address.getByName("www.javatpoint.com");
            InetAddress ip1[] = InetAddress.getAllByName("www.javatpoint.com");
            byte addr[]={72, 3, 2, 12};
            System.out.println("ip : "+ip);
            System.out.print("\nip1 : "+ip1);

            for (InetAddress item : ip1)
            {
                System.out.println("item: "+item);
            }

            InetAddress ip2 =  InetAddress.getByAddress(addr);
            System.out.print("\nip2 : "+ip2);
            System.out.print("\nAddress : " + Arrays.toString(ip.getAddress()));
            System.out.print("\nHost Address : " +ip.getHostAddress());
            System.out.print("\nisAnyLocalAddress : " +ip.isAnyLocalAddress());
            System.out.print("\nisLinkLocalAddress : " +ip.isLinkLocalAddress());
            System.out.print("\nisLoopbackAddress : " +ip.isLoopbackAddress());
            System.out.print("\nisMCGlobal : " +ip.isMCGlobal());
            System.out.print("\nisMCLinkLocal : " +ip.isMCLinkLocal());
            System.out.print("\nisMCNodeLocal : " +ip.isMCNodeLocal());
            System.out.print("\nisMCOrgLocal : " +ip.isMCOrgLocal());
            System.out.print("\nisMCSiteLocal : " +ip.isMCSiteLocal());
            System.out.print("\nisMulticastAddress : " +ip.isMulticastAddress());
            System.out.print("\nisSiteLocalAddress : " +ip.isSiteLocalAddress());
            System.out.print("\nhashCode : " +ip.hashCode());
            System.out.print("\n Is ip1 == ip2 : " +ip.equals(ip2));






            InetAddress inetAddress = InetAddress.getByName("localhost");
            System.out.println(inetAddress.getHostAddress());
            URL url=new URL("https://www.google.com");
            HttpURLConnection urlcon= (HttpURLConnection) url.openConnection();
            urlcon.getErrorStream();
            InputStream stream=urlcon.getInputStream();
            int i;
            while((i=stream.read())!=-1){
                System.out.print("" +(char)i);
            }
        }catch(Exception e){System.out.println(e);}
    }
}