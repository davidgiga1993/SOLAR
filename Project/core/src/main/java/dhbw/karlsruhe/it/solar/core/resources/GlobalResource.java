package dhbw.karlsruhe.it.solar.core.resources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Superclass for globally stored resources which are not bound to a specific place but to a player.
 * @author Andi
 * created 2015-05-16
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({TotalPopulation.class, Credits.class})
public abstract class GlobalResource extends BaseResource implements GlobalResourceInterface {

}
