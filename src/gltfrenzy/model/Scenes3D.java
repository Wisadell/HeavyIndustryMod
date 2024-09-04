package gltfrenzy.model;

import arc.assets.loaders.*;
import arc.files.*;
import arc.graphics.*;
import arc.graphics.Mesh;
import arc.struct.*;
import arc.util.*;
import gltfrenzy.data.*;
import gltfrenzy.loader.*;
import gltfrenzy.loader.Scenes3DLoader.*;
import gltfrenzy.loader.Scenes3DReader.*;
import gltfrenzy.model.MeshSet.*;
import gltfrenzy.model.Scenes3D.MeshContainerDefer.*;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.util.*;

/**
 * A <a href=https://registry.khronos.org/glTF/specs/2.0/glTF-2.0.html>glTF 2.0</a> scene implementation.
 */
public class Scenes3D implements Disposable{
    public final Seq<MeshSet> meshes = new Seq<>();
    public final ObjectMap<String, MeshSet> meshNames = new ObjectMap<>();

    public final Seq<Node> nodes = new Seq<>();
    public final ObjectMap<String, Node> nodeNames = new ObjectMap<>();
    public final IntSeq rootNodes = new IntSeq();

    public MeshSet mesh(String name){
        return meshNames.getThrow(name, () -> new IllegalArgumentException("Mesh '" + name + "' not found."));
    }

    public Node node(String name){
        return nodeNames.getThrow(name, () -> new IllegalArgumentException("Node '" + name + "' not found."));
    }

    @Override
    public void dispose(){
        meshes.each(MeshSet::dispose);
        meshes.clear();
    }

