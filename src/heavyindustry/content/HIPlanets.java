package heavyindustry.content;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import heavyindustry.core.*;
import heavyindustry.graphics.g3d.*;
import heavyindustry.maps.ColorPass.*;
import heavyindustry.maps.*;
import heavyindustry.maps.HeightPass.*;
import heavyindustry.maps.planets.*;
import heavyindustry.type.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.graphics.g3d.*;
import mindustry.type.*;
import mindustry.world.meta.*;

/** Defines the {@linkplain Planet planets} and other celestial objects this mod offers. */
public final class HIPlanets {
    public static Planet kepler, blackHole;

    /** HIPlanets should not be instantiated. */
    private HIPlanets() {}

    /**
     * Instantiates all contents. Called in the main thread in {@link heavyindustry.core.HeavyIndustryMod#loadContent()}.
     * <p>Remember not to execute it a second time, I did not take any precautionary measures.
     */
    public static void load() {
        blackHole = new BlackHole("black-hole", 12f) {{
            orbitSpacing = 24f;
        }};
        kepler = new BetterPlanet("kepler", blackHole, 1f, 3) {{
            icon = "kepler-icon";
            orbitRadius = 40f;
            atmosphereRadIn = 0f;
            atmosphereRadOut = 0.3f;
            atmosphereColor = Blocks.water.mapColor;

            Vec3 ringPos = new Vec3(0, 1, 0).rotate(Vec3.X, 25);

            generator = new KeplerPlanetGenerator() {{
                baseHeight = 0;
                baseColor = Blocks.stone.mapColor;

                Mathf.rand.setSeed(2);
                heights.add(new NoiseHeight() {{
                    offset.set(1000, 0, 0);
                    octaves = 7;
                    persistence = 0.5;
                    magnitude = 1;
                    heightOffset = -0.5f;
                }});
                Seq<HeightPass> mountains = new Seq<>();
                for (int i = 0; i < 30; i++) {
                    mountains.add(new DotHeight() {{
                        dir.setToRandomDirection().y *= 10f;
                        dir.rotate(Vec3.X, 22f);
                        min = 0.99f;
                        magnitude = Math.abs(Tmp.v31.set(dir).nor().rotate(Vec3.X, -22f).y) * Mathf.random(0.5f);
                        interp = Interp.exp10In;
                    }});
                }
                heights.add(new MultiHeight(mountains, MultiHeight.MixType.max, MultiHeight.Operation.add), new ClampHeight(0f, 0.8f));
                colors.addAll(
                        new NoiseColorPass() {{
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 3;
                            magnitude = 1.2f;
                            min = 0.3f;
                            max = 0.6f;
                            out = HIBlocks.corruptedMoss.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 5;
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 5;
                            magnitude = 1.2f;
                            min = 0.1f;
                            max = 0.4f;
                            out = HIBlocks.overgrownGrass.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }},
                        new NoiseColorPass() {{
                            seed = 8;
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 7;
                            magnitude = 1.2f;
                            min = 0.1f;
                            max = 0.4f;
                            out = HIBlocks.mycelium.mapColor;
                            offset.set(1500f, 300f, -500f);
                        }}
                );
                for (int i = 0; i < 5; i++) {
                    colors.add(new SphereColorPass(new Vec3().setToRandomDirection(), 0.06f, Blocks.darksand.mapColor));
                }
                colors.add(
                        new FlatColorPass() {{
                            min = max = 0f;
                            out = Blocks.water.mapColor;
                        }},
                        new FlatColorPass() {{
                            min = 0.3f;
                            max = 0.5f;
                            out = Blocks.snow.mapColor;
                        }},
                        new FlatColorPass() {{
                            max = 1f;
                            min = 0.5f;
                            out = Blocks.iceSnow.mapColor;
                        }}
                );
            }};
            sectorSeed = 3;
            defaultEnv = Env.terrestrial | Env.groundOil | Env.groundWater | Env.oxygen;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = false;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            prebuildBase = false;
            ruleSetter = r -> {
                r.waveTeam = Team.crux;
                r.placeRangeCheck = false;
                r.showSpawns = false;
            };
            alwaysUnlocked = true;
            meshLoader = () -> new MultiMesh(
                    new AtmosphereHexMesh(7),
                    new HexMesh(this, 7),

                    new CircleMesh(circle("ring4.png"), this, 80, 2.55f, 2.6f, ringPos),
                    new CircleMesh(circle("ring3.png"), this, 80, 2.2f, 2.5f, ringPos),
                    new CircleMesh(circle("ring2.png"), this, 80, 1.9f, 2.1f, ringPos),
                    new CircleMesh(circle("ring1.png"), this, 80, 1.8f, 1.85f, ringPos)
            );
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 6, -0.5f, 0.14f, 6, Blocks.water.mapColor.cpy().a(0.2f), 2, 0.42f, 1f, 0.6f),
                    new HexSkyMesh(this, 1, 0.6f, 0.15f, 6, Blocks.water.mapColor.cpy().a(0.2f), 2, 0.42f, 1.2f, 0.5f)
            );
        }};
    }

    static Texture circle(String name) {
        return new Texture(HeavyIndustryMod.internalTree.child("sprites/planets/kepler/rings/" + name));
    }
}
