package heavyindustry.graphics;

import arc.files.*;
import arc.graphics.*;
import arc.graphics.g3d.*;
import arc.graphics.gl.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.*;
import mindustry.type.*;
import heavyindustry.type.*;

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
            dalani.dispose();
            brine.dispose();
            nanofluid.dispose();
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

    /**
     * An atmosphere shader that incorporates the planet shape in a form of depth texture. Better quality, but at the little
     * cost of performance.
     */
    public static class DepthAtmosphereShader extends HILoadShader {
        private static final Mat3D mat = new Mat3D();

        public Camera3D camera;
        public BetterPlanet planet;

        public DepthAtmosphereShader(){
            super("depth-atmosphere","depth-atmosphere");
        }

        @Override
        public void apply(){
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

    /**
     * Specialized mesh shader to capture fragment depths.
     */
    public static class DepthShader extends HILoadShader {
        public Camera3D camera;

        public DepthShader(){
            super("depth", "depth");
        }

        @Override
        public void apply(){
            setUniformf("u_camPos", camera.position);
            setUniformf("u_camRange", camera.near, camera.far - camera.near);
        }
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
            super("screenspace", "tiler");
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
            super("screenspace", "postalpha");
        }

        @Override
        public void apply(){
            setUniformf("u_alpha", alpha);
        }
    }

    public static class HISurfaceShader extends HILoadShader {
        Texture noiseTex;

        public HISurfaceShader(String fragment) {
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

    public static class HILoadShader extends Shader {
        public HILoadShader(String vertex, String fragment){
            super(file(vertex + ".vert"), file(fragment + ".frag"));
        }
    }
}
