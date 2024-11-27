package heavyindustry.graphics;

import arc.func.*;
import arc.graphics.*;
import arc.graphics.Texture.*;
import mindustry.graphics.*;
import mindustry.graphics.CacheLayer.*;
import heavyindustry.core.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * Defines the {@linkplain CacheLayer cache layer}s this mod offers.
 * @author Wisadell
 */
public final class HICacheLayer {
    public static ShaderLayer dalani,brine,nanofluid,armor;

    /** HICacheLayer should not be instantiated. */
    private HICacheLayer() {}

    /** Loads the cache layers. */
    public static void init() {
        Textures.load();

        dalani = new ShaderLayer(HIShaders.dalani);
        brine = new ShaderLayer(HIShaders.brine);
        nanofluid = new ShaderLayer(HIShaders.nanofluid);

        armor = new ShaderLayer(HIShaders.tiler) {
            @Override
            public void begin() {
                renderer.blocks.floor.endc();
                renderer.effectBuffer.begin();
                graphics.clear(Color.clear);
                renderer.blocks.floor.beginc();
            }

            @Override
            public void end() {
                renderer.blocks.floor.endc();
                renderer.effectBuffer.end();

                HIShaders.tiler.texture = Textures.armor;
                renderer.effectBuffer.blit(shader);

                renderer.blocks.floor.beginc();
            }
        };

        CacheLayer.add(dalani,brine,nanofluid,armor);
    }

    public static final class Textures {
        public static Texture smooth,particle,darker,armor;

        /** Textures should not be instantiated. */
        private Textures() {}

        public static void load() {
            smooth = loadTexture("smooth-noise", t -> {
                t.setFilter(TextureFilter.linear);
                t.setWrap(TextureWrap.repeat);
            });
            particle = loadTexture("particle-noise", t -> {
                t.setFilter(TextureFilter.linear);
                t.setWrap(TextureWrap.repeat);
            });
            darker = loadTexture("darker-noise", t -> {
                t.setFilter(TextureFilter.linear);
                t.setWrap(TextureWrap.repeat);
            });
            armor = loadTexture("armor", t -> {
                t.setFilter(TextureFilter.nearest);
                t.setWrap(TextureWrap.repeat);
            });
        }

        public static Texture loadTexture(String name, Cons<Texture> modifier) {
            Texture tex = new Texture(HeavyIndustryMod.modInfo.root.child("textures").child(name + (name.endsWith(".png") ? "" : ".png")));
            modifier.get(tex);

            return tex;
        }
    }
}
