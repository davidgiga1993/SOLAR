package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;

public interface CenterOfOrbit {
    float getX();
    double getXDouble();

    float getWidth();

    float getY();
    double getYDouble();

    float getHeight();

    Mass getMass();

    /**
     * This interface should only be implemented by AstronomicalBody.
     * Workaround to increase testability until I find the time to split Physics and rendering
     */
    default AstronomicalBody asAstronomicalBody() {
        return (AstronomicalBody) this;
    }
}
