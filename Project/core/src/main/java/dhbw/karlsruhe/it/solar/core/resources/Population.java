package dhbw.karlsruhe.it.solar.core.resources;


/**
 * Populatio Resource: Each colony has a certain number of inhabitants. Behavior of population is governed by this class.
 * @author Andi
 * Th, 19. March 2015
 */
public class Population {
	private float value;
	private Unit unit;
	
	public enum Unit {
		THOUSAND,
		MILLION,
		BILLION
	}
	
	public Population(float numberOfColonists, Unit populationUnit) {
		value = numberOfColonists;
		unit = populationUnit;
	}
	
	private String formatValue() {
		return String.format("%.02f", value);
	}
	
	public float inThousands() {
        switch(unit) {
        case THOUSAND:
            return value;
        case MILLION:
            return value * 1000;
        case BILLION:
            return value * 1000 * 1000;
        default:
            return 0;
        }
	}
	
	public float inMillions() {
        switch(unit) {
        case THOUSAND:
            return value / 1000;
        case MILLION:
            return value;
        case BILLION:
            return value * 1000;
        default:
            return 0;
        }
	}
	
	public float inBillions() {
        switch(unit) {
        case THOUSAND:
            return value / (1000 * 1000);
        case MILLION:
            return value / 1000;
        case BILLION:
            return value;
        default:
            return 0;
        }
	}
	
	@Override
	public String toString() {
        switch(unit) {
        case THOUSAND:
            return formatValue() + " thousand";
        case MILLION:
            return formatValue() + " million";
        case BILLION:
            return formatValue() + " billion";
        default:
            return "apparently zombified";
        }
	}

}
