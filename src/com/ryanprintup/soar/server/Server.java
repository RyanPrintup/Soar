package com.ryanprintup.soar.server;

import com.ryanprintup.soar.handlers.ClientConnectHandler;
import com.ryanprintup.soar.net.PacketTypes;
import com.ryanprintup.soar.utils.Debug;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable
{
    private Thread serverThread;

    private ServerSocket serverSocket;
    private int port;
    
    private final int MAX_CLIENTS = 20;
    
    private ArrayList<ChatClient> clients = new ArrayList<ChatClient>();
    
    private Console console = new Console();

    public Server(int port)
    {
        this.port = port;
    }

    public void start() throws IOException
    {
        if (Debug.GENERAL) Debug.log("Starting Server");
        serverSocket = new ServerSocket(port);

        serverThread = new Thread(this);
        serverThread.start();
    }

    public void run()
    {
        Socket socket;

        while (true) {
            try {
                socket = serverSocket.accept();
                if (Debug.GENERAL) Debug.log("Client Connected");  
                
                addClient(socket);
            } catch (IOException e) {
                if (Debug.ERRORS) Debug.log("Client failed to connect");
            } 
        }
    }
    
    public void handleIncomingPacket(ChatClient sender, byte[] data)
    {
        /*BufferStream stream = new BufferStream(data);
        
        switch (stream.readUInt8()) {
            case PacketTypes.CLIENT_CONNECT_PACKET:
                new ClientConnectHandler().handle(stream);
                break;
            case PacketTypes.CLIENT_DISCONNECT_PACKET:
                break;
            case PacketTypes.CONNECTION_RESPONSE_PACKET:
                break;
            case PacketTypes.MESSAGE_PACKET:
                break;
        }*/
    }
    
    public void addClient(ChatClient client)
    {
        if (!isServerFull()) {
            clients.add(client);
        }
    }
    
    public void addClient(Socket socket)
    {
        addClient(new ChatClient(socket));
    }
    
    public void disconnectClient(int index)
    {
        
    }
    
    public void disconnectClient(ChatClient client)
    {
        for (int c = 0; c < connectedClients(); c++)
        {
            if (clients.get(c) == client) {
                disconnectClient(c);
            }
        }
    }
    
    public boolean isServerFull()
    {
        return connectedClients() >= MAX_CLIENTS;
    }
    
    public int connectedClients()
    {
        return clients.size();
    }
}
