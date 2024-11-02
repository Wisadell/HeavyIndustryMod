package heavyindustry.core;

import heavyindustry.content.*;
import heavyindustry.game.*;
import heavyindustry.gen.*;
import heavyindustry.graphics.*;
import heavyindustry.ui.*;
import heavyindustry.ui.dialogs.*;
import arc.*;
import arc.flabel.*;
import arc.scene.ui.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.mod.Mods.*;
import mindustry.ui.dialogs.*;

import java.util.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * Main entry point of the mod. Handles startup things like content loading, entity registering, and utility bindings.
 * @author Wisadell
 */
public class HeavyIndustryMod extends Mod {
    /** Commonly used static read-only String. Do not attempt to modify it unless you know what you are doing. */
    public static final String modName = "heavy-industry";

    /** Omitting longer mod names is generally used to load mod sprites. */
    public static String name(String add){
        return modName + "-" + add;
    }

    public static final boolean onlyPlugIn = settings.getBool("hi-plug-in-mode");

    private static final String linkGitHub = "https://github.com/Wisadell/HeavyIndustryMod", author = "Wisadell";

    public static LoadedMod modInfo;

    public HeavyIndustryMod(){
        Log.info("Loaded HeavyIndustry Mod constructor.");

        HIClassMap.load();

        Events.on(ClientLoadEvent.class, e -> {
            HIIcon.load();
            if(onlyPlugIn || settings.getBool("hi-homepage-dialog")) return;
            FLabel label = new FLabel(bundle.get("hi-author") + author);
            BaseDialog dialog = new BaseDialog(bundle.get("hi-name")){{
                buttons.button("@close", this::hide).size(210f, 64f);
                buttons.button((bundle.get("hi-link-github")), () -> {
                    if (!app.openURI(linkGitHub)) {
                        ui.showErrorMessage("@linkfail");
                        app.setClipboardText(linkGitHub);
                    }
                }).size(210f, 64f);
                cont.pane(t -> {
                    t.image(atlas.find(name("cover"))).left().size(600f, 338f).pad(3f).row();
                    t.add(bundle.get("hi-version")).left().growX().wrap().pad(4f).labelAlign(Align.left).row();
                    t.add(label).left().row();
                    t.add(bundle.get("hi-class")).left().growX().wrap().pad(4).labelAlign(Align.left).row();
                    t.add(bundle.get("hi-note")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                    t.add(bundle.get("hi-prompt")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                    t.add(bundle.get("hi-contributor")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                    t.add(bundle.get("hi-close-homepage-dialog")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                }).grow().center().maxWidth(600f);
            }};
            dialog.show();
        });

        Events.on(FileTreeInitEvent.class, e -> {
            HISounds.load();
            app.post(() -> {
                HIShaders.init();
                HICacheLayer.init();
            });
        });

        Events.on(DisposeEvent.class, e -> {
            HIShaders.dispose();
        });

        app.post(() -> modInfo = mods.getMod(HeavyIndustryMod.class));
    }

    @Override
    public void loadContent(){
        if (modInfo == null) Log.warn("modInfo is null.");
        HIRegister.load();

        if(onlyPlugIn) return;

        HITeams.load();
        HIItems.load();
        HIStatusEffects.load();
        HILiquids.load();
        HIBullets.load();
        HIUnitTypes.load();
        HIBlocks.load();
        HIWeathers.load();
        HIOverride.loadReflect();
        HIOverride.load();
        HIPlanets.load();
        HISectorPresets.load();
        HITechTree.load();
    }

    @Override
    public void init(){
        super.init();
        if(!headless){
            HIDialog.init();
        }

        settings.defaults("hi-homepage-dialog", false);
        settings.defaults("hi-tesla-range", true);
        settings.defaults("hi-plug-in-mode", false);

        mods.locateMod(modName).meta.hidden = onlyPlugIn;
        if(onlyPlugIn){
            Mods.LoadedMod mod = mods.locateMod(modName);
            mod.meta.displayName = mod.meta.displayName + "-Plug-In";
            mod.meta.version = mods.locateMod(modName).meta.version + "-plug-in";
        }

        if(ui != null && ui.settings != null){
            BaseDialog dialog = new BaseDialog("tips");
            Runnable exit = () -> {
                dialog.hide();
                app.exit();
            };
            dialog.cont.add(bundle.format("hi-reset-exit"));
            dialog.buttons.button("@confirm", exit).center().size(150, 50);

            ui.settings.addCategory(bundle.format("hi-settings"), HIIcon.reactionIcon, t -> {
                t.checkPref("hi-homepage-dialog", false);
                t.checkPref("hi-tesla-range", true);
                t.checkPref("hi-enable-serpulo-sector-invasion", true);
                t.pref(new SettingsMenuDialog.SettingsTable.CheckSetting("hi-plug-in-mode", false, null) {
                    @Override
                    public void add(SettingsMenuDialog.SettingsTable table) {
                        CheckBox box = new CheckBox(title);

                        box.update(() -> box.setChecked(settings.getBool(name)));

                        box.changed(() -> {
                            settings.put(name, box.isChecked());
                            dialog.show();
                        });
                        box.left();
                        addDesc(table.add(box).left().padTop(3f).get());
                        table.row();
                    }
                });
            });
        }

        //Replace the original technology tree
        HIResearchDialog dialog = new HIResearchDialog();
        ResearchDialog research = ui.research;
        research.shown(() -> {
            dialog.show();
            Objects.requireNonNull(research);
            Time.runTask(1f, research::hide);
        });
    }

    public static LoadedMod mod(){
        return modInfo;
    }
}
