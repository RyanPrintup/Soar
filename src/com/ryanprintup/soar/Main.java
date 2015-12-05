package com.ryanprintup.soar;

import com.ryanprintup.soar.client.Client;
import com.ryanprintup.soar.utils.Debug;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        try {
            Soar.setPort(8080);
            Soar.getServer().start();
            new Client("localhost", 8080).connect();
        } catch (IOException e) {
            if (Debug.ERRORS) Debug.log("Error starting server: " + e);
        }
    }
}
