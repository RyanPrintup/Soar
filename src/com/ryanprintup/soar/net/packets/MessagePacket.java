package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;
import com.ryanprintup.soar.net.PacketTypes;

public class MessagePacket extends Packet
{
    private String name;
    private String message;
    
    public MessagePacket(String name, String message)
    { 
        super();
        
        this.name    = name;
        this.message = message;
    }
    
    public MessagePacket(byte[] buffer)
    {
        super(buffer);
    }
    
    @Override
    public void read(ByteBufferInputStream stream)
    {
        this.name    = stream.readString();
        this.message = stream.readString();
    }
    
    @Override
    public void write(ByteBufferOutputStream stream)
    {
        stream.writeString(name);
        stream.writeString(message);
    }
    
    @Override
    public byte getId()
    {
        return PacketTypes.MESSAGE_PACKET;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getMessage()
    {
        return message;
    }
}
