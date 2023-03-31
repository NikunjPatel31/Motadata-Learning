package org.example.SocketProgramming;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver {

    public static void main(String[] args) {

        try
        {
            InetAddress ip = InetAddress.getByName("10.20.40.197");

            DatagramSocket datagramSocket = new DatagramSocket(3000, ip);

            byte[] buf = new byte[1024];

            DatagramPacket datagramPacket = new DatagramPacket(buf, 1024);

            datagramSocket.receive(datagramPacket);

            String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

            System.out.println(str);

            datagramSocket.close();
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }

}
