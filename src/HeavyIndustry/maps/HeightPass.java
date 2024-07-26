package HeavyIndustry.maps;

import arc.math.*;
import arc.math.geom.*;
import arc.util.noise.*;

public abstract class HeightPass {
    public abstract float height(Vec3 pos, float height);

    public static class CraterHeight extends HeightPass {
        public Vec3 position;
        public float radius, offset;
        public boolean set = false;

        public CraterHeight(Vec3 position, float radius, float offset) {
            this.position = position;
            this.radius = radius;
            this.offset = offset;
        }

        @Override
        public float height(Vec3 pos, float height) {
            if (pos.dst(position) < radius) return offset + height * (set ? 0f : 1f);
            return height;
        }
    }

    public static class NoiseHeight extends HeightPass {
        public Vec3 offset = new Vec3();
        public int seed;
        public double octaves = 1.0, persistence = 1.0, scale = 1.0;
        public float magnitude = 1, heightOffset = 0;

        @Override
        public float height(Vec3 pos, float height) {
            pos = new Vec3(pos).add(offset);
            return Simplex.noise3d(seed, octaves, persistence, scale, pos.x, pos.y, pos.z) * magnitude + heightOffset + height;
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

    public static class AngleInterpHeight extends HeightPass {
        public Vec3 dir = new Vec3();
        public Interp interp = Interp.linear;
        public float magnitude = 1;

        @Override
        public float height(Vec3 pos, float height) {
            return interp.apply(1f - pos.angle(dir)/180f) * magnitude + height;
        }
    }
}
