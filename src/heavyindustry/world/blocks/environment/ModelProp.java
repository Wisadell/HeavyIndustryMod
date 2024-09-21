//package heavyindustry.world.blocks.environment;
//
//import heavyindustry.gen.*;
//import heavyindustry.graphics.g3d.*;
//import arc.graphics.*;
//import arc.graphics.g2d.*;
//import arc.math.*;
//import mindustry.world.*;
//
//import static heavyindustry.core.HeavyIndustryMod.*;
//
//public class ModelProp extends Block implements DelegateMapColor {
//    public ModelProp(String name) {
//        super(name);
//        this.parent = parent;
//        this.color = color;
//        this.meshes = meshes;
//        alwaysReplace = breakable = false;
//        solid = instantBuild = allowRectanglePlacement = true;
//    }
//
//    @Override
//    public boolean isStatic(){
//        return true;
//    }
//
//    @Override
//    public Block substitute(){
//        return parent;
//    }
//
//    @Override
//    public void drawBase(Tile tile){
//        parent.drawBase(tile);
//        float z = Draw.z();
//
//        Draw.draw(ModelPropDrawer.accumLayer, () -> modelPropDrawer.draw(
//                meshes[Mathf.randomSeed(tile.pos(), 0, meshes.length - 1)], tile.worldx(), tile.worldy(),
//                Mathf.randomSeed(tile.pos() + 1, 0, 4) * 90f, color
//        ));
//        Draw.z(z);
//    }
//}
