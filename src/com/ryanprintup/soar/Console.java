package com.ryanprintup.soar;

public class Console
{    
    public void write(String message)
    {
        System.out.println(message);
    }
    
    public void write(String messages[])
    {
        for (String m : messages) {
            write(messages);
        }
    }

    public void error(String error)
    {
        write("[ERROR] " + error);
    }

    public void info(String error)
    {
        write("[INFO] " + error);
    }

    public void debug(String error)
    {
        write("[DEBUG] " + error);
    }
}
