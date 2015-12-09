package com.ryanprintup.soar.net.packets;

import com.ryanprintup.soar.io.ByteBufferInputStream;
import com.ryanprintup.soar.io.ByteBufferOutputStream;
import com.ryanprintup.soar.net.PacketTypes;

public class ConnectionResponsePacket extends Packet
{
    private boolean success;
    private long clientID;
    private String rejectionReason;

    public ConnectionResponsePacket()
    {
        rejectionReason = "";
    }

    public ConnectionResponsePacket(boolean success, long clientID, String rejectionReason)
    {
        this.success = success;
        this.clientID = clientID;
        this.rejectionReason = rejectionReason;
    }

    @Override
    public void read(ByteBufferInputStream stream)
    {
        success         = stream.readBoolean();
        clientID        = stream.readVLQ();
        rejectionReason = stream.readString();
    }

    @Override
    public void write(ByteBufferOutputStream stream)
    {
        stream.writeBoolean(success);
        stream.writeVLQ(clientID);
        stream.writeString(rejectionReason);
    }

    @Override
    public byte getId()
    {
        return PacketTypes.CONNECTION_RESPONSE_PACKET;
    }


    public boolean getSuccess()
    {
        return success;
    }

    public long getClientID()
    {
        return clientID;
    }

    public String getRejectionReason()
    {
        return rejectionReason;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public void setClientID(long clientID)
    {
        this.clientID = clientID;
    }

    public void setRejectionReason(String rejectionReason)
    {
        this.rejectionReason = rejectionReason;
    }
}
