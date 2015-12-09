package com.ryanprintup.soar.net;

public final class PacketTypes
{
    public static final byte HEARTBEAT_PACKET           = 0x00;
    public static final byte CLIENT_CONNCET_PACKET      = 0x01;
    public static final byte CONNECTION_RESPONSE_PACKET = 0x02;
    public static final byte CHAT_MESSAGE_PACKET        = 0x03;
    public static final byte CLIENT_DISCONNECT_PACKET   = 0x04;
}
