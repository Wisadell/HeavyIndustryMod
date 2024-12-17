package heavyindustry.ui.fragment;

import arc.scene.*;
import arc.scene.event.*;
import arc.scene.ui.layout.*;
import heavyindustry.world.blocks.environment.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;

import static mindustry.Vars.*;

public final class CliffPlacerFragment extends Table {
    Table layout;

    public CliffPlacerFragment() {
        setFillParent(true);
        visible(() -> ui.hudfrag.shown && state.isEditor() && state.isPlaying() && control.input.commandMode);
        touchable(() -> visible ? Touchable.enabled : Touchable.disabled);
        right();

        add(layout = new Table(Tex.buttonSideLeft, t -> {
            t.table(Tex.underlineOver, title -> {
                title.label(() -> "@hi-cliff-placer").color(Pal.accent);
            }).growX().padBottom(10f).row();
            t.table(Styles.black3, buttons -> {
                buttons.button("@hi-process-cliffs", Icon.play, Styles.nonet, iconSmall, CliffF::processCliffs).growX().height(35f).pad(5f).row();
                buttons.button("@hi-un-process-cliffs", Icon.undo, Styles.nonet, iconSmall, CliffF::unProcessCliffs).growX().height(35f).pad(5f);
            }).margin(10f).growX();
        })).margin(10f);
    }

    public CliffPlacerFragment build(Group parent) {
        parent.addChildAt(0, this);
        return this;
    }
}
