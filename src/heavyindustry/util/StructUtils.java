package heavyindustry.util;

import arc.func.*;

/** Struct utilities, providing some stateless iterative utilities such as reduce. */
public class StructUtils {
    public static <T> void each(T[] array, Cons<? super T> cons){
        each(array, 0, array.length, cons);
    }

    public static <T> void each(T[] array, int offset, int length, Cons<? super T> cons){
        for(int i = offset, len = i + length; i < len; i++) cons.get(array[i]);
    }
}
