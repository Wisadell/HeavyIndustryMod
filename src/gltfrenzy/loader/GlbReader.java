package gltfrenzy.loader;

import arc.files.*;
import arc.util.io.*;
import arc.util.serialization.*;
import gltfrenzy.data.*;
import gltfrenzy.io.*;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;

/**
 * Reader implementation for {@code .glb} monolithic binary file.
 */
public class GlbReader implements Scenes3DReader{
    @Override
    public Scenes3DData read(Fi file) throws IOException{
        try(var stream = new CounterInputStream(file.read());
            var read = new LEDataInputStream(stream)
        ){
            int fileLen = readHeader(read);

            var data = new Scenes3DData();
            for(int i = 0; stream.count < fileLen; i++){
                byte[] chunk = new byte[read.readInt()];
                int chunkType = read.readInt();

                read.readFully(chunk);
                switch(chunkType){
                    case 0x4e4f534a -> {
                        if(i != 0) throw new IOException("JSON chunk found at index " + i + ".");
                        data.spec = Gltf.create(Jval.read(new String(chunk, StandardCharsets.UTF_8)));
                        data.buffers = new ByteBuffer[data.spec.buffers.length];
                    }
                    case 0x004e4942 -> {
                        if(i != 1) throw new IOException("BIN chunk found at index " + i);
                        var buffer = data.buffers[0] = ByteBuffer.wrap(chunk);
                        buffer.order(ByteOrder.LITTLE_ENDIAN);
                    }
                    default -> {
                        if(i == 0) throw new IOException("Custom chunk found at index 0.");
                    }
                }
            }

            return data;
        }
    }

    protected int readHeader(DataInput read) throws IOException{
        int magic = read.readInt();
        if(magic != 0x46546c67) throw new IOException("Invalid header 0x" + Integer.toHexString(magic) + ".");

        int version = read.readInt();
        if(version != 2) throw new IOException("Unsupported container version " + version + ".");

        return read.readInt();
    }
}
