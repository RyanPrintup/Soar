package com.ryanprintup.soar;

import com.ryanprintup.soar.server.Server;

/**
 * Core class handling singleton
 */
public final class Soar
{
    private static Server _instance;
    private static int port = -1;
    
    public Soar()
    {
    }
    
    public static Server getServer()
    {
        if (_instance == null) {
            _instance = new Server(port);
        }
        
        return _instance;
    }
    
    public static void setPort(int port)
    {
        if (port == -1) {
            port = port;
        }
    }
}
