package gltfrenzy.data;

import arc.math.geom.Mat3D;
import arc.math.geom.Quat;
import arc.math.geom.Vec3;
import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Node {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public int camera;

    public int[] children;

    public int skin;

    public @Nullable Mat3D matrix;

    public int mesh;

    public Quat rotation;

    public Vec3 scale;

    public Vec3 translation;

    public float[] weights;

    private Node() {}

    public static Node create(Jval json) {
        Node out = new Node();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        camera: {
            Jval camera__data = json.get("camera");
            if(camera__data == null) {
                out.camera = -1;
                break camera;
            }
            int camera;
            camera = camera__data.asInt();
            out.camera = camera;
        }
        children: {
            Jval children__data = json.get("children");
            if(children__data == null) {
                out.children = new int[0];
                break children;
            }
            int[] children;
            Jval.JsonArray children__array = children__data.asArray();
            children = new int[children__array.size];
            for(int children__i = 0, children__len = children__array.size; children__i < children__len; children__i++) {
                Jval children__item = children__array.get(children__i);
                int children__out;
                children__out = children__item.asInt();
                children[children__i] = children__out;
            }
            out.children = children;
        }
        skin: {
            Jval skin__data = json.get("skin");
            if(skin__data == null) {
                out.skin = -1;
                break skin;
            }
            int skin;
            skin = skin__data.asInt();
            out.skin = skin;
        }
        matrix: {
            Jval matrix__data = json.get("matrix");
            if(matrix__data == null) {
                out.matrix = null;
                break matrix;
            }
            Mat3D matrix;
            Jval.JsonArray matrix__array = matrix__data.asArray();
            matrix = new Mat3D();
            for(int matrix__i = 0; matrix__i < 16; matrix__i++) {
                matrix.val[matrix__i] = matrix__array.get(matrix__i).asFloat();
            }
            out.matrix = matrix;
        }
        mesh: {
            Jval mesh__data = json.get("mesh");
            if(mesh__data == null) {
                out.mesh = -1;
                break mesh;
            }
            int mesh;
            mesh = mesh__data.asInt();
            out.mesh = mesh;
        }
        rotation: {
            Jval rotation__data = json.get("rotation");
            if(rotation__data == null) {
                out.rotation = new Quat();
                break rotation;
            }
            Quat rotation;
            Jval.JsonArray rotation__array = rotation__data.asArray();
            rotation = new Quat(rotation__array.get(0).asFloat(), rotation__array.get(1).asFloat(), rotation__array.get(2).asFloat(), rotation__array.get(3).asFloat());
            out.rotation = rotation;
        }
        scale: {
            Jval scale__data = json.get("scale");
            if(scale__data == null) {
                out.scale = new Vec3(1f, 1f, 1f);
                break scale;
            }
            Vec3 scale;
            Jval.JsonArray scale__array = scale__data.asArray();
            scale = new Vec3(scale__array.get(0).asFloat(), scale__array.get(1).asFloat(), scale__array.get(2).asFloat());
            out.scale = scale;
        }
        translation: {
            Jval translation__data = json.get("translation");
            if(translation__data == null) {
                out.translation = new Vec3();
                break translation;
            }
            Vec3 translation;
            Jval.JsonArray translation__array = translation__data.asArray();
            translation = new Vec3(translation__array.get(0).asFloat(), translation__array.get(1).asFloat(), translation__array.get(2).asFloat());
            out.translation = translation;
        }
        weights: {
            Jval weights__data = json.get("weights");
            if(weights__data == null) {
                out.weights = new float[0];
                break weights;
            }
            float[] weights;
            Jval.JsonArray weights__array = weights__data.asArray();
            weights = new float[weights__array.size];
            for(int weights__i = 0, weights__len = weights__array.size; weights__i < weights__len; weights__i++) {
                Jval weights__item = weights__array.get(weights__i);
                float weights__out;
                weights__out = weights__item.asFloat();
                weights[weights__i] = weights__out;
            }
            out.weights = weights;
        }
        return out;
    }
}
