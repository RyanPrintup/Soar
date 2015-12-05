package com.ryanprintup.soar.client;

import com.ryanprintup.soar.net.packets.MessagePacket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable
{
    private Socket socket;
    private String host;
    private int port;

    private DataInputStream inStream;
    private DataOutputStream outStream;
    
    private String name;

    public Client(String address, int port)
    {
        this.host = address;
        this.port = port;
    }

    public boolean connect()
    {
        System.out.println("Connecting to server");
        try {
            socket = new Socket(host, port);

            inStream = new DataInputStream(socket.getInputStream());
            outStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error trying to connect to server: " + e);
            return false;
        }
        
        new Thread(this).start();
        
        return true;
    }
    
    public void run()
    {
        
    }
    
    public void send(String message) throws IOException
    {
        MessagePacket packet = new MessagePacket(name, message);
        //send(PacketManager.createPacket(PacketTypes.MESSAGE_PACKET, packet.getBuffer()));
    }
    
    public void send(byte[] data) throws IOException
    {
        outStream.write(data);
    }

    private void recieve()
    {
        byte[] data = null;

        try {
            int length = inStream.readInt();
            data = new byte[length];

            if (length > 0) {
                inStream.readFully(data);
            }
        } catch (IOException e) {

        }
    }
}
