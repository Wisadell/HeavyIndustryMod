package heavyindustry.ui;

import arc.util.*;
import mindustry.core.*;

public final class HIUI {
    /** Based on {@link UI#formatAmount(long)} but for floats. */
    public static String formatAmount(float number){
        if(number == Float.MAX_VALUE) return "infinite";
        if(number == Float.MIN_VALUE) return "-infinite";

        float mag = Math.abs(number);
        String sign = number < 0 ? "-" : "";
        if(mag >= 1_000_000_000f){
            return sign + Strings.fixed(mag / 1_000_000_000f, 2) + "[gray]" + UI.billions + "[]";
        }else if(mag >= 1_000_000f){
            return sign + Strings.fixed(mag / 1_000_000f, 2) + "[gray]" + UI.millions + "[]";
        }else if(mag >= 1000f){
            return sign + Strings.fixed(mag / 1000f, 2) + "[gray]" + UI.thousands + "[]";
        }else{
            return sign + Strings.fixed(mag, 2);
        }
    }
}
