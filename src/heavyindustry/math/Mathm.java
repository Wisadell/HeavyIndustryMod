package heavyindustry.math;

import arc.func.*;
import arc.math.*;
import arc.math.geom.*;

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

    private static final Vec2 tmp = new Vec2();

    /**
     * Calculate vector coordinates using Fourier series in polar coordinate form.
     * @param time Parameters (or interpolation) passed in for Fourier series.
     * @param params Parameter group, determine a sin function for every three data points, parameter format:{@code {Angular velocity, initial phase, extremum,...}}
     * @return The vector calculated by the specified Fourier series under given interpolation is in the form of an orthogonal coordinate system.
     */
    public static Vec2 fourierSeries(float time, float... params){
        tmp.setZero();
        for(int i = 0; i < params.length; i+=3){
            float w = params[i];
            float f = params[i + 1];
            float l = params[i + 2];

            tmp.add(Angles.trnsx(f + time*w, l), Angles.trnsy(f + time*w, l));
        }

        return tmp;
    }

    /**
     * Use Fourier series in polar coordinate form to calculate vector coordinates, and call back the calculation result through a function.
     * @param transRecall The callback function for calculating the result.
     * @param time Parameters (or interpolation) passed in for Fourier series.
     * @param params Parameter group, determine a sin function for every three data points, parameter format:{@code {Angular velocity, initial phase, extremum, ...}}
     */
    public static void fourierSeries(Floatc2 transRecall, float time, float... params){
        Vec2 v = fourierSeries(time, params);
        transRecall.get(v.x, v.y);
    }

    public static float gradientRotate(float rad, float fine){
        return gradientRotate(rad, fine, 0.25f, 4);
    }

    public static float gradientRotate(float rad, float fine, int sides){
        return gradientRotate(rad, fine, 1f/sides, 4);
    }

    public static float gradientRotate(float rad, float fine, float off, int sides){
        return rad - off* Mathf.sin(rad*sides + fine) + fine/sides;
    }

    public static float gradientRotateDeg(float deg, float fine){
        return gradientRotate(deg*Mathf.degRad, fine*Mathf.degRad, 0.25f, 4)*Mathf.radDeg;
    }

    public static float gradientRotateDeg(float deg, float fine, int sides){
        return gradientRotate(deg*Mathf.degRad, fine*Mathf.degRad, 1f/sides, 4)*Mathf.radDeg;
    }

    public static float gradientRotateDeg(float deg, float fine, float off, int sides){
        return gradientRotate(deg*Mathf.degRad, fine*Mathf.degRad, off, sides)*Mathf.radDeg;
    }

    public static float innerAngle(float a, float b) {
        a %= 360;
        b %= 360;
        return b - a > 180? b - a - 360: b - a < -180? b - a + 360: b - a;
    }

    public static boolean confine(double d, double min, double max){
        return d < min || d > max;
    }
}
