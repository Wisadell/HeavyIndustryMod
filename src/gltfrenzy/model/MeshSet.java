package gltfrenzy.model;

import arc.graphics.*;
import arc.graphics.gl.*;
import arc.struct.*;
import arc.util.*;

public class MeshSet implements Disposable{
    public String name = "";
    public final Seq<MeshContainer> containers = new Seq<>();

    public MeshSet(){}

    @Override
    public void dispose(){
        containers.each(MeshContainer::dispose);
        containers.clear();
    }

    public static class MeshContainer implements Disposable{
        public Mesh mesh;
        public int mode;

        public MeshContainer(Mesh mesh, int mode){
            this.mesh = mesh;
            this.mode = mode;
        }

        public void render(Shader shader){
            mesh.render(shader, mode);
        }

        @Override
        public void dispose(){
            mesh.dispose();
        }
    }
}
