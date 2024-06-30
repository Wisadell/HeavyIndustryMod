package HeavyIndustry.content;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.entities.effect.ParticleEffect;
import mindustry.type.Liquid;

import static HeavyIndustry.HeavyIndustryMod.name;

public class HILiquids {
    public static Liquid nanofluid = new Liquid("nanofluid", Color.valueOf("7fd489")){{
        heatCapacity = 1.5f;
        viscosity = 0.8f;
        temperature = 0.3f;
        lightColor = Color.valueOf("7fd489").a(0.3f);
        particleSpacing = 10;
        particleEffect = new ParticleEffect(){{
            particles = 5;
            baseLength = 1;
            length = 13;
            region = name("diamond");
            sizeInterp = Interp.pow5In;
            lifetime = 22;
            sizeFrom = 1;
            sizeTo = 0;
            colorFrom = Color.valueOf("96e6a0");
            colorTo = Color.valueOf("62ae7f");
        }};
    }};
    public static Liquid nitratedOil = new Liquid("nitrated-oil", Color.valueOf("333333")){{
        temperature = 0.5f;
        viscosity = 0.8f;
        flammability = 0.8f;
        explosiveness = 3.2f;
        coolant = false;
    }};
    public static Liquid methane = new Liquid("methane", Color.valueOf("fbd367")){{
        gas = true;
        temperature = 0.5f;
        viscosity = 0.5f;
        heatCapacity = 0.5f;
        flammability = 1f;
        explosiveness = 1.5f;
        boilPoint = -1f;
        coolant = false;
    }};
}
