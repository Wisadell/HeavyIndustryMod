package dynamilize.classmaker.code;

import dynamilize.classmaker.*;

import java.util.*;

public interface IInvoke<R> extends Element {
    @Override
    default void accept(ElementVisitor visitor) {
        visitor.visitInvoke(this);
    }

    @Override
    default ElementKind kind() {
        return ElementKind.INVOKE;
    }

    ILocal<?> target();

    IMethod<?, R> method();

    List<ILocal<?>> args();

    ILocal<? super R> returnTo();

    boolean callSuper();
}
