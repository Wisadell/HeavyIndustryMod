package dynamilize.classmaker.code;

import dynamilize.classmaker.*;

public interface IReturn<T> extends Element {
    @Override
    default void accept(ElementVisitor visitor) {
        visitor.visitReturn(this);
    }

    @Override
    default ElementKind kind() {
        return ElementKind.RETURN;
    }

    ILocal<T> returnValue();
}
