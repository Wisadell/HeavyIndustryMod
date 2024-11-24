package heavyindustry.gen;

import heavyindustry.graphics.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;

import static mindustry.Vars.*;
import static heavyindustry.graphics.Drawn.*;

public interface Linkablec extends Buildingc, Ranged{
    Seq<Building> tmpSeq = new Seq<>(1);

    @Override
    default boolean onConfigureBuildTapped(Building other) {
        if (this == other || linkPos() == other.pos()) {
            configure(Tmp.p1.set(-1, -1));
            return false;
        }
        if (other.within(this, range()) && other.team == team()) {
            configure(Point2.unpack(other.pos()));
            return false;
        }
        return true;
    }

    default void drawLink(@Nullable Seq<Building> builds) {
        Draw.reset();
        if (builds == null) {
            if (linkValid(link())) {
                Draw.color(getLinkColor());
                Drawf.circles(getX(), getY(), block().size / 2f * tilesize + Mathf.absin(Time.time * sinScl, 6f, 1f), getLinkColor());
                Drawn.link(this, link(), getLinkColor());
            }
        } else if(builds.size > 0){
            Draw.color(getLinkColor());
            Drawf.circles(getX(), getY(), block().size / 2f * tilesize + Mathf.absin(Time.time * sinScl, 6f, 1f), getLinkColor());

            for (Building b : builds) {
                if (!linkValid(b)) continue;
                Drawn.link(this, b, getLinkColor());
            }
        }

        Draw.reset();
    }

    default void drawLink() {
        drawLink(null);
    }

    default Building link() {
        return world.build(linkPos());
    }

    default boolean linkValid() {
        return linkValid(link());
    }

    default boolean linkValid(Building b) {
        return b != null;
    }

    default void linkPos(Point2 point2) {
        linkPos(point2.pack());
    }

    int linkPos();

    void linkPos(int value);
    Color getLinkColor();
}
