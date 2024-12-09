package heavyindustry.util;

import arc.*;
import arc.func.*;
import arc.struct.*;
import rhino.*;

import static mindustry.Vars.*;

/**
 * Utility class for transition between Java and JS scripts, as well as providing a custom top level scope for the sake of
 * cross-mod compatibility. Use the custom scope for programmatically compiling Rhino functions. Note that {@link #modScope}
 * does not support the {@code require()} function.
 */
public final class JSBridge {
    private static final Seq<String> packages = Seq.with(
            "heavyindustry.ai",
            "heavyindustry.content",
            "heavyindustry.core",
            "heavyindustry.entities",
            "heavyindustry.entities.abilities",
            "heavyindustry.entities.bullet",
            "heavyindustry.entities.effect",
            "heavyindustry.entities.part",
            "heavyindustry.files",
            "heavyindustry.func",
            "heavyindustry.game",
            "heavyindustry.gen",
            "heavyindustry.graphics",
            "heavyindustry.graphics.g2d",
            "heavyindustry.graphics.g3d",
            "heavyindustry.graphics.g3d.model",
            "heavyindustry.graphics.g3d.model.obj",
            "heavyindustry.graphics.g3d.model.obj.mtl",
            "heavyindustry.graphics.g3d.model.obj.obj",
            "heavyindustry.graphics.g3d.render",
            "heavyindustry.graphics.trail",
            "heavyindustry.maps",
            "heavyindustry.maps.planets",
            "heavyindustry.math",
            "heavyindustry.math.gravity",
            "heavyindustry.mod",
            "heavyindustry.struct",
            "heavyindustry.type",
            "heavyindustry.type.unit",
            "heavyindustry.type.weapons",
            "heavyindustry.type.weather",
            "heavyindustry.ui",
            "heavyindustry.ui.dialogs",
            "heavyindustry.ui.displays",
            "heavyindustry.ui.elements",
            "heavyindustry.util",
            "heavyindustry.world.blocks.defense",
            "heavyindustry.world.blocks.defense.turrets",
            "heavyindustry.world.blocks.distribution",
            "heavyindustry.world.blocks.environment",
            "heavyindustry.world.blocks.heat",
            "heavyindustry.world.blocks.liquid",
            "heavyindustry.world.blocks.logic",
            "heavyindustry.world.blocks.payload",
            "heavyindustry.world.blocks.power",
            "heavyindustry.world.blocks.production",
            "heavyindustry.world.blocks.sandbox",
            "heavyindustry.world.blocks.storage",
            "heavyindustry.world.blocks.units",
            "heavyindustry.world.comp",
            "heavyindustry.world.consumers",
            "heavyindustry.world.draw",
            "heavyindustry.world.lightning",
            "heavyindustry.world.lightning.generator",
            "heavyindustry.world.meta",
            "heavyindustry.world.particle",
            "heavyindustry.world.particle.model"
    );

    public static Context context;
    public static ImporterTopLevel defScope;
    public static ImporterTopLevel modScope;

    /** JSBridge should not be instantiated. */
    private JSBridge() {}

    /** Initializes the JS bridge. Call this in the main thread only! */
    public static void init() {
        context = mods.getScripts().context;
        if (mods.getScripts().scope instanceof ImporterTopLevel scope)
            defScope = scope;

        modScope = new ImporterTopLevel(context);
        context.evaluateString(modScope, Core.files.internal("scripts/global.js").readString(), "global.js", 1);
        context.evaluateString(modScope, """
                function apply(map, object) {
                    for (let key in object) {
                        map.put(key, object[key]);
                    }
                }
                """, "apply.js", 1
        );
    }

    public static void importDefaults(ImporterTopLevel scope) {
        for (String pack : packages) {
            importPackage(scope, pack);
        }
    }

    public static void importPackage(ImporterTopLevel scope, String packageName) {
        NativeJavaPackage p = new NativeJavaPackage(packageName, mods.mainLoader());
        p.setParentScope(scope);

        scope.importPackage(p);
    }

    public static void importPackage(ImporterTopLevel scope, Package pack) {
        importPackage(scope, pack.getName());
    }

    public static void importClass(ImporterTopLevel scope, String canonical) {
        try {
            importClass(scope, Reflects.findClass(canonical));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void importClass(ImporterTopLevel scope, Class<?> type) {
        NativeJavaClass nat = new NativeJavaClass(scope, type);
        nat.setParentScope(scope);

        scope.importClass(nat);
    }

    public static Function compileFunc(Scriptable scope, String sourceName, String source) {
        return compileFunc(scope, sourceName, source, 1);
    }

    public static Function compileFunc(Scriptable scope, String sourceName, String source, int lineNum) {
        return context.compileFunction(scope, source, sourceName, lineNum);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func<Object[], T> requireType(Function func, Context context, Scriptable scope, Class<T> returnType) {
        Class<?> type = Reflects.box(returnType);
        return args -> {
            Object res = func.call(context, scope, scope, args);
            if (type == void.class || type == Void.class) return null;

            if (res instanceof Wrapper w) res = w.unwrap();
            if (!type.isAssignableFrom(res.getClass()))
                throw new IllegalStateException("Incompatible return type: Expected '" + returnType + "', but got '" + res.getClass() + "'!");
            return (T) type.cast(res);
        };
    }
}
