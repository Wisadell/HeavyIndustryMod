package heavyindustry.gen;

import arc.math.geom.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;

public interface Energyc extends Unitc {
    boolean teleportValid();

    void teleport(float x, float y);

    float reload();

    float teleportMinRange();

    float teleportRange();

    Vec2 lastPos();

    float reloadValue();

    float lastHealth();

    Interval timer();

    Trail[] trails();

    void reload(float value);

    void teleportMinRange(float value);

    void teleportRange(float value);

    void lastPos(Vec2 value);

    void reloadValue(float value);

    void lastHealth(float value);

    void timer(Interval value);

    void trails(Trail[] value);
}
