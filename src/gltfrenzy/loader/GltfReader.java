package gltfrenzy.loader;

import arc.files.*;
import arc.util.serialization.*;
import gltfrenzy.data.*;

import java.io.*;
import java.nio.*;

/**
 * Reader implementation for {@code .gltf} textual file.
 */
public class GltfReader implements Scenes3DReader{
    @Override
    public Scenes3DData read(Fi file) throws IOException{
        try(var reader = file.reader("UTF-8")){
            int len = (int)file.length();
            len = len == 0 ? 4096 : len;

            var builder = new StringBuilder(len);
            char[] buffer = new char[len];
            while(true){
                int read = reader.read(buffer);
                if(read == -1) break;

                builder.append(buffer, 0, read);
            }

            var data = new Scenes3DData();
            data.spec = Gltf.create(Jval.read(builder.toString()));
            data.buffers = new ByteBuffer[data.spec.buffers.length];
            return data;
        }
    }
}
