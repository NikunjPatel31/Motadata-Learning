package org.example.JConsole;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadDump
{

    public static void main(String[] args)
    {

        while (true)
        {
            new Timer().scheduleAtFixedRate(new TimerTask()
            {
                @Override
                public void run()
                {

                    while (true)
                    {
                        for (int i = 0; i < 1000; i++)
                        {
//                            System.out.println(Thread.currentThread().getName() + " - " + i);
                        }
                    }
                }
            }, 0, 1000);
        }
    }

}
