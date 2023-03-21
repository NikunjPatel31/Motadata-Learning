package ChatApplication;

import ClientServer.Const;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteMessage extends Thread
{
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    String user;

    public WriteMessage(Socket socket, String user)
    {
        this.socket = socket;

        this.user = user;
    }

    @Override
    public void run()
    {
        startWriting();
    }

    void startWriting()
    {
        System.out.println(user + " writing mode enabled" + Const.NEW_LINE_SEPERATOR);

        try
        {
            writer = new PrintWriter(socket.getOutputStream());

            reader = new BufferedReader(new InputStreamReader(System.in));

            while (true)
            {
                String input = reader.readLine();

                writer.println(input);

                writer.flush();

            }

        }

        catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }
}
