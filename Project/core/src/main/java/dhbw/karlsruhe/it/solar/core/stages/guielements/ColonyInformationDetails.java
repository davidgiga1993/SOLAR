package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Information cell displaying info to settled colonies.
 * @author Andi
 * 2015-04-14
 */
public class ColonyInformationDetails  extends InformationDetails {
    
    public ColonyInformationDetails(AstronomicalBody body) {
        colonyInformation(body);
    }
    
    private void colonyInformation(AstronomicalBody body) {
        //TODO: Proper implementation - how will this fit into the current design?
        if(body.isColonized()) {
            row();
            add(new Label("Population: " + body.getPopulationNumbers(),Styles.DEFAULTLABEL_STYLE));
        }        
    }
}
