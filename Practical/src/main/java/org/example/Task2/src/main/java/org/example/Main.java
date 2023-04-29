package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String json = new String(Files.readAllBytes(Paths.get("/home/nikunj/Downloads/Task 27.04.2023/JsonOutput/Palo Alto Networks.json")));

        JSONArray jsonArray = new JSONArray(json);

        for (Object group : jsonArray)
        {
            System.out.println(group);
        }

        System.out.println(jsonArray);
    }
}