package HeavyIndustry;

import HeavyIndustry.content.*;
import arc.*;
import arc.flabel.FLabel;
import arc.scene.ui.Label;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class HeavyIndustryMod extends Mod{
    public static String ModName = "heavy-industry";
    public static String name(String add){
        return ModName + "-" + add;
    }
    public HeavyIndustryMod(){
        Log.info("Loaded HeavyIndustry constructor.");
        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Heavy Industry Mod");
                dialog.buttons.button("@close", dialog::hide).size(100f, 50f);
                dialog.cont.pane(table -> {
                    table.image(Core.atlas.find(name("img"))).left().row();
                    table.add("Test Page").left().growX().wrap().pad(4).labelAlign(Align.left).row();
                    Label flabel1 = new FLabel(" Current version of mod: 1.1.0.1");
                    table.add(flabel1).left().row();
                    table.add("Heavy Industry Java Mod\nA large number of missing textures in Mod is a normal phenomenon, and you don't need to worry about it. I will try my best to improve them in the future.\n").left().growX().wrap().width(500).maxWidth(550).pad(4).labelAlign(Align.left).row();
                }).grow().center().maxWidth(600);
                dialog.show();
            });
        });
    }
    @Override
    public void loadContent(){
        Log.info("Loading some example content.");
        HISounds.load();
        HIAttribute.load();
        HIStatusEffects.load();
        HIUnitTypes.load();
        HIBlocks.load();
        HIOverride.overrideBlocks();
        HIOverride.overrideUnit();
        HIOverride.overrideLiquids();
        HIOverride.overrideItem();
        HISectorPresets.load();
        HITechTree.load();
    }
}
