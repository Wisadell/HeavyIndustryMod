package dynamilize.classmaker.code.annotation;

import java.lang.annotation.*;
import java.util.*;

public interface IAnnotation<A extends Annotation> {
    AnnotationType<A> annotationType();

    Map<String, Object> pairs();

    /** Only available on existing type identifiers. */
    A asAnnotation();

    <T> T getAttr(String attr);
}
