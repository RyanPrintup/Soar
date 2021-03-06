package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;
import com.ryanprintup.soar.net.PacketTypes;

public class ClientConnectPacket extends Packet
{
    private String name;

    public ClientConnectPacket()
    {
    }

    public ClientConnectPacket(String name)
    {
        this.name = name;
    }

    @Override
    public void read(ByteBufferInputStream stream)
    {
        name = stream.readString();
    }

    @Override
    public void write(ByteBufferOutputStream stream)
    {
        stream.writeString(name);
    }

    @Override
    public byte getId()
    {
        return PacketTypes.CLIENT_CONNCET_PACKET;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
