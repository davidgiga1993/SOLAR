package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.Temperature.TempUnit;
import dhbw.karlsruhe.it.solar.core.resources.AtmosphericGas;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Temperature.class})
public class SurfaceTemperature {
    
    private static final float MEAN_BODY_DISTRIBUTION = 1 - 1 / (float)(2*Math.sqrt(2));
    @XmlElement(name = "Min_Temperature")
    private Temperature tempMinimum;
    @XmlElement(name = "Mean_Temperature")
    private Temperature meanTemperature;
    @XmlElement(name = "Max_Temperature")
    private Temperature tempMaximum;
    
    public SurfaceTemperature() {
        
    }
    
    public SurfaceTemperature(Temperature tempMinimum, Temperature meanTemperature, Temperature tempMaximum) {
        this.tempMinimum = tempMinimum;
        this.meanTemperature = meanTemperature;
        this.tempMaximum = tempMaximum;
    }

    public SurfaceTemperature(Temperature temperature) {
        this.tempMinimum = null;
        this.meanTemperature = temperature;
        this.tempMaximum = null;
    }

    public Temperature getMeanTemperature() {
        return meanTemperature;
    }

    public Temperature getMinimumTemperature() {
        if(null!=tempMinimum) {
            return tempMinimum;
        }
        return meanTemperature;
    }

    public Temperature getMaximumTemperature() {
        if(null!=tempMaximum) {
            return tempMaximum;
        }
        return meanTemperature;
    }
    
    @Override
    public String toString() {
        return formatValue(getMeanTemperature()) + " K";
    }

    private String formatValue(Temperature temp) {
        return String.format("%.00f", temp.inKelvin());
    }

    public void calculateSurfaceTemperature(AstronomicalBody body, Albedo albedo) {
        meanTemperature = new Temperature( calculateThermalEquilibrium(body, albedo) + calculateAdditionalTemperatureInfluences(body), TempUnit.KELVIN);      
    }

    private float calculateAdditionalTemperatureInfluences(AstronomicalBody body) {
        return greenhouseEffect(body) + geothermalActivity() + tidalForces();
    }

    private float tidalForces() {
        // TODO Auto-generated method stub
        return 0;
    }

    private float geothermalActivity() {
        // TODO Auto-generated method stub
        return 0;
    }

    private float greenhouseEffect(AstronomicalBody body) {
        if(body.hasAtmosphere()) {
            float greenhouseEffect = 0;
            for (AtmosphericGas gas : body.getListOfAtmosphericGases()) {
                greenhouseEffect += gas.greenhouseEffect(body.getPhysicalProperties());
            }
            return greenhouseEffect;            
        }
        return 0;
    }

    /**
     * Calculates the Temperature of a body according to the thermal equilibrium pricniple.
     * This assumes that the body's only source of incoming energy is its star and that it has reached an equilibrium, radiating away exactly as much energy via black body radiation as it receives.
     * Calculation therefore neglects other possible energy sources such as greenhouse gases, geothermal activtiy, or tidal forces.
     * @param body
     * @param albedo
     * @return
     */
    private float calculateThermalEquilibrium(AstronomicalBody body, Albedo albedo) {
        if(body.isTidallyLockedToStar()) {
            return body.getTemperatureOfStar().inKelvin() * MEAN_BODY_DISTRIBUTION * (float)Math.sqrt(getMaximumTemperatureValueTidallyLocked(body.getRadiusOfStar(), albedo, body.getMeanDistanceToStar()));            
        }
        return body.getTemperatureOfStar().inKelvin() * (float)Math.sqrt(getRelativeTemperatureValue(body.getRadiusOfStar(), albedo, body.getMeanDistanceToStar()));
    }

    private double getMaximumTemperatureValueTidallyLocked(Length radiusOfStar, Albedo albedo, Length orbitalDistance) {
        return radiusOfStar.asMeters()/orbitalDistance.asMeters() * Math.sqrt(( 1 - albedo.getAlbedoValue()) / 2);
    }

    private double getRelativeTemperatureValue(Length radiusOfStar, Albedo albedo, Length orbitalDistance) {
        return radiusOfStar.asMeters() * Math.sqrt( 1 - albedo.getAlbedoValue()) / (2*orbitalDistance.asMeters());
    }

}
