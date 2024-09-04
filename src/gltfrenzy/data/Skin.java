package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Skin {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public int inverseBindMatrices;

    public int skeleton;

    public int[] joints;

    private Skin() {}

    public static Skin create(Jval json) {
        Skin out = new Skin();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        inverseBindMatrices: {
            Jval inverseBindMatrices__data = json.get("inverseBindMatrices");
            if(inverseBindMatrices__data == null) {
                out.inverseBindMatrices = -1;
                break inverseBindMatrices;
            }
            int inverseBindMatrices;
            inverseBindMatrices = inverseBindMatrices__data.asInt();
            out.inverseBindMatrices = inverseBindMatrices;
        }
        skeleton: {
            Jval skeleton__data = json.get("skeleton");
            if(skeleton__data == null) {
                out.skeleton = -1;
                break skeleton;
            }
            int skeleton;
            skeleton = skeleton__data.asInt();
            out.skeleton = skeleton;
        }
        joints: {
            Jval joints__data = json.get("joints");
            if(joints__data == null) {
                throw new IllegalArgumentException("Property `joints` is required for `Skin`.");
            }
            int[] joints;
            Jval.JsonArray joints__array = joints__data.asArray();
            joints = new int[joints__array.size];
            for(int joints__i = 0, joints__len = joints__array.size; joints__i < joints__len; joints__i++) {
                Jval joints__item = joints__array.get(joints__i);
                int joints__out;
                joints__out = joints__item.asInt();
                joints[joints__i] = joints__out;
            }
            out.joints = joints;
        }
        return out;
    }
}
