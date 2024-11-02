package heavyindustry.math;

import static arc.math.Mathf.*;

public final class Mathm {
    private static final int aSinBits = 14; //16KB. Adjust for accuracy.
    private static final int aSinMask = ~(-1 << aSinBits);
    private static final int aSinCount = aSinMask + 1;
    private static final float[] aSinTable = new float[aSinCount];
    private static final float radFull = PI * 2;
    private static final float sinToIndex = aSinCount / 2f;

    static {
        for (int i = 0; i < aSinCount; i++) aSinTable[i] = (float)(Math.asin((i + 0.5f) / aSinCount * 2 - 1) + radFull);

        aSinTable[0] = radFull - halfPi;
        aSinTable[aSinTable.length - 1] = radFull + halfPi;
        aSinTable[index(1.5f)] = halfPi + radFull;
        aSinTable[index(0.5f)] = pi + halfPi + radFull;
    }

    public static int index(float sin){
        return (int)((sin + 1) * sinToIndex) & aSinMask;
    }

    public static float cosToSin(float x) {
        return x - (x * x * x) / 6 + (x * x * x * x * x) / 120; //Taylor
    }

    public static float acosRad(float cos){
        return aSinTable[index((float)Math.sqrt(1 - cos * cos))];
    }

    public static float asinDeg(float sin){
        return aSinTable[index(sin)] * radiansToDegrees;
    }
}
