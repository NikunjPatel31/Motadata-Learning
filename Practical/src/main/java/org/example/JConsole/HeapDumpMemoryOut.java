package org.example.JConsole;

import java.util.ArrayList;
import java.util.List;

public class HeapDumpMemoryOut
{

    public static void main(String[] args)
    {

        List<Integer> list = new ArrayList<>();

        while (true) {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }

            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }

            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }

            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }

            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }

            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }

            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }

            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }

        }
    }
}
