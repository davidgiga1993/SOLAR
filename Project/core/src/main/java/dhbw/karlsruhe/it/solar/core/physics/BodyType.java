package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Abstract superclass for handling the type of astronomical bodies.
 * @author Andi
 * Created 2015-04-05
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
@XmlSeeAlso({AsteroidType.class, MoonType.class, PlanetType.class, StarType.class})
public abstract class BodyType {
        
}
