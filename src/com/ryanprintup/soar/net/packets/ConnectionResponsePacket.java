package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;
import com.ryanprintup.soar.net.PacketTypes;

public class ConnectionResponsePacket extends Packet
{
    private boolean success;
    private String rejectionReason;
    
    public ConnectionResponsePacket(boolean success, String rejectionReason)
    {
        super();
        
        this.success = success;
        this.rejectionReason = rejectionReason;
    }
    
    public ConnectionResponsePacket(byte[] buffer)
    {
        super(buffer);
    }
    
    @Override
    public void read(ByteBufferInputStream stream)
    {
        this.success         = stream.readBoolean();
        this.rejectionReason = stream.readString();
    }
    
    @Override
    public void write(ByteBufferOutputStream stream)
    {
        stream.writeBoolean(success);
        stream.writeString(rejectionReason);
    }
    
    @Override
    public byte getId()
    {
        return PacketTypes.CONNECTION_RESPONSE_PACKET;
    }
    
    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public void setRejectionReason(String rejectionReason)
    {
        this.rejectionReason = rejectionReason;
    }
    
    public boolean getSuccess()
    {
        return success;
    }

    public String getRejectionReason()
    {
        return rejectionReason;
    }
}
