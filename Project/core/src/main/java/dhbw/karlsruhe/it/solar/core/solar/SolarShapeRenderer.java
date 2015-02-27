package dhbw.karlsruhe.it.solar.core.solar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by Arga on 26.02.2015.
 */
public class SolarShapeRenderer extends ShapeRenderer {

// TODO: introduce a second shapeRenderer only for orbits. or render every orbit with this one, as it is faster anyways...
    protected final ImmediateModeRenderer renderer = getRenderer();
    protected final Matrix4 combinedMatrix = new Matrix4();
    protected Color color = getColor();

    /**
     * Some orbits are to big, so that the precision of circle(..) is not sufficient. This method provides a higher accuracy.
     * @param centerX
     * @param centerY
     * @param radius
     * @param segments
     */
    public void orbit(float centerX, float centerY, float radius, int segments) {
        double theta = 2 * MathUtils.PI / (segments);
        // precalculate the sine and cosine
        double c = Math.cos(theta);
        double s = Math.sin(theta);
        double t;

        // we start at angle = 0
        double x = radius;
        double y = 0;

        float vx, vy;

        beginOrbit();
        for(int ii = 0; ++ii < segments;) {
            //output vertex
            renderer.color(color);
            vx = (float) x + centerX;
            vy = (float) y + centerY;
            renderer.vertex(vx, vy, 0);
            //apply the rotation matrix
            t = x;
            x = c * x - s * y;
            y = s * t + c * y;
        }
        end();
    }



    public void orbit(float centerX, float centerY, float radius) {
        orbit(centerX, centerY, radius, (int) Math.sqrt(radius));
    }

    protected void check(int segments) {
        if (segments + renderer.getNumVertices() > renderer.getMaxVertices()) {
            end();
            beginOrbit();
        }
    }

    /** Starts a new batch of shapes. Shapes drawn within the batch will attempt to use the type specified. The call to this method
     * must be paired with a call to {@link #end()}.
     * @see #setAutoShapeType(boolean) */
    public void beginOrbit () {
        combinedMatrix.set(getProjectionMatrix());
        Matrix4.mul(combinedMatrix.val, getTransformMatrix().val);
        renderer.begin(combinedMatrix, GL20.GL_LINE_LOOP);
    }

}
