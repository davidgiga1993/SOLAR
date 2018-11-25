package dhbw.karlsruhe.it.solar.core.utils;

import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActorScale;

/**
 * Created by Arga on 03.01.2016.
 */
public class LengthConverter {

    private LengthConverter() {
    }

    /**
     * This method will use the orbitScale of the given SolarActorScale
     *
     * @param lengthInPixels <
     * @param scale          <
     * @return length
     */
    public static Length toPhysical(float lengthInPixels, SolarActorScale scale) {
        float orbitScale = scale.getOrbitScale();
        float lengthKm = (float) SolarActor.scaleDistanceToPhysical(lengthInPixels / orbitScale);
        return new Length(lengthKm, Length.DistanceUnit.KILOMETERS);
    }

    /**
     * This method will use the orbitScale of the given SolarActorScale
     *
     * @param length <
     * @param scale  <
     * @return length in pixels
     */
    public static float toPixels(Length length, SolarActorScale scale) {
        double scaledLength = length.asKilometers() * scale.getOrbitScale();
        return (float) SolarActor.scaleDistanceToStage(scaledLength);
    }
}
