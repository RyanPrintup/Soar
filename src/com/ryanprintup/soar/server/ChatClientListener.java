package com.ryanprintup.soar.server;

import com.ryanprintup.soar.Soar;
import com.ryanprintup.soar.utils.Debug;
import java.io.DataInputStream;
import java.io.IOException;

public class ChatClientListener extends Thread
{
    private ChatClient client;
    private DataInputStream inStream;
    
    private boolean running = false;
    
    public ChatClientListener(ChatClient client, DataInputStream inStream)
    {
        this.client = client;
        this.inStream = inStream;
    }
    
    @Override
    public void run()
    {
        running = true;
        
        while (running) {
            try {
                byte[] buffer = new byte[1024];
                inStream.read(buffer);
                
                Soar.getServer().handleIncomingPacket(client, buffer);
            } catch (IOException e) {
                if (Debug.ERRORS) Debug.log("Couldn't recieve packet from user: " + e);
            }
        }
        
        running = false;
    }
    
    public void stopListening()
    {
        running = false;
        interrupt();
    }
}
