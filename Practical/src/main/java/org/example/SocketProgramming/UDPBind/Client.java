package org.example.SocketProgramming.UDPBind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class Client
{
    public static void main(String[] args)
    {
        DatagramSocket socket = null;

        BufferedReader reader = null;

        try
        {
            socket = new DatagramSocket();

            InetAddress inetAddress = InetAddress.getLocalHost();

            socket.bind(new InetSocketAddress(inetAddress, 6000));


        }
        catch (Exception exception)
        {
            System.out.println("Exception: main: "+exception);

            exception.printStackTrace();
        }
    }
//    public static void main(String[] args)
//    {
//        DatagramSocket datagramSocket = null;
//
//        BufferedReader bufferedReader = null;
//
//        try
//        {
//            datagramSocket = new DatagramSocket();
//
//            InetAddress inetAddress = InetAddress.getByName("localhost");
//
//            int port = 6000;
//
//            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
//
//            //datagramSocket.bind(socketAddress);
//
//            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//            String message = bufferedReader.readLine();
//
//            DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(), inetAddress, 6000);
//
//            datagramSocket.send(datagramPacket);
//
//            System.out.println("Message sent");
//        }
//        catch (Exception exception)
//        {
//            System.out.println("Exception: main: "+exception);
//
//            exception.printStackTrace();
//        }
//    }
}
