package dynamilize.classmaker.code;

import dynamilize.classmaker.*;
import dynamilize.classmaker.code.annotation.*;

import java.lang.annotation.*;
import java.util.*;

public interface IClass<T> extends Element, AnnotatedElement {
    @Override
    default void accept(ElementVisitor visitor) {
        visitor.visitClass(this);
    }

    @Override
    default ElementKind kind() {
        return ElementKind.CLASS;
    }

    /**
     * Return the Java class marked by this type identifier.
     * @return The class marked with this type of tag.
     */
    Class<T> getTypeClass();

    /**
     * Is this type identifier an existing type identifier.
     * @return If the marked class has already been loaded by JVM.
     */
    boolean isExistedClass();

    boolean isPrimitive();

    IClass<T[]> asArray();

    <A extends Annotation> AnnotationType<A> asAnnotation(Map<String, Object> defaultAttributes);

    boolean isAnnotation();

    boolean isArray();

    IClass<?> componentType();

    String name();

    /**
     * Get the real name of this class in bytecode, for example: java.lang.Object -> Ljava/lang/Object;
     * <p>Specifically, for basic data types:
     * <pre>{@code
     * int     -> I
     * float   -> F
     * byte    -> B
     * short   -> S
     * long    -> J
     * double  -> D
     * char    -> C
     * boolean -> Z
     * void    -> V
     * }</pre>
     * @return The actual name of the class.
     */
    String realName();

    /**
     * Get the bytecode type identifier of this type, which is the real name without the first symbol L, for example, java.lang.Object -> java/lang/Object
     * @return The byte identifier name of the type.
     * @see ClassInfo#realName()
     */
    String internalName();

    int modifiers();

    IClass<? super T> superClass();

    List<IClass<?>> interfaces();

    List<Element> elements();

    <Type> IField<Type> getField(IClass<Type> type, String name);

    <R> IMethod<T, R> getMethod(IClass<R> returnType, String name, IClass<?>... args);

    IMethod<T, Void> getConstructor(IClass<?>... args);

    boolean isAssignableFrom(IClass<?> target);
}
