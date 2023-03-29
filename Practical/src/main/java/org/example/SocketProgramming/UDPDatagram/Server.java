package org.example.SocketProgramming.UDPDatagram;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server
{
    public static void main(String[] args)
    {
        Server.fun();
        DatagramSocket datagramSocket = null;

        try
        {
            datagramSocket = new DatagramSocket(6000);

            byte[] receiveByte = new byte[1024];

            DatagramPacket datagramPacket = new DatagramPacket(receiveByte, receiveByte.length);

            datagramSocket.receive(datagramPacket);

            String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

            System.out.println("Message received: "+message);
        }
        catch (Exception exception)
        {
            System.out.println("Exception: main: "+exception);
        }
    }

    private static void fun(){}
}
