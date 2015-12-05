package com.ryanprintup.soar.server;

import com.ryanprintup.soar.utils.Debug;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient
{
    private Socket socket;
    private DataInputStream inStream;
    private DataOutputStream outStream;
    
    private ChatClientListener listenerThread;
    
    public ChatClient(Socket socket)
    {
        this.socket = socket;
        
        try {
            this.inStream  = new DataInputStream(socket.getInputStream());
            this.outStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            if (Debug.ERRORS) Debug.log("Failed to get input and output stream: " + e);
        }
        
        listenerThread = new ChatClientListener(this, inStream);
        listenerThread.start();
    }
    
    public void send(byte[] packet) throws IOException
    {
        outStream.write(packet);
    }
}
