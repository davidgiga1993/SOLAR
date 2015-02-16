package dhbw.karlsruhe.it.solar.core.ai.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arga on 13.02.2015.
 */
public interface SteeringProvider {
    /**
     * Berechnet den Kurs zum vorher gesetzten Ziel
     * @param position aktuelle Position des ansteuernden Objektes
     * @return berechneter Kurs
     */
    public Steering getSteering(Kinematic position);

    /**
     * Setzt das Ziel der Kursberechnung
     * @param newTarget
     */
    public void setTarget(Kinematic newTarget);
}