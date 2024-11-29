package heavyindustry.ui.display;

import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.ui.*;

import static mindustry.Vars.*;

public class ArrowDisplay extends Table {
    public ArrowDisplay(float amount, boolean isInput) {
        add(new Stack() {{
            Image image = new Image(Icon.right).setScaling(Scaling.fit);
            image.setWidth(height * 2.5f);
            add(image);

            if (amount > 0) {
                add(new Table(t -> {
                    t.left().bottom();
                    t.add((isInput ? "[accent]" : "[remove]") + amount + "K").style(Styles.outlineLabel);
                    t.pack();
                }));
            }

        }}).size(iconMed).padRight(3 + (amount != 0 && Strings.autoFixed(amount, 2).length() > 2 ? 8 : 0));
    }
}
