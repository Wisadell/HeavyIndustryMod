package heavyindustry.graphics.g3d.model.obj;

import arc.files.*;
import arc.graphics.gl.*;

public class ObjectShader extends Shader {
    public ObjectShader(Fi vertexShader, Fi fragmentShader) {
        super(vertexShader, fragmentShader);
    }

    public ObjectShader(String vertexShader, String fragmentShader) {
        super(vertexShader, fragmentShader);
    }
}
