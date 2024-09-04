package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Gltf {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String[] extensionsUsed;

    public String[] extensionsRequired;

    public Accessor[] accessors;

    public Animation[] animations;

    public Asset asset;

    public Buffer[] buffers;

    public BufferView[] bufferViews;

    public Camera[] cameras;

    public Image[] images;

    public Material[] materials;

    public Mesh[] meshes;

    public Node[] nodes;

    public Sampler[] samplers;

    public int scene;

    public Scene[] scenes;

    public Skin[] skins;

    public Texture[] textures;

    private Gltf() {}

    public static Gltf create(Jval json) {
        Gltf out = new Gltf();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        extensionsUsed: {
            Jval extensionsUsed__data = json.get("extensionsUsed");
            if(extensionsUsed__data == null) {
                out.extensionsUsed = new String[0];
                break extensionsUsed;
            }
            String[] extensionsUsed;
            extensionsUsed = extensionsUsed__data.asArray().map(extensionsUsed__item -> {
                String extensionsUsed__out;
                extensionsUsed__out = extensionsUsed__item.asString();
                return extensionsUsed__out;
            } ).toArray(String.class);
            out.extensionsUsed = extensionsUsed;
        }
        extensionsRequired: {
            Jval extensionsRequired__data = json.get("extensionsRequired");
            if(extensionsRequired__data == null) {
                out.extensionsRequired = new String[0];
                break extensionsRequired;
            }
            String[] extensionsRequired;
            extensionsRequired = extensionsRequired__data.asArray().map(extensionsRequired__item -> {
                String extensionsRequired__out;
                extensionsRequired__out = extensionsRequired__item.asString();
                return extensionsRequired__out;
            } ).toArray(String.class);
            out.extensionsRequired = extensionsRequired;
        }
        accessors: {
            Jval accessors__data = json.get("accessors");
            if(accessors__data == null) {
                out.accessors = new Accessor[0];
                break accessors;
            }
            Accessor[] accessors;
            accessors = accessors__data.asArray().map(accessors__item -> {
                Accessor accessors__out;
                accessors__out = Accessor.create(accessors__item);
                return accessors__out;
            } ).toArray(Accessor.class);
            out.accessors = accessors;
        }
        animations: {
            Jval animations__data = json.get("animations");
            if(animations__data == null) {
                out.animations = new Animation[0];
                break animations;
            }
            Animation[] animations;
            animations = animations__data.asArray().map(animations__item -> {
                Animation animations__out;
                animations__out = Animation.create(animations__item);
                return animations__out;
            } ).toArray(Animation.class);
            out.animations = animations;
        }
        asset: {
            Jval asset__data = json.get("asset");
            if(asset__data == null) {
                throw new IllegalArgumentException("Property `asset` is required for `Gltf`.");
            }
            Asset asset;
            asset = Asset.create(asset__data);
            out.asset = asset;
        }
        buffers: {
            Jval buffers__data = json.get("buffers");
            if(buffers__data == null) {
                out.buffers = new Buffer[0];
                break buffers;
            }
            Buffer[] buffers;
            buffers = buffers__data.asArray().map(buffers__item -> {
                Buffer buffers__out;
                buffers__out = Buffer.create(buffers__item);
                return buffers__out;
            } ).toArray(Buffer.class);
            out.buffers = buffers;
        }
        bufferViews: {
            Jval bufferViews__data = json.get("bufferViews");
            if(bufferViews__data == null) {
                out.bufferViews = new BufferView[0];
                break bufferViews;
            }
            BufferView[] bufferViews;
            bufferViews = bufferViews__data.asArray().map(bufferViews__item -> {
                BufferView bufferViews__out;
                bufferViews__out = BufferView.create(bufferViews__item);
                return bufferViews__out;
            } ).toArray(BufferView.class);
            out.bufferViews = bufferViews;
        }
        cameras: {
            Jval cameras__data = json.get("cameras");
            if(cameras__data == null) {
                out.cameras = new Camera[0];
                break cameras;
            }
            Camera[] cameras;
            cameras = cameras__data.asArray().map(cameras__item -> {
                Camera cameras__out;
                cameras__out = Camera.create(cameras__item);
                return cameras__out;
            } ).toArray(Camera.class);
            out.cameras = cameras;
        }
        images: {
            Jval images__data = json.get("images");
            if(images__data == null) {
                out.images = new Image[0];
                break images;
            }
            Image[] images;
            images = images__data.asArray().map(images__item -> {
                Image images__out;
                images__out = Image.create(images__item);
                return images__out;
            } ).toArray(Image.class);
            out.images = images;
        }
        materials: {
            Jval materials__data = json.get("materials");
            if(materials__data == null) {
                out.materials = new Material[0];
                break materials;
            }
            Material[] materials;
            materials = materials__data.asArray().map(materials__item -> {
                Material materials__out;
                materials__out = Material.create(materials__item);
                return materials__out;
            } ).toArray(Material.class);
            out.materials = materials;
        }
        meshes: {
            Jval meshes__data = json.get("meshes");
            if(meshes__data == null) {
                out.meshes = new Mesh[0];
                break meshes;
            }
            Mesh[] meshes;
            meshes = meshes__data.asArray().map(meshes__item -> {
                Mesh meshes__out;
                meshes__out = Mesh.create(meshes__item);
                return meshes__out;
            } ).toArray(Mesh.class);
            out.meshes = meshes;
        }
        nodes: {
            Jval nodes__data = json.get("nodes");
            if(nodes__data == null) {
                out.nodes = new Node[0];
                break nodes;
            }
            Node[] nodes;
            nodes = nodes__data.asArray().map(nodes__item -> {
                Node nodes__out;
                nodes__out = Node.create(nodes__item);
                return nodes__out;
            } ).toArray(Node.class);
            out.nodes = nodes;
        }
        samplers: {
            Jval samplers__data = json.get("samplers");
            if(samplers__data == null) {
                out.samplers = new Sampler[0];
                break samplers;
            }
            Sampler[] samplers;
            samplers = samplers__data.asArray().map(samplers__item -> {
                Sampler samplers__out;
                samplers__out = Sampler.create(samplers__item);
                return samplers__out;
            } ).toArray(Sampler.class);
            out.samplers = samplers;
        }
        scene: {
            Jval scene__data = json.get("scene");
            if(scene__data == null) {
                out.scene = -1;
                break scene;
            }
            int scene;
            scene = scene__data.asInt();
            out.scene = scene;
        }
        scenes: {
            Jval scenes__data = json.get("scenes");
            if(scenes__data == null) {
                out.scenes = new Scene[0];
                break scenes;
            }
            Scene[] scenes;
            scenes = scenes__data.asArray().map(scenes__item -> {
                Scene scenes__out;
                scenes__out = Scene.create(scenes__item);
                return scenes__out;
            } ).toArray(Scene.class);
            out.scenes = scenes;
        }
        skins: {
            Jval skins__data = json.get("skins");
            if(skins__data == null) {
                out.skins = new Skin[0];
                break skins;
            }
            Skin[] skins;
            skins = skins__data.asArray().map(skins__item -> {
                Skin skins__out;
                skins__out = Skin.create(skins__item);
                return skins__out;
            } ).toArray(Skin.class);
            out.skins = skins;
        }
        textures: {
            Jval textures__data = json.get("textures");
            if(textures__data == null) {
                out.textures = new Texture[0];
                break textures;
            }
            Texture[] textures;
            textures = textures__data.asArray().map(textures__item -> {
                Texture textures__out;
                textures__out = Texture.create(textures__item);
                return textures__out;
            } ).toArray(Texture.class);
            out.textures = textures;
        }
        return out;
    }
}
