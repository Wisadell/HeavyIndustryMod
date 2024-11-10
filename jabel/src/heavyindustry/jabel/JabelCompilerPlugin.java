package heavyindustry.jabel;

import com.sun.source.util.*;
import com.sun.tools.javac.code.*;
import com.sun.tools.javac.code.Source.*;

import java.lang.invoke.*;
import java.lang.invoke.MethodHandles.*;
import java.lang.reflect.*;

import static com.sun.tools.javac.code.Source.Feature.*;

/** Makes users able to use Java 9+ syntactic-sugars while still targeting Java 8. */
public class JabelCompilerPlugin implements Plugin {
    static{
        try{
            Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
            field.setAccessible(true);
            // Get the trusted private lookup.
            Lookup lookup = (Lookup)field.get(null);
            // Get the minimum level setter, to force certain features to qualify as a Java 8 feature.
            MethodHandle set = lookup.findSetter(Feature.class, "minLevel", Source.class);

            // Downgrade most Java 8-compatible features.
            for(Feature feature : new Feature[]{
                    EFFECTIVELY_FINAL_VARIABLES_IN_TRY_WITH_RESOURCES,
                    PRIVATE_SAFE_VARARGS,
                    DIAMOND_WITH_ANONYMOUS_CLASS_CREATION,
                    LOCAL_VARIABLE_TYPE_INFERENCE,
                    VAR_SYNTAX_IMPLICIT_LAMBDAS,
                    SWITCH_MULTIPLE_CASE_LABELS,
                    SWITCH_RULE,
                    SWITCH_EXPRESSION,
                    TEXT_BLOCKS,
                    PATTERN_MATCHING_IN_INSTANCEOF,
                    REIFIABLE_TYPES_INSTANCEOF
            }) set.invokeExact(feature, Source.JDK8);
        }catch(Throwable t){
            throw new RuntimeException(t);
        }
    }

    @Override
    public void init(JavacTask task, String... args){}

    @Override
    public String getName(){
        return "jabel";
    }

    // Make it auto start on Java 14+
    public boolean autoStart(){
        return true;
    }
}