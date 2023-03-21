package org.example.JConsole;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class HeapDumpPractical
{
    public static void main(String[] args) throws Exception
    {

        List<Integer> list = new ArrayList<>();

        while (true)
        {
            new Timer().scheduleAtFixedRate(new TimerTask()
            {

                @Override
                public void run()
                {

                    try
                    {

                        while (true)
                        {
                            for (int j = 0; j < 1000; j++)
                            {

                                while (true)
                                {
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);
                                        Thread.sleep(1);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);
                                        Thread.sleep(1);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);
                                        Thread.sleep(1);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);
                                        //            Thread.sleep(1);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }
                                    for (int i = 0; i < 100000; i++)
                                    {
                                        list.add(i);

                                    }


                                }
                            }

                        }


                    } catch (Exception e)
                    {

                    }
                }


            }, 0, 1000);
        }
    }
}

