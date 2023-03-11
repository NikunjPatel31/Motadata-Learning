package org.example;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;

import com.jcraft.jsch.ChannelExec;

import com.jcraft.jsch.JSch;

import com.jcraft.jsch.Session;

public class SSHPlugin
{

    private static String ip_address;

    private static String user_name;

    private static String user_password;

    private static String command;

    public void setData() throws IOException
    {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter IP address to connect");

        ip_address = "10.20.40.24"; //bfr.readLine();

        System.out.println("Enter user name to connect");

        user_name = "sankalp"; //bfr.readLine();

        System.out.println("Enter user password to connect");

        user_password = "Mind@123"; //bfr.readLine();

        System.out.println("Enter command to run");

        command = "vmstat; mpstate; iostat"; //bfr.readLine();

    }

    public static void main(String[] args) throws IOException {

        SSHPlugin ssh = new SSHPlugin();

        BufferedReader bReader = null;

        if(ssh != null)
        {
            ssh.setData();
        }

        try
        {
            java.util.Properties config = new java.util.Properties();

            if(config != null)
            {
                config.put("StrictHostKeyChecking", "no");
            }

            JSch jsch = new JSch();

            if(jsch != null)
            {
                Session session = jsch.getSession(user_name, ip_address, 22);

                if(session != null)
                {
                    session.setPassword(user_password);

                    session.setConfig(config);

                    session.connect();
                }

                System.out.println("Connected to the Remote PC ");

                Channel channel = session.openChannel("exec");

                ((ChannelExec)channel).setCommand(command);

                channel.setInputStream(null);

                InputStream instream = channel.getInputStream();

                channel.connect();

                bReader = new BufferedReader(new InputStreamReader(instream));

                String command_output;

                while((command_output = bReader.readLine()) != null)
                {
                    System.out.println(command_output);
                }
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        finally
        {
            try
            {
                if(bReader==null)
                {
                    bReader.close();
                }
            }
            catch (Exception e)
            {
                System.out.println("Buffer reader doesn't close");

                System.out.println(e);
            }
        }
    }
}