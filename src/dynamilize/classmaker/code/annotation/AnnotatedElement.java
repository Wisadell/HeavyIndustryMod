package dynamilize.classmaker.code.annotation;

import dynamilize.classmaker.code.*;

import java.lang.annotation.*;
import java.util.*;

public interface AnnotatedElement {
    List<IAnnotation<?>> getAnnotations();

    void initAnnotations();

    boolean hasAnnotation(IClass<? extends Annotation> annoType);

    <T extends Annotation> IAnnotation<T> getAnnotation(IClass<T> annoType);

    boolean isType(ElementType type);

    <A extends Annotation> A getAnnotation(Class<A> annoClass);

    void addAnnotation(IAnnotation<?> annotation);
}
