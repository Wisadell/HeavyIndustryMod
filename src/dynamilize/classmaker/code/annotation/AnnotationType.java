package dynamilize.classmaker.code.annotation;

import dynamilize.classmaker.*;
import dynamilize.classmaker.code.*;

import java.lang.annotation.*;
import java.util.*;

public interface AnnotationType<T extends Annotation> {
    static <A extends Annotation> AnnotationType<A> asAnnotationType(Class<A> annoType) {
        IClass<A> clazz = ClassInfo.asType(annoType);
        return clazz.asAnnotation(null);
    }

    IClass<T> typeClass();

    Map<String, Object> defaultValues();

    IAnnotation<T> annotateTo(AnnotatedElement element, Map<String, Object> attributes);
}
