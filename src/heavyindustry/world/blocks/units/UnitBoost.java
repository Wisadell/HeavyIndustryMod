package heavyindustry.world.blocks.units;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.style.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import arc.util.pooling.*;
import heavyindustry.util.*;
import heavyindustry.util.Utils.*;
import heavyindustry.world.meta.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class UnitBoost extends Block {
    public StatusEffect[] status;
    public int range = 22;
    public int boostRange = 5;
    public StatusEffect[] boostStatus;
    public boolean boostReplace = false;
    public float consumeTime = 240;

    public float heatRequirement = 10f;
    public float maxRangeBoost = 1.5f;

    public DrawBlock drawer = null;
    public boolean onlyDrawer = true;

    public UnitBoost(String name) {
        super(name);
        solid = update = true;
        destructible = true;
        canOverdrive = false;
    }

    @Override
    public void setBars() {
        super.setBars();

        addBar("heat", (UnitBoostBuild entity) -> new Bar(
                () -> bundle.format("bar.heatpercent", (int)(entity.heat + 0.01f), (int)(entity.efficiencyScale() * 100 + 0.01f)),
                () -> Pal.lightOrange,
                () -> entity.heat / heatRequirement));
    }

    @Override
    public void setStats() {
        stats.timePeriod = consumeTime;
        super.setStats();

        stats.add(Stat.input, heatRequirement, StatUnit.heatUnits);
        stats.add(Stat.range, range, StatUnit.blocks);
        stats.add(new Stat("rangeboost", StatCat.crafting), (int)(maxRangeBoost * 100f), StatUnit.percent);
        if (status.length >0) stats.add(Stat.abilities, t -> {
            t.row();
            t.add(bundle.get("hi-stat-value-show-status")).left();
            t.row();
            t.table(Styles.grayPanel, inner -> {
                inner.left().defaults().left();
                for (StatusEffect s : status) {
                    if (s == StatusEffects.none) continue;
                    inner.row();
                    inner.add(Utils.selfStyleImageButton(new TextureRegionDrawable(s.uiIcon), Styles.emptyi, () -> ui.content.show(s))).padTop(4f).padBottom(6f).size(42);
                    //inner.button(new TextureRegionDrawable(s.uiIcon), () -> ui.content.show(s)).padTop(4f).padBottom(6f).size(50);
                    inner.add(s.localizedName).padLeft(5);
                }
            }).left().growX().margin(6).pad(5).padBottom(-5).row();
        });

        if (findConsumer(c -> c instanceof ConsumeItems) instanceof ConsumeItems cons) {
            stats.remove(Stat.booster);
            stats.add(Stat.booster, HIStatValues.itemRangeBoosters("{0}" + StatUnit.timesSpeed.localized(), stats.timePeriod, boostStatus, boostRange * 8, cons.items, boostReplace, this::consumesItem));
        }
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);

        x *= tilesize;
        y *= tilesize;
        x += offset;
        y += offset;

        Drawf.dashSquare(player.team().color, x, y, range * tilesize);
    }

    @Override
    public void load() {
        super.load();
        if (drawer == null) return;
        drawer.load(this);
    }

    @Override
    public TextureRegion[] icons() {
        return drawer == null ? super.icons() : drawer.finalIcons(this);
    }

    public class UnitBoostBuild extends Building implements HeatConsumer {
        public float[] sideHeat = new float[4];
        public float heat = 0f;
        public float realRange = 0;
        public float phaseHeat = 0;
        public float consumeTimer = 0;

        protected boolean can, show, change1_2 = false;
        protected float rotation, ps, lp1 = 0, lp2 = 1;
        protected final Seq<Float[]> pos = new Seq<>();

        protected final Pool<ExtendedPosition> posPool = Pools.get(ExtendedPosition.class, ExtendedPosition::new);

        // for drawer
        public boolean canShow() {
            return show;
        }

        @Override
        public void updateTile() {
            heat = calculateHeat(sideHeat);

            if (status.length < 1) return;

            phaseHeat = Mathf.lerpDelta(phaseHeat, optionalEfficiency, 0.1f);
            realRange = (range + phaseHeat * boostRange) * efficiency;

            if (efficiency < 0.01f) return;
            can = false;
            Units.nearby(Tmp.r1.setCentered(x, y, realRange * tilesize), u -> {
                if (u.team == team) {
                    boolean phase = phaseHeat > 0.5f;
                    if (phase) {
                        for (StatusEffect s : boostStatus) {
                            if (s == StatusEffects.none) continue;
                            u.apply(s, 10);
                        }
                    }
                    if (!boostReplace || !phase) {
                        for (StatusEffect s : status) {
                            if (s == StatusEffects.none) continue;
                            u.apply(s, 10);
                        }
                    }
                    can = true;
                }
            });

            if (optionalEfficiency > 0 && can && (consumeTimer += Time.delta) > consumeTime) {
                consumeTimer -= consumeTime;
                consume();
            }
            show = can;
        }

        @Override
        public void draw() {
            super.draw();
            if (drawer != null) {
                drawer.draw(this);
                if (onlyDrawer) return;
            }

            if (!state.isPaused()) {
                if (show && efficiency > 0.01f) {
                    ps = Mathf.lerpDelta(ps, 1, 0.04f);
                    rotation += edelta();

                } else {
                    ps = Mathf.lerpDelta(ps, 0, 0.08f);
                }
            }

            if (ps < 0.01f) return;
            float dz = Draw.z();

            Draw.z(Layer.effect);

            Draw.color(team.color, team.color.cpy().mul(Items.phaseFabric.color), phaseHeat);

            Fill.square(x, y, size * ps, rotation);

            Draw.z(Layer.blockUnder);

            Lines.stroke(1.4f * ps);
            float rd = size * tilesize * ps;

            pos.clear();
            for (int i = 0; i < 3; i++) {
                float rt = 360 / 3f * i - rotation;

                if (phaseHeat < 0.99f) Lines.arc(x, y, rd, 1/3f * (1 - phaseHeat + 0.05f), rt);
                float ex = x + Angles.trnsx(rt, rd), ey = y + Angles.trnsy(rt, rd);
                Float[] p = {ex, ey};
                pos.add(p);
            }

            for (int i = 0; i < pos.size; i++) {
                float ox = pos.get(i)[0], oy = pos.get(i)[1];
                float ex = pos.get((i + 2) % pos.size)[0], ey = pos.get((i + 2) % pos.size)[1];

                ExtendedPosition og = posPool.obtain().set(ox, oy);
                float dst = og.dst(ex, ey);
                float angle = og.angleTo(ex, ey);
                Lines.lineAngle(ox, oy, angle, dst * phaseHeat * ps);
                posPool.free(og);
            }
            Draw.z(dz);
            Draw.reset();
        }

        @Override
        public void drawSelect() {
            super.drawSelect();

            if (!change1_2) lp1 = Mathf.lerpDelta(lp1, 1, 0.06f);
            if (change1_2) lp2 = Mathf.lerpDelta(lp2, 0, 0.06f);
            if (lp1 > 0.99f) {
                change1_2 = true;
                lp1 = 0;
            }
            if (lp2 < 0.01f) {
                change1_2 = false;
                lp2 = 1;
            }

            Lines.stroke(2.4f, team.color);
            pos.clear();
            for (Point2 q : Geometry.d8edge) {
                float lx = x + realRange / 2 * tilesize * q.x;
                float ly = y + realRange / 2 * tilesize * q.y;
                Float[] p = {lx, ly};
                pos.add(p);
            }

            for (int i = 0; i < pos.size; i++) {
                float ox = pos.get(i)[0], oy = pos.get(i)[1];
                float ex = pos.get((i + 1) % pos.size)[0], ey = pos.get((i + 1) % pos.size)[1];

                ExtendedPosition og = posPool.obtain().set(ox, oy);
                float dst = og.dst(ex, ey);
                float angle = og.angleTo(ex, ey);
                if (!change1_2) {
                    Lines.lineAngle(ox, oy, angle, dst * lp1);
                } else {
                    Lines.lineAngle(ex, ey, angle - 180, dst * lp2);
                }
                posPool.free(og);
            }
        }

        @Override
        public float[] sideHeat() {
            return sideHeat;
        }

        @Override
        public float heatRequirement() {
            return heatRequirement;
        }

        @Override
        public float efficiencyScale() {
            return Math.min(maxRangeBoost, heat/heatRequirement);
        }

        @Override
        public void updateEfficiencyMultiplier() {
            float scale = efficiencyScale();
            efficiency *= scale;
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.f(consumeTimer);
            write.f(phaseHeat);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            consumeTimer = read.f();
            phaseHeat = read.f();
        }
    }
}
