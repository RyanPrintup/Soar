package com.ryanprintup.soar.server;

public class Console
{    
    public void write(String message)
    {
        System.out.println(message);
    }
    
    public void write(String message[])
    {
        for (String m : message) {
            write(message);
        }
    }
}
