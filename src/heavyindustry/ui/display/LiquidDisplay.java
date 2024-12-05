package heavyindustry.ui.display;

import arc.graphics.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

/** An ItemDisplay, but for liquids. */
public class LiquidDisplay extends Table {
    public final Liquid liquid;
    public final float amount;
    public final boolean perSecond;

    public LiquidDisplay(Liquid liquid, float amount, boolean perSecond) {
        this.liquid = liquid;
        this.amount = amount;
        this.perSecond = perSecond;

        add(new Stack() {{
            add(new Image(liquid.uiIcon).setScaling(Scaling.fit));

            if (amount != 0) {
                Table t = new Table().left().bottom();
                t.add(Strings.autoFixed(amount, 2)).style(Styles.outlineLabel);
                add(t);
            }
        }}).size(iconMed).padRight(3 + (amount != 0 && Strings.autoFixed(amount, 2).length() > 2 ? 8 : 0));

        if (perSecond) {
            add(StatUnit.perSecond.localized()).padLeft(2).padRight(5).color(Color.lightGray).style(Styles.outlineLabel);
        }

        add(liquid.localizedName);
    }
}
