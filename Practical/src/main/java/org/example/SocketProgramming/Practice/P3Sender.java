package org.example.SocketProgramming.Practice;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class P3Sender {

    public static void main(String[] args) {
        try
        {
            DatagramSocket ds = new DatagramSocket();
            String str = "Welcome java";

            InetAddress ip = InetAddress.getByName("0.0.0.0");

            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 6000);

            ds.send(dp);

            ds.close();
        } catch (Exception exception)
        {
            System.out.println("Exception: "+exception);
        }
    }

}

