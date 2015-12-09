package com.ryanprintup.soar.server;

import com.ryanprintup.soar.Soar;
import com.ryanprintup.soar.utils.Debug;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient implements Runnable
{
    private Socket socket;
    private DataInputStream inStream;
    private DataOutputStream outStream;

    private String name;
    private long id;

    private boolean running;
    private Thread recieveThread;
    
    public ChatClient(Socket socket)
    {
        this.socket = socket;
        
        try {
            this.inStream  = new DataInputStream(socket.getInputStream());
            this.outStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            if (Debug.ERRORS) Debug.log("Failed to get input and output stream: " + e);
        }

        recieveThread = new Thread(this);
    }

    public byte[] recieve() throws IOException
    {
        byte[] buffer = new byte[2014];
        inStream.read(buffer);

        return buffer;
    }

    public void send(byte[] packet) throws IOException
    {
        outStream.write(packet);
    }

    public void startRecieveLoop()
    {
        running = true;
        recieveThread.start();
    }

    @Override
    public void run()
    {
        while (running) {
            try {
                byte[] packet = recieve();
                Soar.getServer().handleIncomingPacket(this, packet);
            } catch (IOException e) {
                if (Debug.ERRORS) Debug.log("Failed to recieve message from [" + id + "] " + name + ": " + e);
            }
        }
    }

    public void stopRecieveLoop()
    {
        running = false;
        recieveThread.interrupt();
    }
}
