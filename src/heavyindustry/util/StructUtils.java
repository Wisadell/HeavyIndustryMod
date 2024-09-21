package heavyindustry.util;

import arc.func.*;

/** Struct utilities, providing some stateless iterative utilities such as reduce. */
public final class StructUtils {
    public static <T> void each(T[] array, Cons<? super T> cons){
        each(array, 0, array.length, cons);
    }

    public static <T> void each(T[] array, int offset, int length, Cons<? super T> cons){
        for(int i = offset, len = i + length; i < len; i++) cons.get(array[i]);
    }

    public static <T> int reducei(T[] array, int initial, Reducei<T> reduce){
        for(var item : array) initial = reduce.get(item, initial);
        return initial;
    }

    public static <T> int sumi(T[] array, Intf<T> extract){
        return reducei(array, 0, (item, accum) -> accum + extract.get(item));
    }

    public static <T> boolean arrayEq(T[] first, T[] second, Boolf2<T, T> eq){
        if(first.length != second.length) return false;
        for(int i = 0; i < first.length; i++){
            if(!eq.get(first[i], second[i])) return false;
        }
        return true;
    }

    public interface Reducei<T>{
        int get(T item, int accum);
    }

    public interface Reducef<T>{
        float get(T item, float accum);
    }
}
