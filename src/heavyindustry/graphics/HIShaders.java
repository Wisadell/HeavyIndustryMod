package heavyindustry.graphics;

import arc.files.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g3d.*;
import arc.graphics.gl.*;
import arc.math.geom.*;
import arc.util.*;
import heavyindustry.type.*;
import mindustry.*;
import mindustry.type.*;

import static arc.Core.*;
import static heavyindustry.core.HeavyIndustryMod.*;
import static mindustry.Vars.*;

/**
 * Defines the {@linkplain Shader shader}s this mod offers.
 *
 * @author E-Nightingale
 */
public final class HIShaders {
    public static DepthShader depth;
    public static DepthAtmosphereShader depthAtmosphere;
    public static AlphaShader alphaShader;
    public static SurfaceShaderF dalani, brine, nanofluid, boundWater, pit, waterPit;
    public static MaskShader alphaMask;
    public static WaveShader wave;
    public static MirrorFieldShader mirrorField;
    public static LoadShaderF baseShader;
    public static Tiler tiler;
    public static PlanetTextureShader planetTextureShader;

    /** HIShaders should not be instantiated. */
    private HIShaders() {}

    /** Loads the shaders. */
    public static void init() {
        String prevVert = Shader.prependVertexCode, prevFrag = Shader.prependFragmentCode;
        Shader.prependVertexCode = Shader.prependFragmentCode = "";

        if (graphics.getGLVersion().type == GLVersion.GlType.OpenGL)
            Shader.prependFragmentCode = "#define HAS_GL_FRAGDEPTH\n";

        depth = new DepthShader();
        depthAtmosphere = new DepthAtmosphereShader();

        alphaShader = new AlphaShader();

        dalani = new SurfaceShaderF("dalani");
        brine = new SurfaceShaderF("brine");
        nanofluid = new SurfaceShaderF("nanofluid");
        boundWater = new SurfaceShaderF("boundwater");
        pit = new PitShader("pit", name("concrete-blank1"), name("stone-sheet"), name("truss"));
        waterPit = new PitShader("water-pit", name("concrete-blank1"), name("stone-sheet"), name("truss"));

        alphaMask = new MaskShader();
        mirrorField = new MirrorFieldShader();
        wave = new WaveShader();
        baseShader = new LoadShaderF("screenspace", "distbase");

        tiler = new Tiler();

        planetTextureShader = new PlanetTextureShader();

        Shader.prependVertexCode = prevVert;
        Shader.prependFragmentCode = prevFrag;
    }

    public static void dispose() {
        if (!headless) {
            dalani.dispose();
            brine.dispose();
            nanofluid.dispose();
            boundWater.dispose();
        }
    }

    /**
     * Resolves shader files from this mod via {@link Vars#tree}.
     *
     * @param name The shader file name, e.g. {@code my-shader.frag}.
     * @return The shader file, located inside {@code shaders/}.
     */
    public static Fi file(String name) {
        return tree.get("shaders/" + name);
    }

    /**
     * An atmosphere shader that incorporates the planet shape in a form of depth texture. Better quality, but at the little
     * cost of performance.
     */
    public static class DepthAtmosphereShader extends LoadShaderF {
        private static final Mat3D mat = new Mat3D();

        public Camera3D camera;
        public BetterPlanet planet;

        public DepthAtmosphereShader() {
            super("depth-atmosphere", "depth-atmosphere");
        }

        @Override
        public void apply() {
            setUniformMatrix4("u_proj", camera.combined.val);
            setUniformMatrix4("u_trans", planet.getTransform(mat).val);

            setUniformf("u_camPos", camera.position);
            setUniformf("u_relCamPos", Tmp.v31.set(camera.position).sub(planet.position));
            setUniformf("u_camRange", camera.near, camera.far - camera.near);
            setUniformf("u_center", planet.position);
            setUniformf("u_light", planet.getLightNormal());
            setUniformf("u_color", planet.atmosphereColor.r, planet.atmosphereColor.g, planet.atmosphereColor.b);

            setUniformf("u_innerRadius", planet.radius + planet.atmosphereRadIn);
            setUniformf("u_outerRadius", planet.radius + planet.atmosphereRadOut);

            planet.depthBuffer.getTexture().bind(0);
            setUniformi("u_topology", 0);
            setUniformf("u_viewport", graphics.getWidth(), graphics.getHeight());
        }
    }

