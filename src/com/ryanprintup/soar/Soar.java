package com.ryanprintup.soar;

import com.ryanprintup.soar.server.Server;

public class Soar
{
    private static Server _instance;
    private static int _port = -1;
    
    public Soar()
    {
    }
    
    public static Server getServer()
    {
        if (_instance == null) {
            _instance = new Server(_port);
        }
        
        return _instance;
    }
    
    public static void setPort(int port)
    {
        if (_port == -1) {
            _port = port;
        }
    }
}
