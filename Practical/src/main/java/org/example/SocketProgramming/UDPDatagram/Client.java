package org.example.SocketProgramming.UDPDatagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        DatagramSocket datagramSocket = null;

        BufferedReader bufferedReader = null;

        try
        {
            InetAddress inetAddress = InetAddress.getByName("localhost");

            byte[] b = inetAddress.getAddress();

            System.out.println(b[0]+"."+b[1]+"."+b[2]+"."+b[3]);

            System.out.println(inetAddress.getAddress().length);

            System.out.println("Host Name: "+inetAddress.getHostName());

            datagramSocket = new DatagramSocket();

            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String message = bufferedReader.readLine();

            DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(), inetAddress, 6000);

            datagramSocket.send(datagramPacket);

            System.out.println("Message sent");
        } catch (Exception exception)
        {
            System.out.println("Exception: main: " + exception);
        } finally
        {
            if (bufferedReader != null)
            {
                bufferedReader.close();
            }

            if (datagramSocket != null)
            {
                datagramSocket.close();
            }
        }
    }
}
