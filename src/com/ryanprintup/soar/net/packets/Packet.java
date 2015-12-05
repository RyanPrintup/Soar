package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;

public abstract class Packet
{
    protected byte[] buffer;
    
    public Packet()
    { 
        this.buffer = new byte[0];
    }
    
    public Packet(byte[] buffer)
    {
        this.buffer = buffer;
    }
    
    private void writeToBuffer()
    {
        ByteBufferOutputStream stream = new ByteBufferOutputStream();
        write(stream);
        buffer = stream.getBytes();
    }
    
    public abstract void read(ByteBufferInputStream stream);
    
    public abstract void write(ByteBufferOutputStream stream);
    
    public abstract byte getId();
    
    public long getPayloadLength()
    {
        return buffer.length;
    }
    
    public byte[] getBuffer()
    {
        writeToBuffer();
        
        return buffer;
    }
    
    public byte[] getBytes()
    {
        writeToBuffer();
        ByteBufferOutputStream stream = new ByteBufferOutputStream(2 + (int) getPayloadLength());
        
        stream.writeUInt8(getId());
        stream.writeVLQ(getPayloadLength());
        write(stream);
        
        return stream.getBytes();
    }
}