    /** Specialized mesh shader to capture fragment depths. */
    public static class DepthShader extends LoadShaderF {
        public Camera3D camera;

        public DepthShader() {
            super("depth", "depth");
        }

        @Override
        public void apply() {
            setUniformf("u_camPos", camera.position);
            setUniformf("u_camRange", camera.near, camera.far - camera.near);
        }
    }

    public static class PlanetTextureShader extends LoadShaderF {
        public Vec3 lightDir = new Vec3(1, 1, 1).nor();
        public Color ambientColor = Color.white.cpy();
        public Vec3 camDir = new Vec3();
        public float alpha = 1f;
        public Planet planet;

        public PlanetTextureShader() {
            super("circle-mesh", "circle-mesh");
        }

        @Override
        public void apply() {
            camDir.set(renderer.planets.cam.direction).rotate(Vec3.Y, planet.getRotation());

            setUniformf("u_alpha", alpha);
            setUniformf("u_lightdir", lightDir);
            setUniformf("u_ambientColor", ambientColor.r, ambientColor.g, ambientColor.b);
            setPlanetInfo("u_sun_info", planet.solarSystem);
            setPlanetInfo("u_planet_info", planet);
            setUniformf("u_camdir", camDir);
            setUniformf("u_campos", renderer.planets.cam.position);
        }

        private void setPlanetInfo(String name, Planet planet) {
            Vec3 position = planet.position;
            Shader shader = this;
            shader.setUniformf(name, position.x, position.y, position.z, planet.radius);
        }
    }

    public static class Tiler extends LoadShaderF {
        public Texture texture = atlas.white().texture;
        public float scl = 4f;

        public Tiler() {
            super("screenspace", "tiler");
        }

        @Override
        public void apply() {
            setUniformf("u_offset", camera.position.x - camera.width / 2, camera.position.y - camera.height / 2);
            setUniformf("u_texsize", camera.width, camera.height);
            setUniformf("u_tiletexsize", texture.width / scl, texture.height / scl);

            texture.bind(1);
            renderer.effectBuffer.getTexture().bind(0);

            setUniformi("u_tiletex", 1);
        }
    }

    public static class AlphaShader extends LoadShaderF {
        public float alpha = 1f;

        public AlphaShader() {
            super("screenspace", "postalpha");
        }

        @Override
        public void apply() {
            setUniformf("u_alpha", alpha);
        }
    }

    public static class WaveShader extends LoadShaderF {
        public Color waveMix = Color.white;
        public float mixAlpha = 0.4f;
        public float mixOmiga = 0.75f;
        public float maxThreshold = 0.9f;
        public float minThreshold = 0.6f;
        public float waveScl = 0.2f;

        public WaveShader() {
            super("screenspace", "wave");
        }

        @Override
        public void apply() {
            setUniformf("u_campos", camera.position.x - camera.width / 2, camera.position.y - camera.height / 2);
            setUniformf("u_resolution", camera.width, camera.height);
            setUniformf("u_time", Time.time);

            setUniformf("mix_color", waveMix);
            setUniformf("mix_alpha", mixAlpha);
            setUniformf("mix_omiga", mixOmiga);
            setUniformf("wave_scl", waveScl);
            setUniformf("max_threshold", maxThreshold);
            setUniformf("min_threshold", minThreshold);
        }
    }

    public static class MirrorFieldShader extends LoadShaderF {
        public Color waveMix = Color.white;
        public Vec2 offset = new Vec2(0, 0);
        public float stroke = 2;
        public float gridStroke = 0.8f;
        public float mixAlpha = 0.4f;
        public float alpha = 0.2f;
        public float maxThreshold = 1;
        public float minThreshold = 0.7f;
        public float waveScl = 0.03f;
        public float sideLen = 10;