    public Runnable load(Fi file, FileHandleResolver resolver, @Nullable Scenes3DParameter parameter){
        try{
            var ext = file.extension();
            var reader = ext.equals("gltf") ? new GltfReader() : ext.equals("glb") ? new GlbReader() : null;
            if(reader == null) throw new IllegalArgumentException("Invalid glTF file extension: '" + ext + "'.");

            return load(file, reader.read(file), resolver, parameter);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public Runnable load(Fi file, Scenes3DData data, FileHandleResolver resolver, @Nullable Scenes3DParameter parameter){
        if(parameter == null){
            parameter = new Scenes3DParameter();
            parameter.asset = this;
        }

        if(parameter.asset != null && parameter.asset != this){
            throw new IllegalArgumentException("glTF asset instance mismatch. Either set to `null`, or the same as the instance its passed to `load(...)`.");
        }

        var spec = data.spec;
        var buffers = data.buffers;

        for(int i = 0; i < spec.buffers.length; i++){
            var buffer = spec.buffers[i];

            var uri = URI.create(buffer.uri);
            var scheme = uri.getScheme();
            if(scheme == null){
                var bufFile = resolver.resolve(file.parent().child(buffer.uri).path());
                var output = buffers[i] = ByteBuffer.wrap(bufFile.readBytes());
                output.order(ByteOrder.LITTLE_ENDIAN);
            }else{
                //TODO Base-64 buffers.
            }
        }

        var bufferViews = new ByteBuffer[spec.bufferViews.length];
        for(int i = 0; i < bufferViews.length; i++){
            var view = spec.bufferViews[i];

            var buffer = buffers[view.buffer];
            buffer.limit(view.byteOffset + view.byteLength);
            buffer.position(view.byteOffset);

            bufferViews[i] = buffer.slice();
            bufferViews[i].order(ByteOrder.LITTLE_ENDIAN);
            buffer.clear();
        }

        // TODO Handle morph targets.
        var meshes = new MeshContainerDefer[spec.meshes.length];
        for(int i = 0; i < meshes.length; i++){
            var mesh = spec.meshes[i];
            var cont = meshes[i] = new MeshContainerDefer();
            cont.name = mesh.name;

            cont.containers = new MeshDefer[mesh.primitives.length];
            for(int m = 0; m < mesh.primitives.length; m++){
                var primitives = mesh.primitives[m];
                var attrs = primitives.attributes;

                int verticesLen = 0, verticesCount = -1;
                Seq<VertexAttribute> attributes = new Seq<>(true, attrs.size, VertexAttribute.class);

                for(int j = 0; j < attrs.size; j++){
                    var alias = attrs.getKeyAt(j);
                    alias = parameter.attributeAlias.get(alias, alias);
                    if(mesh.name != null && parameter.skipAttribute.get(mesh.name, ObjectSet::new).contains(alias)){
                        attributes.add((VertexAttribute)null);
                        continue;
                    }

                    var accessor = spec.accessors[attrs.getValueAt(j).asInt()];
                    if(verticesCount != -1 && accessor.count != verticesCount){
                        throw new IllegalArgumentException("Vertices count mismatch, found accessor with count " + accessor.count + " instead of " + verticesCount);
                    }else{
                        verticesCount = accessor.count;
                    }

                    var attr = new VertexAttribute(switch(accessor.type){
                        case scalar -> 1;
                        case vec2 -> 2;
                        case vec3 -> 3;
                        case vec4, mat2 -> 4;
                        case mat3 -> 9;
                        case mat4 -> 16;
                    }, accessor.componentType, accessor.normalized, alias);
                    verticesLen += verticesCount * attr.size;

                    attributes.add(attr);
                }

                var vertexBuffer = Buffers.newUnsafeByteBuffer(verticesLen);
                vertexBuffer.order(ByteOrder.LITTLE_ENDIAN);
                for(int j = 0; j < verticesCount; j++){
                    for(int k = 0; k < attrs.size; k++){
                        var attr = attributes.get(k);
                        if(attr == null) continue;

                        var accessor = spec.accessors[attrs.getValueAt(k).asInt()];
                        if(accessor.bufferView == -1){
                            // TODO Handle sparse accessors.
                            vertexBuffer.put(new byte[attr.size]);
                        }else{
                            var view = bufferViews[accessor.bufferView];
                            view.clear();
                            view.limit(accessor.byteOffset + (j + 1) * attr.size);
                            view.position(accessor.byteOffset + j * attr.size);
                            vertexBuffer.put(view);
                        }
                    }
                }

                int indicesCount = 0;
                ByteBuffer indexBuffer = null;
                if(primitives.indices != -1){
                    var accessor = spec.accessors[primitives.indices];
                    if(accessor.type != AccessorType.scalar) throw new IllegalArgumentException("Indices accessor must be scalar.");

                    indexBuffer = Buffers.newUnsafeByteBuffer((indicesCount = accessor.count) * Short.BYTES);
                    indexBuffer.order(ByteOrder.LITTLE_ENDIAN);
                    if(accessor.bufferView == -1){
                        indexBuffer.put(new byte[indicesCount * Short.BYTES]);
                    }else{
                        var view = bufferViews[accessor.bufferView];
                        switch(accessor.componentType){
                            case Gl.byteV, Gl.unsignedByte -> {
                                byte[] indices = new byte[indicesCount];
                                view.clear();
                                view.limit(accessor.byteOffset + indicesCount * Byte.BYTES);
                                view.position(accessor.byteOffset);
                                view.get(indices);

                                var dst = indexBuffer.asShortBuffer();
                                for(byte index : indices) dst.put(index);
                            }
                            case Gl.shortV, Gl.unsignedShort -> {
                                view.clear();
                                view.limit(accessor.byteOffset + indicesCount * Short.BYTES);
                                view.position(accessor.byteOffset);

                                indexBuffer.asShortBuffer().put(view.asShortBuffer());
                            }
                            case Gl.unsignedInt -> {
                                int[] indices = new int[indicesCount];
                                view.clear();
                                view.limit(accessor.byteOffset + indicesCount * Integer.BYTES);
                                view.position(accessor.byteOffset);
                                view.asIntBuffer().get(indices);

                                var dst = indexBuffer.asShortBuffer();
                                for(int index : indices) dst.put((short)index);
                            }
                            default -> throw new IllegalArgumentException("Indices accessor component type must be integer.");
                        }
                    }
                }

                var queue = cont.containers[m] = new MeshDefer();
                queue.vertices = vertexBuffer;
                queue.vertices.clear();
                queue.maxVertices = verticesCount;

                queue.indices = indexBuffer;
                queue.indices.clear();
                queue.maxIndices = indicesCount;

                queue.attributes = attributes.retainAll(Objects::nonNull).toArray();
                queue.mode = primitives.mode;
            }
        }

        // TODO Handle skin and bone weights.
        var nodeMeshes = new int[spec.nodes.length];
        for(int i = 0; i < spec.nodes.length; i++){
            var out = new Node();
            var node = spec.nodes[i];

            out.name = node.name;
            nodeMeshes[i] = node.mesh;

            if(node.matrix == null){
                out.localTrns.translation.set(node.translation);
                out.localTrns.rotation.set(node.rotation);
                out.localTrns.scale.set(node.scale);
            }else{
                node.matrix.getTranslation(out.localTrns.translation);
                node.matrix.getRotation(out.localTrns.rotation);
                node.matrix.getScale(out.localTrns.scale);
            }

            nodes.add(out);
            if(!out.name.isEmpty()) nodeNames.put(out.name, out);
        }

        for(int i = 0; i < nodes.size; i++){
            var node = nodes.get(i);
            for(int child : spec.nodes[i].children){
                var c = nodes.get(child);
                c.parent = node;
                node.children.add(c);
            }
        }

        nodes.each(Node::update);

        return () -> {
            for(var cont : meshes){
                var set = new MeshSet();
                set.name = cont.name;

                for(var queue : cont.containers){
                    var mesh = new Mesh(true, queue.maxVertices, queue.maxIndices, queue.attributes);
                    {
                        FloatBuffer src = queue.vertices.asFloatBuffer(), dst = mesh.getVerticesBuffer();
                        src.clear();
                        dst.clear();

                        dst.put(src);
                        dst.clear();
                    }

                    {
                        ShortBuffer src = queue.indices.asShortBuffer(), dst = mesh.getIndicesBuffer();
                        src.clear();
                        dst.clear();

                        dst.put(src);
                        dst.clear();
                    }

                    Buffers.disposeUnsafeByteBuffer(queue.vertices);
                    Buffers.disposeUnsafeByteBuffer(queue.indices);
                    set.containers.add(new MeshContainer(mesh, queue.mode));
                }

                this.meshes.add(set);
                if(!set.name.isEmpty()) this.meshNames.put(set.name, set);
            }

            for(int i = 0; i < nodeMeshes.length; i++){
                this.nodes.get(i).mesh = this.meshes.get(nodeMeshes[i]);
            }
        };
    }

    protected static class MeshContainerDefer{
        public String name;
        public MeshDefer[] containers;

        public static class MeshDefer{
            public ByteBuffer vertices, indices;
            public VertexAttribute[] attributes;
            public int maxVertices, maxIndices, mode;
        }
    }
}
