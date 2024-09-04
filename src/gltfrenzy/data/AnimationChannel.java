package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class AnimationChannel {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public int sampler;

    public AnimationChannel target;

    private AnimationChannel() {
    }

    public static AnimationChannel create(Jval json) {
        AnimationChannel out = new AnimationChannel();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        sampler: {
            Jval sampler__data = json.get("sampler");
            if(sampler__data == null) {
                throw new IllegalArgumentException("Property `sampler` is required for `AnimationChannel`.");
            }
            int sampler;
            sampler = sampler__data.asInt();
            out.sampler = sampler;
        }
        target: {
            Jval target__data = json.get("target");
            if(target__data == null) {
                throw new IllegalArgumentException("Property `target` is required for `AnimationChannel`.");
            }
            AnimationChannel target;
            target = AnimationChannel.create(target__data);
            out.target = target;
        }
        return out;
    }
}
