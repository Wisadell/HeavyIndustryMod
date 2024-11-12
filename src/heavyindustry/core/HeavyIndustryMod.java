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
public final class HeavyIndustryMod extends Mod {
    /** Commonly used static read-only String. do not change unless you know what you're doing. */
    public static final String modName = "heavy-industry";

    /** Omitting longer mod names is generally used to load mod sprites. */
    public static String name(String add){
        return modName + "-" + add;
    }

    public static final boolean onlyPlugIn = settings.getBool("hi-plug-in-mode"), developerMode = settings.getBool("hi-developer-mode");

    private static final String linkGitHub = "https://github.com/Wisadell/HeavyIndustryMod", author = "Wisadell";

    public static LoadedMod modInfo;

    public HeavyIndustryMod(){
        Log.info("Loaded HeavyIndustry Mod constructor.");

        HIClassMap.load();

        Events.on(ClientLoadEvent.class, e -> {
            HIIcon.load();

            if(onlyPlugIn) return;

            showDialog();
            showNoMultipleMods();
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
            TableUtils.init();
        }

        settings.defaults("hi-closed-dialog", false);
        settings.defaults("hi-closed-multiple-mods", false);
        settings.defaults("hi-tesla-range", true);
        settings.defaults("hi-plug-in-mode", false);

        mod().meta.hidden = onlyPlugIn;
        if(onlyPlugIn){
            mod().meta.displayName = mod().meta.displayName + "PlugIn";
            mod().meta.version = mod().meta.version + "-plug-in";
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
                t.checkPref("hi-closed-dialog", false);
                t.checkPref("hi-closed-multiple-mods", false);
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
                t.checkPref("hi-developer-mode", false);
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

    private static void showDialog(){
        if (settings.getBool("hi-closed-dialog")) return;

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
                t.image(atlas.find(modName + "-cover")).left().size(600f, 338f).pad(3f).row();
                t.add(bundle.get("hi-version")).left().growX().wrap().pad(4f).labelAlign(Align.left).row();
                t.add(label).left().row();
                t.add(bundle.get("hi-class")).left().growX().wrap().pad(4).labelAlign(Align.left).row();
                t.add(bundle.get("hi-note")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                t.add(bundle.get("hi-prompt")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                t.add(bundle.get("hi-other")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
            }).grow().center().maxWidth(600f);
        }};
        dialog.show();
    }

    private static void showNoMultipleMods(){
        if (settings.getBool("hi-closed-multiple-mods")) return;

        boolean announces = true;

        for(LoadedMod mod : mods.orderedMods()) if(!modName.equals(mod.meta.name) && !mod.meta.hidden){
            announces = false;
            break;
        }

        if(announces) return;
        BaseDialog dialog = new BaseDialog("oh-no"){
            float time = 300f;
            boolean canClose;
        {
            update(() -> canClose = (time -= Time.delta) <= 0f);
            cont.add(bundle.get("hi-multiple-mods"));
            buttons.button("", this::hide).update(b -> {
                b.setDisabled(!canClose);b.setText(canClose ? "@close" : String.format("%s(%ss)", "@close", Strings.fixed(time / 60.0f, 1)));
            }).size(210f, 64f);
        }};
        dialog.show();
    }
}
