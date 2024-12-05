package heavyindustry.graphics.g3d.model;

import arc.math.geom.*;
import arc.util.*;
import heavyindustry.graphics.g3d.model.obj.*;
import heavyindustry.graphics.g3d.render.*;

/**
 * Interface for models.
 **/
public interface Model extends Disposable {
    /**
     * Render model.
     *
     * @param renderer renderer that's renders this model
     **/
    void render(Renderer3D renderer);

    /** Dispose model. */
    default void dispose() {}

    /** Clone this models. */
    Model cloneModel();


    ObjectShader getShader();

    void setShader(ObjectShader shader);

    Mat3D getTransformation();

    void setTransformation(Mat3D transformation);

    Vec3 getTranslation();

    void setTranslation(Vec3 translation);
}
