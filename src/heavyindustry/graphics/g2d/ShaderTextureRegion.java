package heavyindustry.graphics.g2d;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.gl.*;
import arc.struct.*;
import mindustry.game.EventType.*;

public class ShaderTextureRegion extends TextureRegion {
    public static final Seq<ShaderTextureRegion> regions = new Seq<>();

    static {
        Events.run(Trigger.update, () -> regions.each(ShaderTextureRegion::updateShader));
    }

    private final Cons2<Shader, Object[]> shaderPrepare;
    private final FrameBuffer frameBuffer = new FrameBuffer();
    public Texture original;
    /** Texture update frequency, in frames. */
    public int frequency = 5;
    public Shader shader;
    /** Use to transfer shader parameters to {@link ShaderTextureRegion#shaderPrepare}. */
    public Object[] shaderPrepareParams;
    private int counter = 0;

    public ShaderTextureRegion(Shader shader, Texture original, Cons2<Shader, Object[]> shaderPrepare, int shaderPrepareParamsSize) {
        regions.add(this);

        shaderPrepareParams = new Object[shaderPrepareParamsSize];
        this.shader = shader;
        this.shaderPrepare = shaderPrepare;

        this.original = original;
        set(original);
    }

    public void remove() {
        regions.remove(this);
    }

    public void updateShader() {
        counter++;
        if ((counter %= frequency) == 0) {
            Draw.flush();
            Draw.reset();
            frameBuffer.resize(width, height);
            shaderPrepare.get(shader, shaderPrepareParams);
            frameBuffer.begin(Color.black.cpy().a(0f));

            Draw.blit(original, shader);
            Draw.flush();

            frameBuffer.end();
            set(frameBuffer.getTexture());
        }
    }
}
