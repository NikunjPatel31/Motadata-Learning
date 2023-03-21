package org.example.SocketProgramming.ChatApplication;

import org.example.SocketProgramming.RemoteSocket.Const;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        try
        {
            socket = new Socket("localhost", Constant.PORT_NUMBER);

            System.out.println("Client:");

            ReadMessage read = new ReadMessage(socket, Constant.CLIENT);

            WriteMessage write = new WriteMessage(socket, Constant.CLIENT);

            read.start();

            write.start();

            read.join();

            write.join();

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);
            exception.printStackTrace();
        }
        finally
        {
            if (socket != null)
            {
                socket.close();
            }
        }

    }
}
