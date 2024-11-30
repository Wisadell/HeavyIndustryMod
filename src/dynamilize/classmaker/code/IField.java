package dynamilize.classmaker.code;

import dynamilize.classmaker.*;
import dynamilize.classmaker.code.annotation.*;

public interface IField<T> extends Element, AnnotatedElement {
    @Override
    default void accept(ElementVisitor visitor) {
        visitor.visitField(this);
    }

    @Override
    default ElementKind kind() {
        return ElementKind.FIELD;
    }

    String name();

    int modifiers();

    IClass<?> owner();

    IClass<T> type();

    Object initial();
}
