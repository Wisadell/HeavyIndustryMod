package gltfrenzy.loader;

import arc.assets.*;
import arc.assets.loaders.*;
import arc.files.*;
import arc.graphics.*;
import arc.struct.*;
import arc.util.*;
import gltfrenzy.loader.Scenes3DLoader.*;
import gltfrenzy.loader.Scenes3DReader.*;
import gltfrenzy.model.*;

import java.io.*;

/**
 * Asynchronous asset loader implementation to load {@link Scenes3D} assets from either {@code .gltf} or {@code .glb} files.
 */
public class Scenes3DLoader extends AsynchronousAssetLoader<Scenes3D, Scenes3DParameter> implements FileHandleResolver{
    protected final Scenes3DReader reader;
    protected Scenes3D asset;
    protected Runnable sync;

    public Scenes3DLoader(FileHandleResolver resolver, Scenes3DReader reader){
        super(resolver);
        this.reader = reader;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, Fi file, @Nullable Scenes3DParameter parameter){
        if(parameter == null) parameter = new Scenes3DParameter();
        asset = parameter.asset != null ? parameter.asset : (parameter.asset = new Scenes3D());

        Scenes3DData data;
        try{
            data = reader.read(file);
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        sync = asset.load(file, data, this, parameter);
    }

    @Override
    public Scenes3D loadSync(AssetManager manager, String fileName, Fi file, Scenes3DParameter parameter){
        var out = asset;
        sync.run();

        asset = null;
        sync = null;
        return out;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Seq<AssetDescriptor> getDependencies(String fileName, Fi file, Scenes3DParameter parameter){
        return null;
    }

    public static class Scenes3DParameter extends AssetLoaderParameters<Scenes3D>{
        public Scenes3D asset;
        public StringMap attributeAlias = StringMap.of(
            "COLOR_0", VertexAttribute.color.alias,
            "POSITION", VertexAttribute.position3.alias,
            "NORMAL", VertexAttribute.normal.alias,
            "TEXCOORD_0", VertexAttribute.texCoords.alias
        );
        public ObjectMap<String, ObjectSet<String>> skipAttribute = new ObjectMap<>();

        public Scenes3DParameter(){
            this(null);
        }

        public Scenes3DParameter(Scenes3D asset){
            this.asset = asset;
        }

        public Scenes3DParameter alias(String from, String to){
            attributeAlias.put(from, to);
            return this;
        }

        public Scenes3DParameter skip(String mesh, String attribute){
            skipAttribute.get(mesh, ObjectSet::new).add(attribute);
            return this;
        }
    }
}
