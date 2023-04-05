import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class SubscriberClient
{
    public static void main(String[] args)
    {
        ZMQ.Socket subscriber = null;

        try (ZContext context = new ZContext())
        {

            subscriber = context.createSocket(SocketType.SUB);

// if (subscriber.connect("ipc://yash"))
            if (subscriber.connect("tcp://10.20.40.158:9999"))
            {
// subscriber.setMaxMsgSize(4);

                System.out.println("Connection success");

                System.out.println(subscriber.setMaxMsgSize(-1));

                System.out.println(subscriber.getMaxMsgSize());
            } else
            {
                System.out.println("Subscriber connection failed");

                System.exit(0);
            }

// subscriber.subscribe("btc");

// subscriber.subscribe("sol");
            subscriber.subscribe("count");

            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            while (!Thread.currentThread().isInterrupted())
            {
                String message = subscriber.recvStr(0);

// StringTokenizer tokenizer = new StringTokenizer(message, " ");
//
// String currency = tokenizer.nextToken();
//
// long price = Long.parseLong(tokenizer.nextToken());
//
// System.out.println(currency + " " + price);

// System.out.println(message);

                writer.write(message + "\n");

                writer.flush();
            }

            writer.close();

        } catch (Exception exception)
        {
            exception.printStackTrace();
        } finally
        {
            if (subscriber != null)
            {
                subscriber.close();
            }

        }
    }
}