package heavyindustry.gen;

import arc.struct.*;
import mindustry.gen.*;
import mindustry.type.*;

public interface DrillFc extends Buildingc {
    float maxBoost();

    int dominantItems();

    Item dominantItem();

    Item convertItem();

    boolean coreSend();

    float boostMul();

    float boostFinalMul();

    float powerConsMul();

    float powerConsExtra();

    Seq<DrillModulec> modules();

    void maxBoost(float value);

    void dominantItems(int value);

    void dominantItem(Item value);

    void convertItem(Item value);

    void coreSend(boolean value);

    void boostMul(float value);

    void boostFinalMul(float value);

    void powerConsMul(float value);

    void powerConsExtra(float value);

    void modules(Seq<DrillModulec> value);
}
