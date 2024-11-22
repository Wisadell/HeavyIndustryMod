package heavyindustry.math;

import arc.func.*;
import arc.math.*;
import arc.math.geom.*;

public final class Mathm {
    private static final int aSinBits = 14; //16KB. Adjust for accuracy.
    private static final int aSinMask = ~(-1 << aSinBits);
    private static final int aSinCount = aSinMask + 1;
    private static final float[] aSinTable = new float[aSinCount];
    private static final float radFull = Mathf.PI * 2;
    private static final float sinToIndex = aSinCount / 2f;

    static {
        for (int i = 0; i < aSinCount; i++) aSinTable[i] = (float)(Math.asin((i + 0.5f) / aSinCount * 2 - 1) + radFull);

        aSinTable[0] = radFull - Mathf.halfPi;
        aSinTable[aSinTable.length - 1] = radFull + Mathf.halfPi;
        aSinTable[index(1.5f)] = Mathf.halfPi + radFull;
        aSinTable[index(0.5f)] = Mathf.pi + Mathf.halfPi + radFull;
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
        return aSinTable[index(sin)] * Mathf.radiansToDegrees;
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

    /** An asymptotic function that approximates from a specified starting point to a target point. */
    public static double lerp(double origin, double dest, double rate, double x){
        double a = 1 - rate;
        double b = rate*dest;

        double powered = Math.pow(a, x - 1);

        return origin*powered + (b*powered - b/a)/(1 - 1/a);
    }

    /** The function corresponding to an S-shaped curve. */
    public static double sCurve(double left, double right, double dx, double dy, double rate, double x){
        double diff = right - left;
        double xValue = dx*rate;

        return diff/Math.pow(2, xValue - rate*x) + dy + left;
    }

    /**
     * Increase and decrease the curve,reaching the highest point at the most suitable value,and decreasing on both sides.
     * The parameters can control the left and right attenuation rates separately.
     */
    public static double lerpIncrease(double lerpLeft, double lerpRight, double max, double optimal, double x){
        if(x < 0) return 0;
        return x >= 0 && x < optimal? -max*Math.pow(1-x/optimal, lerpLeft) + max: -max*Math.pow(1-optimal/x, lerpRight) + max;
    }
}
