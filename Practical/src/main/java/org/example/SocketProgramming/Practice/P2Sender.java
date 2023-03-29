package org.example.SocketProgramming.Practice;

import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class P2Sender {
    public static void main(String[] args) {
        try
        {
            DatagramSocket ds = new DatagramSocket();
            String str = "Welcome java";

            InetAddress ip = InetAddress.getByName("10.20.40.158");

            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 6000);

            ds.send(dp);

            ds.close();
        } catch (Exception exception)
        {
            System.out.println("Exception: "+exception);
        }
    }
}
