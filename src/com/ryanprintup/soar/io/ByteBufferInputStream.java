package com.ryanprintup.soar.io;

public class ByteBufferInputStream
{    
    private int position = 0;
    private byte[] buffer;
    
    /**
     * Creates a new byte buffer stream based
     * on a pre-existing buffer.
     * 
     * @param buffer The byte buffer
     */
    public ByteBufferInputStream(final byte[] buffer)
    {
        this.buffer = buffer;
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

    public byte readByte()
    {
        return buffer[advance(1) - 1];
    }

    public short readUByte()
    {
        return (short) (readByte() & 0xFF);
    }

    public byte[] readByteArray(int length)
    {
        byte[] data = new byte[length];
        
        for (int x = 0; x < length; x++) {
            data[x] = readByte();
        }
        
        return data;
    }
    
    public byte[] readByteArray()
    {
        return readByteArray((int) readVLQ());
    }

    public short[] readUByteArray(int length)
    {
        short data[] = new short[length];
        
        for (int x = 0; x < length; x++) {
            data[x] = readUByte();
        }
        
        return data;
    }
    
    public long readVLQ()
    {
        long value = 0;

        for (int i = 0; i < capacity(); i++)
        {
            int curByte = readUByte();

            value = (value << 7) | (curByte & 0x7F);

            if ((curByte & 0x80) == 0) {
                break;
            }
        }
        return value;
    }

    public int readInt8()
    {
        return (int) readByte();
    }

    public int readUInt8()
    {
        return readUByte();
    }

    public int readInt16()
    {
	return (readInt8() & 0xff) <<  8 |
		    readInt8() & 0xff;
    }

    public int readUInt16()
    {
    	return readInt16() & 0xFFFFFF;
    }

    public int readInt32()
    {
	return  (readInt8() & 0xff) << 24 |
            (readInt8() & 0xff) << 16 |
            (readInt8() & 0xff) <<  8 |
             readInt8() & 0xff;
    }
    
    public long readUInt32() 
    {
    	return readInt32() & 0xFFFFFFFFL;
    }

    public long readInt64()
    {
    	return ((long) readInt8() & 0xff) << 56 |
               ((long) readInt8() & 0xff) << 48 |
               ((long) readInt8() & 0xff) << 40 |
               ((long) readInt8() & 0xff) << 32 |
               ((long) readInt8() & 0xff) << 24 |
               ((long) readInt8() & 0xff) << 16 |
	           ((long) readInt8() & 0xff) <<  8 |
	            (long) readInt8() & 0xff;
    }

    public long readUInt64()
    {
	return readInt64();
    }

    public boolean readBoolean()
    {
        return readByte() != 0;
    }

    public short readShort()
    {
        return (short) (readByte() << 8 |
                        readByte() & 0xFF);
    }
 
    public int readUShort()
    {
        return readShort() & 0xFFFF;
    }

    public char readChar()
    {
        return (char) readShort();
    }
    
    public float readFloat()
    {
        return Float.intBitsToFloat(readInt32());
    }

    public double readDouble()
    {
        return Double.longBitsToDouble(readInt64());
    }

    public String readString()
    {
        return new String(readByteArray());
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
