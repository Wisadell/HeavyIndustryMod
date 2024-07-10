package HeavyIndustry.content;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.effect.ParticleEffect;
import mindustry.type.Liquid;

import static HeavyIndustry.HeavyIndustryMod.name;

public class HILiquids {
    public static Liquid
            nanofluid,nitratedOil;
    public static void load(){
        nanofluid = new Liquid("nanofluid", Color.valueOf("7fd489")){{
            heatCapacity = 1.5f;
            viscosity = 0.8f;
            temperature = 0.3f;
            lightColor = Color.valueOf("7fd489").a(0.3f);
            particleSpacing = 10;
            particleEffect = new ParticleEffect(){{
                particles = 5;
                baseLength = sizeFrom = 1f;
                length = 13f;
                region = name("diamond");
                sizeInterp = Interp.pow5In;
                lifetime = 22f;
                sizeTo = 0f;
                colorFrom = Color.valueOf("96e6a0");
                colorTo = Color.valueOf("62ae7f");
            }};
            effect = StatusEffects.electrified;
            coolant = true;
        }};
        nitratedOil = new Liquid("nitrated-oil", Color.valueOf("3c3e45")){{
            temperature = 0.5f;
            viscosity = 0.8f;
            flammability = 0.8f;
            explosiveness = 3.2f;
            effect = StatusEffects.tarred;
            canStayOn.add(Liquids.water);
            coolant = false;
        }};
    }
}
