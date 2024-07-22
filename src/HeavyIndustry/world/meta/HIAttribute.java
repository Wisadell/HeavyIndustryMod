package HeavyIndustry.world.meta;

import mindustry.world.meta.Attribute;

public class HIAttribute {
    public static Attribute tide,arkycite;
    public static void load(){
        tide = Attribute.add("tide");
        arkycite = Attribute.add("arkycite");
    }
}
