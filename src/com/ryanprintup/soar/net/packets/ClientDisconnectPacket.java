package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;
import com.ryanprintup.soar.net.PacketTypes;

public class ClientDisconnectPacket extends Packet
{
    private String reason;

    public ClientDisconnectPacket()
    {
    }

    public ClientDisconnectPacket(String reason)
    {
        this.reason = reason;
    }

    @Override
    public void read(ByteBufferInputStream stream)
    {
        reason = stream.readString();
    }

    @Override
    public void write(ByteBufferOutputStream stream)
    {
        stream.writeString(reason);
    }

    @Override
    public byte getId()
    {
        return PacketTypes.CLIENT_DISCONNECT_PACKET;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
}
