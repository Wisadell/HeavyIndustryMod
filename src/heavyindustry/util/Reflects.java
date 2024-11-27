package heavyindustry.util;

import arc.func.*;
import arc.struct.*;
import mindustry.world.*;
import mindustry.world.consumers.*;

import java.lang.reflect.*;

@SuppressWarnings("unchecked")
public final class Reflects {
    /** Reflects should not be instantiated. */
    private Reflects() {}

    public static void removeAllConsumes(Block block) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        getBlockConsumeBuilder(block).clear();
        block.consPower = null;
    }

    public static void removeConsumeItems(Block block) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        getBlockConsumeBuilder(block).removeAll(b -> b instanceof ConsumeItems);
    }

    public static void removeConsumeLiquids(Block block) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        getBlockConsumeBuilder(block).removeAll(b -> b instanceof ConsumeLiquidBase);
    }

    public static Seq<Consume> getBlockConsumeBuilder(Block block) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (Seq<Consume>) getfBlock(block, "consumeBuilder").get(block);
    }

    public static <T extends Block> Field getfBlock(T block, String typeField) throws ClassNotFoundException, NoSuchFieldException {
        return getf(block, "mindustry.world.Block", typeField);
    }

    public static <T> Field getf(T object, String typeName, String typeField) throws ClassNotFoundException, NoSuchFieldException {
        Field field = object.getClass().getClassLoader().loadClass(typeName).getDeclaredField(typeField);
        field.setAccessible(true);

        return field;
    }

    public static <T> T[] newArray(Class<T> type, int length){
        return (T[])Array.newInstance(type, length);
    }

    public static <T> T[] newArray(T[] oldType, int length){
        return (T[])Array.newInstance(oldType.getClass().getComponentType(), length);
    }

    public static boolean isWrapper(Class<?> type){
        return type == Byte.class || type == Short.class || type == Integer.class || type == Long.class || type == Character.class || type == Boolean.class || type == Float.class || type == Double.class;
    }

    public static <T> Prov<T> cons(Class<T> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> c = type.getDeclaredConstructor();
        c.setAccessible(true);
        return (Prov<T>)c.newInstance();
    }

    public static <T> T get(Field field) throws IllegalAccessException {
        return get(null, field);
    }

    public static <T> T get(Object object, Field field) throws IllegalAccessException {
        return (T)field.get(object);
    }

    public static <T> T get(Class<?> type, Object object, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = type.getDeclaredField(name);
        field.setAccessible(true);
        return (T)field.get(object);
    }

    public static <T> T get(Object object, String name) throws NoSuchFieldException, IllegalAccessException {
        return get(object.getClass(), object, name);
    }

    public static <T> T get(Class<?> type, String name) throws NoSuchFieldException, IllegalAccessException {
        return get(type, null, name);
    }

    public static void set(Class<?> type, Object object, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = type.getDeclaredField(name);
        field.setAccessible(true);
        field.set(object, value);
    }

    public static void set(Object object, Field field, Object value) throws IllegalAccessException {
        field.set(object, value);
    }

    public static void set(Object object, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        set(object.getClass(), object, name, value);
    }

    public static void set(Class<?> type, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        set(type, null, name, value);
    }

    public static <T> T invoke(Class<?> type, Object object, String name, Object[] args, Class<?>... parameterTypes) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = type.getDeclaredMethod(name, parameterTypes);
        method.setAccessible(true);
        return (T)method.invoke(object, args);
    }

    public static <T> T invoke(Class<?> type, String name, Object[] args, Class<?>... parameterTypes) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return invoke(type, null, name, args, parameterTypes);
    }

    public static <T> T invoke(Class<?> type, String name) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return invoke(type, name, null);
    }

    public static <T> T invoke(Object object, String name, Object[] args, Class<?>... parameterTypes) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return invoke(object.getClass(), object, name, args, parameterTypes);
    }

    public static <T> T invoke(Object object, String name) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return invoke(object, name, null);
    }

    public static <T> T make(String type) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<T> c = (Class<T>)Class.forName(type);
        return c.getDeclaredConstructor().newInstance();
    }
}
