package dynamilize.classmaker.code;

import dynamilize.classmaker.*;

import java.util.*;

public interface INewArray<T> extends Element {
    @Override
    default void accept(ElementVisitor visitor) {
        visitor.visitNewArray(this);
    }

    @Override
    default ElementKind kind() {
        return ElementKind.NEWARRAY;
    }

    IClass<T> arrayEleType();

    List<ILocal<Integer>> arrayLength();

    ILocal<?> resultTo();
}
