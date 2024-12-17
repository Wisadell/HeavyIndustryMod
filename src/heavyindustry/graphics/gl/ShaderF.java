package heavyindustry.graphics.gl;

import arc.files.*;
import arc.graphics.gl.*;
import arc.util.*;

import static arc.Core.*;

/**
 * Shader that works only on and expects GL 3.0-compatible shaders instead of GL 2.0.
 *
 * @deprecated Mindustry automatically replaces the gl30 syntax, and this class is no longer used.
 */
@Deprecated
public class ShaderF extends Shader {
    public ShaderF(Fi vertex, Fi fragment) {
        super(vertex, fragment);
    }

    public ShaderF(String vertex, String fragment) {
        super(vertex, fragment);
    }

    @Override
    protected String preprocess(String source, boolean fragment) {
        if (source.contains("#ifdef GL_ES"))
            throw new ArcRuntimeException("Shader contains GL_ES specific code; this should be handled by the preprocessor. Code: \n```\n" + source + "\n```");

        if (source.contains("#version"))
            throw new ArcRuntimeException("Shader contains explicit version requirement; this should be handled by the preprocessor. Code: \n```\n" + source + "\n```");

        if (fragment) {
            source =
            "#ifdef GL_ES\n" +
            "precision " + (source.contains("#define HIGHP") && !source.contains("//#define HIGHP") ? "highp" : "mediump") + " float;\n" +
            "precision mediump int;\n" +
            "#else\n" +
            "#define lowp   \n" +
            "#define mediump   \n" +
            "#define highp   \n" +
            "#endif\n" + source;
        } else {
            source =
            "#ifndef GL_ES\n" +
            "#define lowp   \n" +
            "#define mediump   \n" +
            "#define highp   \n" +
            "#endif\n" + source;
        }

        return
            "#version " + (app.isDesktop() ? (graphics.getGLVersion().atLeast(3, 2) ? "150" : "130") : "300 es") +
            "\n" + source;
    }
}
