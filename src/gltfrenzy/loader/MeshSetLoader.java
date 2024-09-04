package gltfrenzy.loader;

import arc.assets.*;
import arc.assets.loaders.*;
import arc.files.*;
import arc.struct.*;
import gltfrenzy.loader.MeshSetLoader.*;
import gltfrenzy.loader.Scenes3DLoader.*;
import gltfrenzy.model.*;

/**
 * Convenience loader to automatically fetch a mesh by-name from a {@link Scenes3D}.
 */
public class MeshSetLoader extends SynchronousAssetLoader<MeshSet, MeshSetParameter>{
    public MeshSetLoader(FileHandleResolver resolver){
        super(resolver);
    }

    @Override
    public MeshSet load(AssetManager manager, String fileName, Fi file, MeshSetParameter parameter){
        int hash = fileName.indexOf('#');
        if(hash == -1) throw new IllegalArgumentException("Invalid file name '" + fileName + "', expected 'path/to/model#mesh-name'");

        var sceneName = fileName.substring(0, hash);
        var scenes = manager.get(sceneName, Scenes3D.class);
        var target = fileName.substring(hash + 1);

        return scenes.mesh(target);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Seq<AssetDescriptor> getDependencies(String fileName, Fi file, MeshSetParameter parameter){
        int hash = fileName.indexOf('#');
        if(hash == -1) throw new IllegalArgumentException("Invalid file name '" + fileName + "', expected 'path/to/model#mesh-name'");

        return Seq.with(new AssetDescriptor<>(resolve(fileName.substring(0, hash)), Scenes3D.class, parameter == null ? null : parameter.sceneParameter));
    }

    public static class MeshSetParameter extends AssetLoaderParameters<MeshSet>{
        public Scenes3DParameter sceneParameter;

        public MeshSetParameter(){
            this(null);
        }

        public MeshSetParameter(Scenes3DParameter sceneParameter){
            this.sceneParameter = sceneParameter;
        }
    }
}
