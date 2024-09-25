package heavyindustry.world.blocks.environment;

import arc.*;
import arc.graphics.g2d.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import static mindustry.Vars.*;

/** @author LaoHuaJi */
public class SideFloor extends Floor {
    public SideFloor(String name) {
        super(name);
    }

    public SideFloor(String name, int variants){
        super(name, variants);
    }

    @Override
    public void load() {
        super.load();
    }

    @Override
    public TextureRegion[] editorVariantRegions(){
        if(editorVariantRegions == null){
            variantRegions();
            editorVariantRegions = new TextureRegion[1];
            TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)variantRegions[0];
            editorVariantRegions[0] = Core.atlas.find("editor-" + region.name);
        }
        return editorVariantRegions;
    }

    @Override
    protected boolean doEdge(Tile tile, Tile otherTile, Floor other){
        return false;
    }

    @Override
    public void drawBase(Tile tile) {
        Draw.rect(variantRegions[getTileIndex(tile)], tile.worldx(), tile.worldy());
        Draw.alpha(1f);
        drawEdges(tile);
        drawOverlay(tile);
    }

    private byte getTileIndex(Tile tile){
        byte index = 0;
        if (world.floor(tile.x, tile.y + 1) == this) index += 1;
        if (world.floor(tile.x + 1, tile.y) == this) index += 2;
        if (world.floor(tile.x, tile.y - 1) == this) index += 4;
        if (world.floor(tile.x - 1, tile.y) == this) index += 8;
        return index;
    }
}
