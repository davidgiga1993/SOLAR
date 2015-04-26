package dhbw.karlsruhe.it.solar.core.physics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.physics.Temperature.TempUnit;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Temperature.class})
public class SurfaceTemperature {
    
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
        meanTemperature = new Temperature( calculateTemperatureSolarRadiation(body, albedo) + calculateAdditionalTemperatureInfluences(), TempUnit.KELVIN);      
    }

    private float calculateAdditionalTemperatureInfluences() {
        return greenhouseEffect() + geothermalActivity() + tidalForces();
    }

    private float tidalForces() {
        // TODO Auto-generated method stub
        return 0;
    }

    private float geothermalActivity() {
        // TODO Auto-generated method stub
        return 0;
    }

    private float greenhouseEffect() {
        // TODO Auto-generated method stub
        return 0;
    }

    private float calculateTemperatureSolarRadiation(AstronomicalBody body, Albedo albedo) {
        if(body.isTidallyLockedToStar()) {
            return body.getTemperatureOfStar().inKelvin() * (float)Math.sqrt(getRelativeTemperatureValueTidallyLocked(body.getRadiusOfStar(), albedo, body.getMeanDistanceToStar())) / 1.543f;            
        }
        return body.getTemperatureOfStar().inKelvin() * (float)Math.sqrt(getRelativeTemperatureValue(body.getRadiusOfStar(), albedo, body.getMeanDistanceToStar()));
    }

    private double getRelativeTemperatureValueTidallyLocked(Length radiusOfStar, Albedo albedo, Length orbitalDistance) {
        return radiusOfStar.asMeters()/orbitalDistance.asMeters() * Math.sqrt(( 1 - albedo.getAlbedoValue()) / 2);
    }

    private double getRelativeTemperatureValue(Length radiusOfStar, Albedo albedo, Length orbitalDistance) {
        return radiusOfStar.asMeters() * Math.sqrt( 1 - albedo.getAlbedoValue()) / (2*orbitalDistance.asMeters());
    }

}
