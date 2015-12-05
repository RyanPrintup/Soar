package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;
import com.ryanprintup.soar.net.PacketTypes;

public class ClientDisconnectPacket extends Packet
{
    private String name;
    private String reason;
    
    public ClientDisconnectPacket(String name, String reason)
    {
        super();
        
        this.name = name;
        this.reason = reason;
    }
    
    public ClientDisconnectPacket(byte[] buffer)
    {
        super(buffer);
    }
    
    @Override
    public void read(ByteBufferInputStream stream)
    {
        this.name   = stream.readString();
        this.reason = stream.readString();
    }
    
    @Override
    public void write(ByteBufferOutputStream stream)
    {
        stream.writeString(name);
        stream.writeString(reason);
    }
    
    @Override
    public byte getId()
    {
        return PacketTypes.CLIENT_DISCONNECT_PACKET;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
    
    public String getName()
    {
        return name;
    }

    public String getReason()
    {
        return reason;
    }
}
