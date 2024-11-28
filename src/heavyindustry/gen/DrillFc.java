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

    void maxBoost(float maxBoost);

    void dominantItems(int dominantItems);

    void dominantItem(Item dominantItem);

    void convertItem(Item convertItem);

    void coreSend(boolean coreSend);

    void boostMul(float boostMul);

    void boostFinalMul(float boostFinalMul);

    void powerConsMul(float powerConsMul);

    void powerConsExtra(float powerConsExtra);

    void modules(Seq<DrillModulec> modules);
}
