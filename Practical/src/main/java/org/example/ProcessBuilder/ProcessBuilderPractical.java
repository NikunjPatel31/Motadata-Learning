package org.example.ProcessBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessBuilderPractical {

    /*
    This method will ask user to enter IP address and then execute fping command using process
    builder object.
    1. Ask user IP address
    2. validate IP address
    3. Create Process Builder using fping
    4. Store result inside bufferedReader object
    5. Store result inside Linkedlist object
     */
    private boolean getPingResponse(List<String> ipInputStringBuilder) throws IOException {
        BufferedReader reader = null;

        Process process = null;

        try {

            process = new ProcessBuilder("fping", "-q", "-c 3", ipInputStringBuilder.get(0)).start();

            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            var result = reader.readLine();

            if (result == null || result.isEmpty()) {

                System.out.println("Unable to get device status"+result);

                return false;

            }

            result = result.split(":")[1].trim();

            return result.charAt(16) == result.charAt(18) && result.charAt(20) == '0';

        } catch (Exception exception) {

            exception.printStackTrace();

        } finally {

            if (reader != null) {

                reader.close();

            }

            if (process != null) {

                process.destroy();

            }

        }

        return false;

    }

    public static void main(String[] args) throws IOException {


        var userInputBufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter an IP Address");

        var builder = new StringBuilder();

        builder.append(userInputBufferedReader.readLine());

        if (builder.isEmpty()) {

            // userInputBufferedReader is empty
            System.out.println("IP address not stored properly");

            return;
        }

        var ipList = new ArrayList<>(List.of(builder.toString().split(",")));

        ProcessBuilderPractical processBuilderPractical = new ProcessBuilderPractical();

        if (processBuilderPractical.getPingResponse(ipList)) {

            System.out.println(builder + " -> It is reachable");

        } else {

            System.out.println(builder + " -> It is not reachable");

        }
    }
}
