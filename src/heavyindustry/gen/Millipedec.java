package heavyindustry.gen;

import arc.func.*;
import mindustry.gen.*;

public interface Millipedec extends Legsc {
    <T extends Unit & Millipedec> void distributeActionBack(Cons<T> cons);

    <T extends Unit & Millipedec> void distributeActionForward(Cons<T> cons);

    boolean isHead();

    boolean isSegment();

    boolean isTail();

    boolean removing();

    boolean saveAdd();

    byte weaponIdx();

    float layer();

    float regenTime();

    float scanTime();

    float splitHealthDiv();

    float waitTime();

    int childId();

    int countAll();

    int countBackward();

    int countForward();

    int headId();

    Unit addTail();

    Unit child();

    Unit head();

    Unit parent();

    Unit tail();

    void child(Unit value);

    void childId(int value);

    void connect(Millipedec other);

    void head(Unit value);

    void headId(int value);

    void layer(float value);

    void parent(Unit value);

    void regenTime(float value);

    void removing(boolean value);

    void saveAdd(boolean value);

    void scanTime(float value);

    void splitHealthDiv(float value);

    void tail(Unit value);

    void waitTime(float value);

    void weaponIdx(byte value);
}
