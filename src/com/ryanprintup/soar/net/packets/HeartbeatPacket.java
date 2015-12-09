package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;
import com.ryanprintup.soar.net.PacketTypes;

public class HeartbeatPacket extends Packet
{
    private long heartbeat;

    public HeartbeatPacket()
    {
    }

    public HeartbeatPacket(long heartbeat)
    {
        this.heartbeat = heartbeat;
    }

    @Override
    public void read(ByteBufferInputStream stream)
    {
        this.heartbeat = stream.readVLQ();
    }

    @Override
    public void write(ByteBufferOutputStream stream)
    {
        stream.writeVLQ(heartbeat);
    }

    @Override
    public byte getId()
    {
        return PacketTypes.HEARTBEAT_PACKET;
    }


    public void setHearbeat(long hearbeat)
    {
        this.heartbeat = heartbeat;
    }

    public long getHeartbeat()
    {
        return heartbeat;
    }
}
