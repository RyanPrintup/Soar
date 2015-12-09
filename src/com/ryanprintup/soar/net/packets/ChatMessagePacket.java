package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;
import com.ryanprintup.soar.net.PacketTypes;

public class ChatMessagePacket extends Packet
{
    private String message;

    public ChatMessagePacket()
    {
    }

    public ChatMessagePacket(String name, String message)
    {
        this.message = message;
    }
    
    @Override
    public void read(ByteBufferInputStream stream)
    {
        this.message = stream.readString();
    }
    
    @Override
    public void write(ByteBufferOutputStream stream)
    {
        stream.writeString(message);
    }
    
    @Override
    public byte getId()
    {
        return PacketTypes.CHAT_MESSAGE_PACKET;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
