package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
public class Ping {
    private static String getIPInputAndCalculateOutput() {
        StringBuilder commandOutput = new StringBuilder();
        BufferedReader reader = null;
        BufferedReader stdError = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter number of Ip Address to ping :");
            int noOfIpAddresses;
            noOfIpAddresses = Integer.parseInt(reader.readLine());
            StringBuilder ipAddresses = new StringBuilder();
            System.out.println("Please enter " + noOfIpAddresses + " IP Addresses that you want to ping : ");
            for (int index = 0; index < noOfIpAddresses; index++) {
                ipAddresses.append(reader.readLine());
                ipAddresses.append(" ");
            }
            System.out.println("\nPlease wait, we are going to ping your IP Addresses......\n");
            String command = "fping -c 3 -q " + ipAddresses;
            ProcessBuilder processBuilder = new ProcessBuilder(new ArrayList<>(Arrays.asList("bash", "-c", command)));
            Process pingProcess;
            pingProcess = processBuilder.start();
            stdError = new BufferedReader(new InputStreamReader(pingProcess.getErrorStream()));
            String outputScanner;
            while ((outputScanner = stdError.readLine()) != null) {
                commandOutput.append(outputScanner);
                commandOutput.append("\n");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
                assert stdError != null;
                stdError.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return commandOutput.toString();
    }
    private static void isHostUp(String pingOutput) {
        for (int index = 0; index < pingOutput.split("\n").length; index++) {
            String[] filteredResult = pingOutput.split("\n")[index].split(":")[1].split("=")[1].split("/");
            if (filteredResult[0].trim().equals(filteredResult[1].trim()) && filteredResult[2].charAt(0) == 48) {
                System.out.println(pingOutput.split("\n")[index].split(":")[0].trim() + " is Up (Active)");
            } else {
                System.out.println(pingOutput.split("\n")[index].split(":")[0].trim() + " is Down (Inactive)");
            }
        }
    }
    public static void main(String[] args) {
        String pingOutput = getIPInputAndCalculateOutput();
        isHostUp(pingOutput);
    }
}