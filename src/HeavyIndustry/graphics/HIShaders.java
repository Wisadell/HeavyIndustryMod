package HeavyIndustry.graphics;

import HeavyIndustry.graphics.gl.DepthAtmosphereShader;
import HeavyIndustry.graphics.gl.DepthShader;
import arc.files.Fi;
import arc.graphics.Color;
import arc.graphics.Texture;
import arc.graphics.g2d.Draw;
import arc.graphics.gl.Shader;
import arc.math.geom.Vec3;
import arc.util.Nullable;
import arc.util.Time;
import mindustry.graphics.Shaders;
import mindustry.type.Planet;

import static arc.Core.*;
import static mindustry.Vars.*;

public class HIShaders {
    public static DepthShader depth;
    public static DepthAtmosphereShader depthAtmosphere;
    public static @Nullable SurfaceShader nanofluid;
    public static PlanetTextureShader planetTextureShader;

    public static void init(){
        depth = new DepthShader();
        depthAtmosphere = new DepthAtmosphereShader();
        nanofluid = new SurfaceShader("nanofluid");
        planetTextureShader = new PlanetTextureShader();
    }

    public static void dispose(){
        if(!headless){
            nanofluid.dispose();
        }
    }

    public static Fi file(String name){
        return tree.get("shaders/" + name);
    }

    public static class PlanetTextureShader extends OlLoadShader{
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

    public static class OlLoadShader extends Shader{

        public OlLoadShader(String fragment, String vertex){
            super(
                    file(vertex + ".vert"),
                    file(fragment + ".frag")
            );
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

    public static class SurfaceShader extends Shader {
        Texture noiseTex;

        public SurfaceShader(String frag) {
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
        public void apply() {
            setUniformf("u_campos",
                    camera.position.x - camera.width / 2,
                    camera.position.y - camera.height / 2
            );
            setUniformf("u_ccampos", camera.position);
            setUniformf("u_resolution", camera.width, camera.height);
            setUniformf("u_rresolution", graphics.getWidth(), graphics.getHeight());
            setUniformf("u_time", Time.time);

            if(hasUniform("u_noise")) {
                if(noiseTex == null) {
                    noiseTex = assets.get("sprites/" + textureName() + ".png", Texture.class);
                }

                noiseTex.bind(1);
                renderer.effectBuffer.getTexture().bind(0);

                setUniformi("u_noise", 1);
            }
        }
    }
}
