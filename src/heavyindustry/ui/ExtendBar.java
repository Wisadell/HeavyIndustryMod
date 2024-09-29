package heavyindustry.ui;

import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.util.pooling.*;
import mindustry.ui.*;

/**
 * Extend Bar.
 * @author LaoHuaJi
 */
public class ExtendBar extends Bar {
    public String icon;

    public ExtendBar(String name, Color color, Floatp fraction, String icon){
        super(name, color, fraction);
        this.icon = icon;
    }

    public ExtendBar(Prov<CharSequence> name, Prov<Color> color, Floatp fraction, String icon){
        super(name, color, fraction);
        this.icon = icon;
    }

    @Override
    public void draw(){
        super.draw();
        Font font = Fonts.outline;
        GlyphLayout lay = Pools.obtain(GlyphLayout.class, GlyphLayout::new);
        lay.setText(font, icon);

        font.setColor(1f, 1f, 1f, 1f);
        font.getCache().clear();
        font.getCache().addText(icon, x + 10f - lay.width / 2f, y + height / 2f + lay.height / 2f + 1);
        font.getCache().draw(parentAlpha);

        Pools.free(lay);
    }
}
