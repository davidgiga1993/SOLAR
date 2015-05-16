package dhbw.karlsruhe.it.solar.core.resources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Superclass for the standard resources of the game which are local and not capacitive (deplete normally).
 * Contrast with GlobalResource and CapacitiveResource.
 * @author Andi
 * created 2015-05-16
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Population.class})
public abstract class StandardResource extends BaseResource implements StandardResourceInterface {

}
