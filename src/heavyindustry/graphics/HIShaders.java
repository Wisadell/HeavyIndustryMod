package heavyindustry.graphics;

import heavyindustry.graphics.gl.*;
import arc.files.*;
import arc.graphics.*;
import arc.graphics.gl.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * Defines the {@linkplain Shader shader}s this mod offers.
 * @author Wisadell
 */
public final class HIShaders {
    public static DepthShader depth;
    public static DepthAtmosphereShader depthAtmosphere;
    public static AlphaShader alphaShader;
    public static HISurfaceShader dalani,brine,nanofluid;
    public static Tiler tiler;
    public static PlanetTextureShader planetTextureShader;

    /** Loads the shaders. */
    public static void init(){
        depth = new DepthShader();
        depthAtmosphere = new DepthAtmosphereShader();

        alphaShader = new AlphaShader();

        dalani = new HISurfaceShader("dalani");
        brine = new HISurfaceShader("brine");
        nanofluid = new HISurfaceShader("nanofluid");

        tiler = new Tiler();

        planetTextureShader = new PlanetTextureShader();
    }

    public static void dispose(){
        if(!headless){
            nanofluid.dispose();
            dalani.dispose();
        }
    }

    /**
     * Resolves shader files from this mod via {@link Vars#tree}.
     * @param name The shader file name, e.g. {@code my-shader.frag}.
     * @return     The shader file, located inside {@code shaders/}.
     */
    public static Fi file(String name) {
        return tree.get("shaders/" + name);
    }

    public static class PlanetTextureShader extends HILoadShader {
        public Vec3 lightDir = new Vec3(1, 1, 1).nor();
        public Color ambientColor = Color.white.cpy();
        public Vec3 camDir = new Vec3();
        public float alpha = 1f;
        public Planet planet;

        public PlanetTextureShader(){
            super("circle-mesh", "circle-mesh");
        }

        @Override
        public void apply(){
            camDir.set(renderer.planets.cam.direction).rotate(Vec3.Y, planet.getRotation());

            setUniformf("u_alpha", alpha);
            setUniformf("u_lightdir", lightDir);
            setUniformf("u_ambientColor", ambientColor.r, ambientColor.g, ambientColor.b);
            setPlanetInfo("u_sun_info", planet.solarSystem);
            setPlanetInfo("u_planet_info", planet);
            setUniformf("u_camdir", camDir);
            setUniformf("u_campos", renderer.planets.cam.position);
        }

        private void setPlanetInfo(String name, Planet planet){
            Vec3 position = planet.position;
            Shader shader = this;
            shader.setUniformf(name, position.x, position.y, position.z, planet.radius);
        }
    }

    public static class Tiler extends HILoadShader {
        public Texture texture = atlas.white().texture;
        public float scl = 4f;

        public Tiler(){
            super("tiler", "screenspace");
        }

        @Override
        public void apply(){
            setUniformf("u_offset", camera.position.x - camera.width / 2, camera.position.y - camera.height / 2);
            setUniformf("u_texsize", camera.width, camera.height);
            setUniformf("u_tiletexsize", texture.width / scl, texture.height / scl);

            texture.bind(1);
            renderer.effectBuffer.getTexture().bind(0);

            setUniformi("u_tiletex", 1);
        }
    }

    public static class AlphaShader extends HILoadShader {
        public float alpha = 1f;

        public AlphaShader(){
            super("postalpha", "screenspace");
        }

        @Override
        public void apply(){
            setUniformf("u_alpha", alpha);
        }
    }

    public static class HILoadShader extends Shader {
        public HILoadShader(String fragment, String vertex){
            super(file(vertex + ".vert"), file(fragment + ".frag"));
        }
    }

    public static class HISurfaceShader extends Shader {
        Texture noiseTex;

        public HISurfaceShader(String frag) {
            super(Shaders.getShaderFi("screenspace.vert"), tree.get("shaders/" + frag + ".frag"));
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
        public void apply(){
            setUniformf("u_campos", camera.position.x - camera.width / 2, camera.position.y - camera.height / 2);
            setUniformf("u_resolution", camera.width, camera.height);
            setUniformf("u_time", Time.time);

            if(hasUniform("u_noise")){
                if(noiseTex == null){
                    noiseTex = assets.get("sprites/" + textureName() + ".png", Texture.class);
                }

                noiseTex.bind(1);
                renderer.effectBuffer.getTexture().bind(0);

                setUniformi("u_noise", 1);
            }
        }
    }
}
