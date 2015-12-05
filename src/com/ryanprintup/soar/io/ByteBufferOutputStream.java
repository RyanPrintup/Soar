package com.ryanprintup.soar.io;

public class ByteBufferOutputStream
{
    private static final int DEFAULT_BUFFER_SIZE = 32; // 32 bytes
    
    private int position = 0;
    private byte[] buffer;
    
    /**
     * Creates a new byte buffer stream. The capacity is
     * initially 32 bytes, this increases if necessary.
     */
    public ByteBufferOutputStream()
    {
        this(DEFAULT_BUFFER_SIZE);
    }
    
    /**
     * Creates a new byte buffer stream with a
     * specified capacity in bytes.
     * 
     * @param size The initial capacity
     */
    public ByteBufferOutputStream(int size)
    {
        this.buffer = new byte[size];
    }
    
    private int advance(int count)
    {
        position += count;
        
        if (position > buffer.length) {
            throw new ArrayIndexOutOfBoundsException("Position greater than buffer size.");
        }
        
        return position;
    }
    
    public void skip(int count)
    {
        advance(count);
    }
    
    /**
     * Expands the capacity of the buffer.
     * 
     * @param size the amount to expand by
     */
    public void expand(int size)
    {
        byte[] tempBuf = new byte[capacity() + size];
        System.arraycopy(buffer, 0, tempBuf, 0, capacity());
        
        buffer = tempBuf;
    }
    
    public void writeByte(byte value)
    {
        if ((position + 1) > capacity()) {
            expand(1);
        }
        
        buffer[advance(1) - 1] = value;
    }
    
    public void writeUByte(short value)
    {
    	writeByte((byte) (value & 0xFF));
    }
    
    public void writeByteArray(byte[] array)
    {
        /**
         * Even though this calls writeByte() which will expand
         * the buffer, we repeat this code here for efficiency. 
         * This will expand the full array length at once instead
         * of each individual byte.
         */
        if ((position + array.length) > capacity()) {
            expand((position + array.length) - capacity());
        }
        
        for (byte b : array) {
            writeByte(b);
        }
    }
    
    public void writeUByteArray(short[] array)
    {
        for (short b : array) {
            writeUByte(b);
        }
    }
    
    public void writeVLQ(long value)
    {
        int numRelevantBits = 64 - Long.numberOfLeadingZeros(value);
        int numBytes = (numRelevantBits + 6) / 7;

        if (numBytes == 0) {
            numBytes = 1;
        }

        byte[] output = new byte[numBytes];
        for (int i = numBytes - 1; i >= 0; i--) {
            int curByte = (int) (value & 0x7F);

            if (i != (numBytes - 1)) {
                curByte |= 0x80;
            }

            output[i] = (byte) curByte;
            value >>>= 7;
        }

        writeByteArray(output);
    }
    
    public void writeInt8(int value)
    {
        writeByte((byte) value);
    }

    public void writeUInt8(int value)
    {
        writeUByte((short) value);
    }

    public void writeInt16(int value)
    {
        writeInt8(value >>> 8);
        writeInt8(value);
		
    }

    public void writeUInt16(int value)
    {
        writeInt16(value & 0xFFFFFF);
    }

    public void writeInt32(int value)
    {
        writeByte((byte) (value >>> 24));
        writeByte((byte) (value >>> 16));
        writeByte((byte) (value >>> 8));
        writeByte((byte) value);
    }

    public void writeUInt32(long value)
    {
        writeInt32((int) (value & 0xFFFFFFFFL));
    }

    public void writeInt64(long value)
    {
        writeInt8((int) (value >>> 56));
        writeInt8((int) (value >>> 48));
        writeInt8((int) (value >>> 40));
        writeInt8((int) (value >>> 32));
        writeInt8((int) (value >>> 24));
        writeInt8((int) (value >>> 16));
        writeInt8((int) (value >>> 8));
        writeInt8((int) value);
    }

    public void writeUInt64(long value)
    {
        writeInt64(value);
    }

    public void writeBoolean(boolean value)
    {
        writeByte((byte) (value ? 1 : 0));
    }

    public void writeShort(short value)
    {
        writeByte((byte) (value >>> 8));
        writeByte((byte) value);
    }

    public void writeUShort(int value)
    {
        writeShort((short) (value & 0xFFFF));
    }

    public void writeChar(char value)
    {
        writeShort((short) value);
    }

    public void writeFloat(float value)
    {
        writeInt32(Float.floatToIntBits(value));
    }

    public void writeDouble(double value)
    {
        writeInt64(Double.doubleToLongBits(value));
    }

    public void writeString(String value)
    {
        byte[] stringBytes = value.getBytes();
        long stringPayloadLength = stringBytes.length;

        writeVLQ(stringPayloadLength);
        writeByteArray(stringBytes);
    }

    public int remaining()
    {
        return buffer.length - position;
    }

    public int capacity()
    {
        return buffer.length;
    }

    public int position()
    {
        return position;
    }
    
    public byte[] getBytes()
    {
        return buffer;
    }
}
