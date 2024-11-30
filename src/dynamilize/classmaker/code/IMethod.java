package dynamilize.classmaker.code;

import dynamilize.classmaker.*;
import dynamilize.classmaker.code.annotation.*;

import java.util.*;

public interface IMethod<S, R> extends Element, AnnotatedElement {
    @Override
    default void accept(ElementVisitor visitor) {
        visitor.visitMethod(this);
    }

    @Override
    default ElementKind kind() {
        return ElementKind.METHOD;
    }

    String name();

    int modifiers();

    String typeDescription();

    List<Parameter<?>> parameters();

    List<IClass<? extends Throwable>> throwTypes();

    IClass<S> owner();

    IClass<R> returnType();

    ICodeBlock<R> block();
}
