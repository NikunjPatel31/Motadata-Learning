package org.example.SocketProgramming.UPDConnect;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server
{
    public static void main(String[] args)
    {
        DatagramSocket datagramSocket = null;

        try
        {
            System.out.println("We are waiting for the message...");
            int port = 6000;

            InetAddress inetAddress = InetAddress.getByName("localhost");

            datagramSocket = new DatagramSocket(port, inetAddress);

            //datagramSocket.connect(inetAddress, port);

            byte[] readByte = new byte[1024];

            DatagramPacket datagramPacket = new DatagramPacket(readByte, 1024);

            datagramSocket.receive(datagramPacket);

            String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

            System.out.println("Message: "+message);

            System.out.println("isConnected(): "+datagramSocket.isConnected());
        }
        catch (Exception exception)
        {
            System.out.println("Exception: main: "+exception);

            exception.printStackTrace();
        }
    }
}
