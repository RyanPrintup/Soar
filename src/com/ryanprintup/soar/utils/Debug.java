package com.ryanprintup.soar.utils;

public final class Debug
{
    public static final boolean DEBUG = true;
    public static final boolean GENERAL = true;
    public static final boolean METHOD_CALLS = true;
    public static final boolean RETURN_VALUES = true;
    public static final boolean ERRORS = true;
    
    public static void log(String message)
    {
        if (DEBUG)
            System.out.println(message);
    }
}
