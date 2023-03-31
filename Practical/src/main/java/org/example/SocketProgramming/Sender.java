package org.example.SocketProgramming;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender
{
    public static void main(String[] args) {

        try
        {
            DatagramSocket datagramSocket = new DatagramSocket();
            String str = "Hello world...";

            InetAddress ip = InetAddress.getByName("0.0.0.0");

            DatagramPacket datagramPacket = new DatagramPacket(str.getBytes(), str.length(), ip, 3000);

            datagramSocket.send(datagramPacket);

            datagramSocket.close();
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }
}
