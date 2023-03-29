package org.example.SocketProgramming.UDPBind;

import java.net.*;


/**
    This program will generate exception when datagramSocket.bind() is called.
    because when we called default constructor of DatagramSocket it got connected to localhost.
 */

public class Server
{
    public static void main(String[] args)
    {
        DatagramSocket datagramSocket = null;

        try
        {
            datagramSocket = new DatagramSocket(null);

            InetAddress inetAddress = InetAddress.getByName("localhost");

            int port = 6000;

            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);

            datagramSocket.bind(socketAddress);

            System.out.println("Port number: "+datagramSocket.getPort());

            byte[] bytes = new byte[1024];

            DatagramPacket datagramPacket = new DatagramPacket(bytes, 1024);

            datagramSocket.receive(datagramPacket);

            String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

            System.out.println("Message: "+message);
        }
        catch (Exception exception)
        {
            System.out.println("Exception: main: "+exception);

            exception.printStackTrace();
        }
        finally
        {
            //System.out.println(datagramSocket.getPort());
        }
    }
}
