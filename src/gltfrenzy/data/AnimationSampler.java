package gltfrenzy.data;

import arc.util.Nullable;
import arc.util.serialization.Jval;

public final class AnimationSampler {

    public @Nullable Jval extensions;

    public @Nullable Jval extras;

    public int input;

    public AnimationInterpolation interpolation;

    public int output;

    private AnimationSampler() {
    }

    public static AnimationSampler create(Jval json) {
        AnimationSampler out = new AnimationSampler();
        out.extensions = json.get("extensions");
        out.extras = json.get("extras");
        input: {
            Jval input__data = json.get("input");
            if(input__data == null) {
                throw new IllegalArgumentException("Property `input` is required for `AnimationSampler`.");
            }
            int input;
            input = input__data.asInt();
            out.input = input;
        }
        interpolation: {
            Jval interpolation__data = json.get("interpolation");
            if(interpolation__data == null) {
                out.interpolation = AnimationInterpolation.linear;
                break interpolation;
            }
            AnimationInterpolation interpolation;
            interpolation = AnimationInterpolation.create(interpolation__data);
            out.interpolation = interpolation;
        }
        output: {
            Jval output__data = json.get("output");
            if(output__data == null) {
                throw new IllegalArgumentException("Property `output` is required for `AnimationSampler`.");
            }
            int output;
            output = output__data.asInt();
            out.output = output;
        }
        return out;
    }
}
