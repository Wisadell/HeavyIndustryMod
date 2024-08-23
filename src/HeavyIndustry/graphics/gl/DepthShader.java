package HeavyIndustry.graphics.gl;

import arc.graphics.g3d.*;
import arc.graphics.gl.*;

import static HeavyIndustry.graphics.HIShaders.*;

public class DepthShader extends Shader{
    public Camera3D camera;

    public DepthShader(){
        super(file("depth.vert"), file("depth.frag"));
    }

    @Override
    public void apply(){
        setUniformf("u_camPos", camera.position);
        setUniformf("u_camRange", camera.near, camera.far - camera.near);
    }
}