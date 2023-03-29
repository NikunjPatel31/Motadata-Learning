package org.example.SocketProgramming.Practice;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class P2Client {
    public static void main(String[] args) {
        try
        {
            InetAddress ip = InetAddress.getByName("0.0.0.0");

            DatagramSocket ds = new DatagramSocket(6000, ip);

            byte[] buf = new byte[7];

            DatagramPacket dp = new DatagramPacket(buf, 7);

            ds.receive(dp);

            String str = new String(dp.getData(), 0, dp.getLength());

            System.out.println(str);

            ds.close();
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);
        }
    }
}
