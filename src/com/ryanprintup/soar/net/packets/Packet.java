package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;

public abstract class Packet
{
    public byte[] build()
    {
        ByteBufferOutputStream packetStream = new ByteBufferOutputStream();
        ByteBufferOutputStream dataStream = new ByteBufferOutputStream();

        write(dataStream);

        packetStream.writeUInt8(getId());
        packetStream.writeVLQ(dataStream.capacity());
        packetStream.writeByteArray(dataStream.getBytes());

        return packetStream.getBytes();
    }

    public abstract void read(ByteBufferInputStream stream);
    
    public abstract void write(ByteBufferOutputStream stream);
    
    public abstract byte getId();
}
