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

    private static final Vec2 bezOut = new Vec2(), p1 = new Vec2(), p2 = new Vec2(), p3 = new Vec2(), p4 = new Vec2(), tmp = new Vec2();

    static {
        for (int i = 0; i < aSinCount; i++) aSinTable[i] = (float) (Math.asin((i + 0.5f) / aSinCount * 2 - 1) + radFull);

        aSinTable[0] = radFull - Mathf.halfPi;
        aSinTable[aSinTable.length - 1] = radFull + Mathf.halfPi;
        aSinTable[index(1.5f)] = Mathf.halfPi + radFull;
        aSinTable[index(0.5f)] = Mathf.pi + Mathf.halfPi + radFull;
    }

    /** Mathm should not be instantiated. */
    private Mathm() {}

    /** @return whether x,y is inside the square with radius d centered at cx, cy. */
    public static boolean isInSquare(float cx, float cy, float d, float x, float y) {
        return x > cx - d && x < cx + d && y > cy - d && y < cy + d;
    }

    public static float cornerDst(float r) {
        return cornerDst(r, r);
    }

    public static float cornerDst(float w, float h) {
        return (float) Math.sqrt(w * h * 2f);
    }

    public static float cbrt(float x) {
        return (float) Math.cbrt(x);
    }

    public static Vec2 quad(float a, float b, float c) {
        Vec2 sol = null;
        if (Math.abs(a) < 1e-6) {
            if (Math.abs(b) < 1e-6) {
                sol = Math.abs(c) < 1e-6 ? tmp.set(0, 0) : null;
            } else {
                tmp.set(-c / b, -c / b);
            }
        } else {
            float disc = b * b - 4 * a * c;
            if (disc >= 0) {
                disc = Mathf.sqrt(disc);
                a = 2 * a;
                sol = tmp.set((-b - disc) / a, (-b + disc) / a);
            }
        }
        return sol;
    }

    /** @return Smallest positive nonzero solution of a quadratic. */
    public static float quadPos(float a, float b, float c) {
        Vec2 ts = quad(a, b, c);
        if (ts != null) {
            float t0 = ts.x, t1 = ts.y;
            float t = Math.min(t0, t1);
            if (t <= 0) t = Math.max(t0, t1);
            if (t > 0) {
                return t;
            }
        }
        return 0f;
    }

    public static float solve(float a, float b, float c) {
        if (a == 0) {
            return -c / b;
        } else {
            Vec2 t = quad(a, b, c);
            return Math.max(t.x, t.y);
        }
    }

    /** <a href="https://math.stackexchange.com/questions/785/is-there-a-general-formula-for-solving-quartic-degree-4-equations">...</a> */
    public static float[] quartic(float a, float b, float c, float d, float e) {
        float p1 = 2 * c * c * c - 9 * b * c * d + 27 * a * d * d + 27 * b * b * e - 72 * a * c * e;
        float p2 = c * c - 3 * b * d + 12 * a * e;
        float p3 = p1 + Mathf.sqrt(-4 * p2 * p2 * p2 + p1 * p1);
        float p4 = cbrt(p3 / 2);
        float p5 = p2 / (3 * a * p4) + (p4 / (3 * a));

        float p6 = Mathf.sqrt((b * b) / (4 * a * a) - (2 * c) / (3 * a) + p5);
        float p7 = (b * b) / (2 * a * a) - (4 * c) / (3 * a) - p5;
        float p8 = (-(b * b * b) / (a * a * a) + (4 * b * c) / (a * a) - (8 * d) / a) / (4 * p6);

        return new float[]{
                -(b / (4 * a)) - (p6 / 2) - (Mathf.sqrt(p7 - p8) / 2),
                -(b / (4 * a)) - (p6 / 2) + (Mathf.sqrt(p7 - p8) / 2),
                -(b / (4 * a)) - (p6 / 2) - (Mathf.sqrt(p7 + p8) / 2),
                -(b / (4 * a)) - (p6 / 2) + (Mathf.sqrt(p7 + p8) / 2)
        };
    }

    public static Vec2 randomCirclePoint(Vec2 v, float radius) {
        v.setToRandomDirection().setLength(radius * Mathf.sqrt(Mathf.random()));

        return v;
    }

    public static float circleStarPoint(float theta) {
        theta = Mathf.mod(theta, 90f);
        theta *= Mathf.degRad;
        float b = -2 * Mathf.sqrt2 * Mathf.cos(theta - Mathf.pi / 4f);
        return (-b - Mathf.sqrt(b * b - 4)) / 2;
    }

    /** Pulled out of {@link Angles#moveToward(float, float, float)} */
    public static int angleMoveDirection(float from, float to) {
        from = Mathf.mod(from, 360f);
        to = Mathf.mod(to, 360f);

        if (from > to == Angles.backwardDistance(from, to) > Angles.forwardDistance(from, to)) {
            return -1;
        } else {
            return 1;
        }
    }

    /** Lerp from one angle to another. */
    public static float lerpAngle(float from, float to, float progress) {
        return Angles.moveToward(from, to, progress * Angles.angleDist(from, to));
    }

    public static Vec2 bezier(float t, float x1, float y1, float x2, float y2, Vec2 h1, Vec2 h2) {
        p1.set(x1, y1);
        p4.set(x2, y2);
        p2.set(p1).add(h1);
        p3.set(p4).add(h2);
        Bezier.cubic(bezOut, t, p1, p2, p3, p4, tmp);
        return bezOut;
    }

    public static Vec2 bezierDeriv(float t, float x1, float y1, float x2, float y2, Vec2 h1, Vec2 h2) {
        p1.set(x1, y1);
        p4.set(x2, y2);
        p2.set(p1).add(h1);
        p3.set(p4).add(h2);
        Bezier.cubicDerivative(bezOut, t, p1, p2, p3, p4, tmp);
        return bezOut;
    }

    public static int index(float sin) {
        return (int)((sin + 1) * sinToIndex) & aSinMask;
    }

    public static float cosToSin(float x) {
        return x - (x * x * x) / 6 + (x * x * x * x * x) / 120; //Taylor
    }

    public static float acosRad(float cos) {
        return aSinTable[index((float)Math.sqrt(1 - cos * cos))];
    }

    public static float asinDeg(float sin) {
        return aSinTable[index(sin)] * Mathf.radiansToDegrees;
    }

    /**
     * Calculate vector coordinates using Fourier series in polar coordinate form.
     * @param time Parameters (or interpolation) passed in for Fourier series.
     * @param params Parameterf group, determine a sin function for every three data points, parameter format:{@code {Angular velocity, initial phase, extremum,...}}
     * @return The vector calculated by the specified Fourier series under given interpolation is in the form of an orthogonal coordinate system.
     */
    public static Vec2 fourierSeries(float time, float... params) {
        tmp.setZero();
        for (int i = 0; i < params.length; i+=3) {
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
     * @param params Parameterf group, determine a sin function for every three data points, parameter format:{@code {Angular velocity, initial phase, extremum, ...}}
     */
    public static void fourierSeries(Floatc2 transRecall, float time, float... params) {
        Vec2 v = fourierSeries(time, params);
        transRecall.get(v.x, v.y);
    }

    public static float gradientRotate(float rad, float fine) {
        return gradientRotate(rad, fine, 0.25f, 4);
    }

    public static float gradientRotate(float rad, float fine, int sides) {
        return gradientRotate(rad, fine, 1f/sides, 4);
    }

    public static float gradientRotate(float rad, float fine, float off, int sides) {
        return rad - off* Mathf.sin(rad*sides + fine) + fine/sides;
    }

    public static float gradientRotateDeg(float deg, float fine) {
        return gradientRotate(deg*Mathf.degRad, fine * Mathf.degRad, 0.25f, 4)*Mathf.radDeg;
    }

    public static float gradientRotateDeg(float deg, float fine, int sides) {
        return gradientRotate(deg * Mathf.degRad, fine * Mathf.degRad, 1f / sides, 4) * Mathf.radDeg;
    }

    public static float gradientRotateDeg(float deg, float fine, float off, int sides) {
        return gradientRotate(deg * Mathf.degRad, fine * Mathf.degRad, off, sides) * Mathf.radDeg;
    }

    public static float innerAngle(float a, float b) {
        a %= 360;
        b %= 360;
        return b - a > 180? b - a - 360: b - a < -180? b - a + 360: b - a;
    }

    public static boolean confine(double d, double min, double max) {
        return d < min || d > max;
    }

    /** An asymptotic function that approximates from a specified starting point to a target point. */
    public static double lerp(double origin, double dest, double rate, double x) {
        double a = 1 - rate;
        double b = rate * dest;

        double powered = Math.pow(a, x - 1);

        return origin*powered + (b*powered - b / a) / (1 - 1 / a);
    }

    /** The function corresponding to an S-shaped curve. */
    public static double sCurve(double left, double right, double dx, double dy, double rate, double x) {
        double diff = right - left;
        double xValue = dx * rate;

        return diff / Math.pow(2, xValue - rate * x) + dy + left;
    }

    /**
     * Increase and decrease the curve,reaching the highest point at the most suitable value,and decreasing on both sides.
     * The parameters can control the left and right attenuation rates separately.
     */
    public static double lerpIncrease(double lerpLeft, double lerpRight, double max, double optimal, double x) {
        if (x < 0) return 0;
        return x >= 0 && x < optimal ? -max * Math.pow(1 - x / optimal, lerpLeft) + max: -max * Math.pow(1 - optimal / x, lerpRight) + max;
    }
}
