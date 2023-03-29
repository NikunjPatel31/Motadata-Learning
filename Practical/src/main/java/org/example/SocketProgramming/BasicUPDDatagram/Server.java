package org.example.SocketProgramming.BasicUPDDatagram;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server
{
    public static void main(String[] args)
    {
        DatagramSocket socket = null;

        try
        {
            socket = new DatagramSocket(6000);

            //socket.connect(InetAddress.getByName("localhost"), 6000);

            byte[] readBytes = new byte[1024];

            DatagramPacket packet = new DatagramPacket(readBytes, 1024);

            socket.receive(packet);

            String message = new String(packet.getData(), 0, packet.getLength());

            System.out.println("Message: "+message);


        }
        catch (Exception exception)
        {
            System.out.println("Exception: main: "+exception);
        }
    }
}