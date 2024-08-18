package HeavyIndustry.maps;

import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.noise.*;

public abstract class HeightPass {
    public abstract float height(Vec3 pos, float height);

    public static class SphereHeight extends HeightPass {
        public Vec3 pos = new Vec3();
        public float radius = 0f;
        public float offset = 0f;
        public boolean set = false;

        @Override
        public float height(Vec3 pos, float height) {
            if (pos.dst(this.pos) < radius) return offset + height * (set ? 0f : 1f);
            return height;
        }
    }
    public static class NoiseHeight extends HeightPass {
        public Vec3 offset = new Vec3();
        public int seed;
        public double octaves = 1.0;
        public double persistence = 1.0;
        public double scale = 1.0;
        public float magnitude = 1;
        public float heightOffset = 0;

        @Override
        public float height(Vec3 pos, float height) {
            return Simplex.noise3d(seed, octaves, persistence, scale, pos.x + offset.x, pos.y + offset.y, pos.z + offset.z) * magnitude + heightOffset + height;
        }
    }
    public static class ClampHeight extends HeightPass {
        public float min, max;

        public ClampHeight(float min, float max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public float height(Vec3 pos, float height) {
            return Mathf.clamp(height, min, max);
        }
    }
    public static class DotHeight extends HeightPass {
        public Vec3 dir = new Vec3();

        public float min = -1f;
        public float max = 1f;

        public boolean map = true;

        public Interp interp = Interp.linear;
        public float magnitude = 1;

        @Override
        public float height(Vec3 pos, float height) {
            float dot = dir.nor().dot(pos);
            if (dot < min || dot > max) return height;
            dot = Mathf.map(dot, map ? min : -1f, map ? max : 1f, 0f, 1f);
            return interp.apply(dot) * magnitude + height;
        }
    }
    public static class MultiHeight extends HeightPass {
        public Seq<HeightPass> heights;
        public MixType mixType;
        public Operation operation;

        public MultiHeight(Seq<HeightPass> heights, MixType mixType, Operation operation) {
            this.heights = heights;
            this.mixType = mixType;
            this.operation = operation;
        }

        @Override
        public float height(Vec3 pos, float height) {
            switch (operation) {
                case add -> {
                    return height + rawHeight(pos);
                }
                case set -> {
                    return rawHeight(pos);
                }
                case carve -> {
                    return height - rawHeight(pos);
                }
            }
            return height;
        }
        float rawHeight(Vec3 pos) {
            switch (mixType) {
                case max -> {
                    return heights.max(pass -> pass.height(pos, 0f)).height(pos, 0f);
                }
                case average -> {
                    return heights.sumf(pass -> pass.height(pos, 0))/(float)heights.size;
                }
            }
            return 0f;
        }

        public enum MixType {
            max, average
        }
        public enum Operation {
            add, set, carve
        }
    }
}
