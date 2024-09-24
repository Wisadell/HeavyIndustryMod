package heavyindustry.graphics;

import heavyindustry.graphics.gl.*;
import arc.files.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.gl.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/** Defines the {@linkplain Shader shader}s this mod offers. */
public final class HIShaders {
    public static DepthShader depth;
    public static DepthAtmosphereShader depthAtmosphere;
    public static @Nullable HISurfaceShader nanofluid,dalani;
    public static PlanetTextureShader planetTextureShader;

    /** Loads the shaders. */
    public static void init(){
        String prevVert = Shader.prependVertexCode, prevFrag = Shader.prependFragmentCode;
        Shader.prependVertexCode = Shader.prependFragmentCode = "";

        if(graphics.getGLVersion().type == GLVersion.GlType.OpenGL){
            Shader.prependFragmentCode = "#define HAS_GL_FRAGDEPTH\n";
        }

        depth = new DepthShader();
        depthAtmosphere = new DepthAtmosphereShader();

        nanofluid = new HISurfaceShader("nanofluid");
        dalani = new HISurfaceShader("dalani");

        planetTextureShader = new PlanetTextureShader();

        Shader.prependVertexCode = prevVert;
        Shader.prependFragmentCode = prevFrag;
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
     * @return     The shader file, located inside {@code shaders/confictura/}.
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

    public static class HILoadShader extends Shader{
        public HILoadShader(String fragment, String vertex){
            super(file(vertex + ".vert"), file(fragment + ".frag"));
        }

        public void set(){
            Draw.shader(this);
        }

        @Override
        public void apply(){
            super.apply();

            setUniformf("u_time_millis", System.currentTimeMillis() / 1000f * 60f);
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