        public MirrorFieldShader() {
            super("screenspace", "mirrorfield");
        }

        @Override
        public void apply() {
            setUniformf("u_campos", camera.position.x - camera.width / 2, camera.position.y - camera.height / 2);
            setUniformf("u_resolution", camera.width, camera.height);
            setUniformf("u_time", Time.time);

            setUniformf("offset", offset);
            setUniformf("u_step", stroke);
            setUniformf("mix_color", waveMix);
            setUniformf("mix_alpha", mixAlpha);
            setUniformf("u_stroke", gridStroke);
            setUniformf("u_alpha", alpha);
            setUniformf("wave_scl", waveScl);
            setUniformf("max_threshold", maxThreshold);
            setUniformf("min_threshold", minThreshold);
            setUniformf("side_len", sideLen);
        }
    }

    public static class MaskShader extends LoadShaderF {
        public Texture texture;

        public MaskShader() {
            super("screenspace", "alphamask");
        }

        @Override
        public void apply() {
            setUniformi("u_texture", 1);
            setUniformi("u_mask", 0);

            texture.bind(1);
        }
    }

    /** SurfaceShader but uses a mod fragment asset. */
    public static class PitShader extends SurfaceShaderF {
        protected TextureRegion topLayer, bottomLayer, truss;
        protected String topLayerName, bottomLayerName, trussName;

        public PitShader(String fragment, String topLayer, String bottomLayer, String truss) {
            super(fragment);
            topLayerName = topLayer;
            bottomLayerName = bottomLayer;
            trussName = truss;
        }

        @Override
        public void apply() {
            var texture = atlas.find("grass1").texture;
            if (topLayer == null)
                topLayer = atlas.find(topLayerName);

            if (bottomLayer == null)
                bottomLayer = atlas.find(bottomLayerName);

            if (truss == null)
                truss = atlas.find(trussName);

            if (noiseTex == null)
                noiseTex = assets.get("sprites/" + textureName() + ".png", Texture.class);

            setUniformf("u_campos", camera.position.x - camera.width / 2, camera.position.y - camera.height / 2);
            setUniformf("u_resolution", camera.width, camera.height);
            setUniformf("u_time", Time.time);

            setUniformf("u_toplayer", topLayer.u, topLayer.v, topLayer.u2, topLayer.v2);
            setUniformf("u_bottomlayer", bottomLayer.u, bottomLayer.v, bottomLayer.u2, bottomLayer.v2);
            setUniformf("bvariants", bottomLayer.width / 32f);
            setUniformf("u_truss", truss.u, truss.v, truss.u2, truss.v2);

            texture.bind(2);
            noiseTex.bind(1);
            renderer.effectBuffer.getTexture().bind(0);
            setUniformi("u_noise", 1);
            setUniformi("u_texture2", 2);
        }
    }

    public static class SurfaceShaderF extends LoadShaderF {
        Texture noiseTex;

        public SurfaceShaderF(String fragment) {
            super("screenspace", fragment);
            loadNoise();
        }

        public String textureName() {
            return "noise";
        }

        public void loadNoise() {
            assets.load("sprites/" + textureName() + ".png", Texture.class).loaded = t -> {
                t.setFilter(Texture.TextureFilter.linear);
                t.setWrap(Texture.TextureWrap.repeat);
            };
        }

        @Override
        public void apply() {
            setUniformf("u_campos", camera.position.x - camera.width / 2, camera.position.y - camera.height / 2);
            setUniformf("u_resolution", camera.width, camera.height);
            setUniformf("u_time", Time.time);

            if (hasUniform("u_noise")) {
                if (noiseTex == null) {
                    noiseTex = assets.get("sprites/" + textureName() + ".png", Texture.class);
                }

                noiseTex.bind(1);
                renderer.effectBuffer.getTexture().bind(0);

                setUniformi("u_noise", 1);
            }
        }
    }

    public static class LoadShaderF extends Shader {
        public LoadShaderF(String vertex, String fragment) {
            super(file(vertex + ".vert"), file(fragment + ".frag"));
        }
    }
}
