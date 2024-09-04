package gltfrenzy.io;

import java.io.*;

/**
 * A little-endian {@link DataInputStream}.
 */
public class LEDataInputStream extends InputStream implements DataInput{
    private final byte[] buffer = new byte[8];
    private final DataInputStream read;

    public LEDataInputStream(InputStream in){
        read = new DataInputStream(in);
    }

    @Override
    public int available() throws IOException{
        return read.available();
    }

    @Override
    public final short readShort() throws IOException{
        read.readFully(buffer, 0, 2);
        return (short)(
            (buffer[1] & 0xff) << 8 |
            (buffer[0] & 0xff)
        );
    }

    @Override
    public final int readUnsignedShort() throws IOException{
        read.readFully(buffer, 0, 2);
        return (
            (buffer[1] & 0xff) << 8 |
            (buffer[0] & 0xff)
        );
    }

    @Override
    public final char readChar() throws IOException{
        read.readFully(buffer, 0, 2);
        return (char)(
            (buffer[1] & 0xff) << 8 |
            (buffer[0] & 0xff)
        );
    }

    @Override
    public final int readInt() throws IOException{
        read.readFully(buffer, 0, 4);
        return
            (buffer[3]) << 24 |
            (buffer[2] & 0xff) << 16 |
            (buffer[1] & 0xff) << 8 |
            (buffer[0] & 0xff);
    }

    @Override
    public final long readLong() throws IOException{
        read.readFully(buffer, 0, 8);
        return
            (long)(buffer[7]) << 56 |
            (long)(buffer[6] & 0xff) << 48 |
            (long)(buffer[5] & 0xff) << 40 |
            (long)(buffer[4] & 0xff) << 32 |
            (long)(buffer[3] & 0xff) << 24 |
            (long)(buffer[2] & 0xff) << 16 |
            (long)(buffer[1] & 0xff) << 8 |
            (long)(buffer[0] & 0xff);
    }

    @Override
    public final float readFloat() throws IOException{
        return Float.intBitsToFloat(readInt());
    }

    @Override
    public final double readDouble() throws IOException{
        return Double.longBitsToDouble(readLong());
    }

    @Override
    public final int read(byte[] b, int off, int len) throws IOException{
        return read.read(b, off, len);
    }

    @Override
    public final void readFully(byte[] b) throws IOException{
        read.readFully(b, 0, b.length);
    }

    @Override
    public final void readFully(byte[] b, int off, int len) throws IOException{
        read.readFully(b, off, len);
    }

    @Override
    public final int skipBytes(int n) throws IOException{
        return read.skipBytes(n);
    }

    @Override
    public final boolean readBoolean() throws IOException{
        return read.readBoolean();
    }

    @Override
    public final byte readByte() throws IOException{
        return read.readByte();
    }

    @Override
    public int read() throws IOException{
        return read.read();
    }

    @Override
    public final int readUnsignedByte() throws IOException{
        return read.readUnsignedByte();
    }

    @Override
    @Deprecated
    public final String readLine() throws IOException{
        return read.readLine();
    }

    @Override
    public final String readUTF() throws IOException{
        return read.readUTF();
    }

    @Override
    public final void close() throws IOException{
        read.close();
    }
}
