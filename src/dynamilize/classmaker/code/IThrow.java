package dynamilize.classmaker.code;

import dynamilize.classmaker.*;

public interface IThrow<T extends Throwable> extends Element {
    @Override
    default void accept(ElementVisitor visitor) {
        visitor.visitThrow(this);
    }

    @Override
    default ElementKind kind() {
        return ElementKind.THROW;
    }

    ILocal<T> thr();
}
