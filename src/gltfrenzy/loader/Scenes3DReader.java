package gltfrenzy.loader;

import arc.files.*;
import gltfrenzy.data.*;

import java.io.*;
import java.nio.*;

/**
 * glTF asset reader abstraction.
 */
public interface Scenes3DReader{
    Scenes3DData read(Fi file) throws IOException;

    class Scenes3DData{
        public Gltf spec;
        public ByteBuffer[] buffers;
    }
}
