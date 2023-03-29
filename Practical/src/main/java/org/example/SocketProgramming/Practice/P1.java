package org.example.SocketProgramming.Practice;

import java.io.DataOutputStream;
import java.net.Socket;

public class P1 {
    public static void main(String[] args) {
        try
        {
            Socket socket = new Socket("localhost", 6666);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            for (int i = 0; i < 5; i++)
            {
                dataOutputStream.writeUTF("hee");

                dataOutputStream.flush();
            }

            dataOutputStream.close();

            socket.close();

        } catch (Exception exception)
        {
            System.out.println("Exception: "+exception);
        }
    }
}
