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
    private final ImmediateModeRenderer renderer = getRenderer();
    private final Matrix4 combinedMatrix = new Matrix4();
    private Color color = getColor();
    private final boolean disabled = false;

    /**
     * Some orbits are to big, so that the precision of circle(..) is not sufficient. This method provides a higher accuracy.
     *
     * @param radius
     * @param segments
     */
    public void orbit(double radius, int segments) {
        if(disabled) {
            return;
        }
        double theta = 2 * MathUtils.PI / (segments);
        // pre-calculate the sine and cosine
        // TODO: revise precision (Pluto's a jerk)
        // TODO: [Performance Potential] Cache orbit vertices instead of recalculating them on every frame
        double c = Math.cos(theta);
        double s = Math.sin(theta);
        double t;

        // we start at angle = 0
        double x = radius;
        double y = 0;

        float vx, vy;

        beginOrbit();
        for (int ii = 0; ++ii < segments; ) {
            //output vertex
            renderer.color(color);
            vx = (float) x;
            vy = (float) y;
            renderer.vertex(vx, vy, 0);
            //apply the rotation matrix
            t = x;
            x = c * x - s * y;
            y = s * t + c * y;
        }
        end();
    }



    public void orbit(double radius) {
        orbit(radius, (int) Math.sqrt(radius));
    }

    protected void check(int segments) {
        if (segments + renderer.getNumVertices() > renderer.getMaxVertices()) {
            end();
            beginOrbit();
        }
    }

    /**
     * Starts a new batch of shapes. Shapes drawn within the batch will attempt to use the type specified. The call to this method
     * must be paired with a call to {@link #end()}.
     *
     * @see #setAutoShapeType(boolean)
     */
    private void beginOrbit() {
        combinedMatrix.set(getProjectionMatrix());
        Matrix4.mul(combinedMatrix.val, getTransformMatrix().val);
        renderer.begin(combinedMatrix, GL20.GL_LINE_LOOP);
    }

}
