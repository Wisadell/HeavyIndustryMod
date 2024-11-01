package heavyindustry.world.blocks.units;

import arc.graphics.g2d.*;
import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.storage.*;

import static mindustry.Vars.*;

public class AdaptPayloadSource extends PayloadSource {
    public static final Team[] teams = new Team[]{Team.derelict, Team.sharded, Team.crux, Team.green, Team.malis, Team.blue};

    public AdaptPayloadSource(String name) {
        super(name);
        noUpdateDisabled = false;
        unitCapModifier = 1;
        targetable = false;
        underBullets = true;
        destructible = false;

        config(Block.class, (AdaptPayloadSourceBuild build, Block block) -> {
            if(canProduce(block) && build.configBlock != block){
                build.configBlock = block;
                build.unit = null;
                build.payload = null;
                build.scl = 0f;
            }
        });

        config(UnitType.class, (AdaptPayloadSourceBuild build, UnitType unit) -> {
            if(canProduce(unit) && build.unit != unit){
                build.unit = unit;
                build.configBlock = null;
                build.payload = null;
                build.scl = 0f;
            }
        });

        config(Integer.class, (AdaptPayloadSourceBuild build, Integer i) -> {
            build.unit = null;
            build.configBlock = null;
            build.payload = null;
            build.scl = 0;
        });

        configClear((AdaptPayloadSourceBuild build) -> {
            build.configBlock = null;
            build.unit = null;
            build.payload = null;
            build.scl = 0f;
        });
    }

    @Override
    public boolean canProduce(Block b) {
        return b.isVisible() && !(b instanceof CoreBlock) && !state.rules.isBanned(b) && b.environmentBuildable();
    }

    public class AdaptPayloadSourceBuild extends PayloadSourceBuild {
        @Override
        public void updateTile() {
            enabled = true;
            super.updateTile();
        }

        @Override
        public void buildConfiguration(Table table){
            ButtonGroup<ImageButton> g = new ButtonGroup<>();
            Table cont = new Table();
            cont.defaults().size(55);
            int i = 0;
            for(; i < teams.length; i++){
                Team team1 = teams[i];
                ImageButton button = cont.button(((TextureRegionDrawable)Tex.whiteui).tint(team1.color), Styles.clearTogglei, 35, () -> {}).group(g).get();
                button.changed(() -> {
                    if(button.isChecked()) {
                        if(player.team() == team){
                            configure(team1.id);
                        } else deselect();
                    }
                });
                button.update(() -> button.setChecked(team == team1));
            }
            table.add(cont).maxHeight(Scl.scl(55 * 2)).left();
            table.row();
            ItemSelection.buildTable(AdaptPayloadSource.this, table,
                    content.blocks().select(AdaptPayloadSource.this::canProduce).<UnlockableContent>as()
                            .add(content.units().select(AdaptPayloadSource.this::canProduce).as()),
                    () -> (UnlockableContent)config(), this::configure, false, selectionRows, selectionColumns);
        }

        @Override
        public void configure(Object value) {
            if(player.team() == team) super.configure(value);
            else deselect();
        }

        @Override
        public void configured(Unit builder, Object value) {
            super.configured(builder, value);
            if(!(value instanceof Integer v)) return;
            if (builder != null && builder.isPlayer()) {
                Team team = Team.get(v);
                builder.team = team;
                builder.getPlayer().team(team);

                onRemoved();
                changeTeam(team);
                onProximityUpdate();
            }
        }

        @Override
        public void draw() {
            Draw.rect(region, x, y);
            Draw.rect(outRegion, x, y, rotdeg());
            Draw.color(team.color);
            Draw.rect(teamRegion, x, y);
            Draw.color();
            Draw.scl(scl);
            drawPayload();
            Draw.reset();
        }

        @Override
        public void damage(float damage) {}

        @Override
        public boolean canPickup() {
            return false;
        }
    }
}
