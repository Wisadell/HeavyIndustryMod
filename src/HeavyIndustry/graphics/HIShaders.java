package HeavyIndustry.graphics;

import arc.graphics.Texture;
import arc.graphics.gl.Shader;
import arc.util.Nullable;
import arc.util.Time;
import mindustry.graphics.Shaders;

import static arc.Core.*;
import static mindustry.Vars.*;

public class HIShaders {
    public static @Nullable SurfaceShader nanofluid;

    public static void init(){
        nanofluid = new SurfaceShader("nanofluid");
    }

    public static void dispose(){
        if(!headless){
            nanofluid.dispose();
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
