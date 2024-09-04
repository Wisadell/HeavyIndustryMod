package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class Animation {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public String name;

    public AnimationChannel[] channels;

    public AnimationSampler[] samplers;

    private Animation() {
    }

    public static Animation create(Jval json) {
        Animation out = new Animation();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        out.name = json.getString("name", "");
        channels: {
            Jval channels__data = json.get("channels");
            if(channels__data == null) {
                throw new IllegalArgumentException("Property `channels` is required for `Animation`.");
            }
            AnimationChannel[] channels;
            channels = channels__data.asArray().map(channels__item -> {
                AnimationChannel channels__out;
                channels__out = AnimationChannel.create(channels__item);
                return channels__out;
            } ).toArray(AnimationChannel.class);
            out.channels = channels;
        }
        samplers: {
            Jval samplers__data = json.get("samplers");
            if(samplers__data == null) {
                throw new IllegalArgumentException("Property `samplers` is required for `Animation`.");
            }
            AnimationSampler[] samplers;
            samplers = samplers__data.asArray().map(samplers__item -> {
                AnimationSampler samplers__out;
                samplers__out = AnimationSampler.create(samplers__item);
                return samplers__out;
            } ).toArray(AnimationSampler.class);
            out.samplers = samplers;
        }
        return out;
    }
}
