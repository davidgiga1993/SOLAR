package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Information cell displaying life rating body information.
 * @author Andi
 * 2015-04-14
 */
public class LifeRatingInformationDetails  extends InformationDetails {
    
    public LifeRatingInformationDetails(AstronomicalBody body) {
        bodyInformation(body);
    }
    
    private void bodyInformation(AstronomicalBody body) {
        add(new Label("Life Rating: " + body.getLifeRating().inPercent(),Styles.DEFAULTLABEL_STYLE));      
        row();
        add(new Label("Surface Gravity: " + body.getSurfaceGravity().toString(), Styles.DEFAULTLABEL_STYLE));
        row();
        add(new Label("Surface Temperatures: " + body.getTemperatures().toString(), Styles.DEFAULTLABEL_STYLE));
        row();
        add(new Label("Atmosphere: " + getAmtosphereDetails(body), Styles.DEFAULTLABEL_STYLE));
        row();
        add(new Label("Hydrosphere: " + getHydrosphereDetails(body), Styles.DEFAULTLABEL_STYLE));
        row();
        add(new Label("Biosphere: " + getBiosphereDetails(body), Styles.DEFAULTLABEL_STYLE));     
    }

    private String getBiosphereDetails(AstronomicalBody body) {
        if(null != body.getBiosphere()) {
            return body.getBiosphere().toString();
        }
        return "none";
    }

    private String getHydrosphereDetails(AstronomicalBody body) {
        if(null != body.getHydrosphere()) {
            return body.getHydrosphere().toString();         
        }
        return "none";
    }

    private String getAmtosphereDetails(AstronomicalBody body) {
        if(null != body.getAtmosphere()) {
            return body.getAtmosphere().toString();            
        }
        return "none";
    }
}
