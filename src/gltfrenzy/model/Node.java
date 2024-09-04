package gltfrenzy.model;

import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;

/**
 * Defines an object node in the hierarchy tree. Children of this node inherits its transform.
 */
public class Node{
    private static final Mat3D mat = new Mat3D();

    public String name = "";
    public final Seq<Node> children = new Seq<>();
    public @Nullable Node parent;

    public final Transform localTrns = new Transform();
    public final Mat3D globalTrns = new Mat3D();

    public MeshSet mesh;

    public void update(){
        if(parent != null){
            globalTrns.set(parent.globalTrns).mul(localTrns.matrix(mat));
        }else{
            localTrns.matrix(globalTrns);
        }

        children.each(Node::update);
    }

    public static class Transform{
        public final Vec3 translation = new Vec3();
        public final Quat rotation = new Quat();
        public final Vec3 scale = new Vec3(1f, 1f, 1f);

        public Transform set(Transform from){
            translation.set(from.translation);
            rotation.set(from.rotation);
            scale.set(from.scale);
            return this;
        }

        public Mat3D matrix(Mat3D out){
            return out.set(translation, rotation, scale);
        }
    }
}
