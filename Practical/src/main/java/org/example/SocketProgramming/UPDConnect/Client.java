package org.example.SocketProgramming.UPDConnect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client
{
    public static void main(String[] args)
    {
        DatagramSocket socket = null;

        BufferedReader reader = null;

        try
        {
            InetAddress inetAddress = InetAddress.getByName("localhost");

            socket = new DatagramSocket();

            socket.connect(inetAddress, 6000);

            System.out.println("isConnect(): "+socket.isConnected());

            reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a message: ");

            String message = reader.readLine();

            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length());

            socket.send(packet);

            System.out.println("Message sent...!");
        }
        catch (Exception exception)
        {
            System.out.println("Exception: main: "+exception);
        }
    }
}
