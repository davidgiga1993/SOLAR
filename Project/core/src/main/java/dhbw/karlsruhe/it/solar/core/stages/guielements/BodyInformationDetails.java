package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;


/**
 * Created by Arga on 24.02.2015.
 */
public class BodyInformationDetails extends InformationDetails {
    
    private Table detailsTable = new Table();

    public BodyInformationDetails(AstronomicalBody body) {
        super();

        detailsTable.add(new OrbitalInformationDetails(body)).left();
        detailsTable.add(new LifeRatingInformationDetails(body)).right();
        detailsTable.add(new ColonyInformationDetails(body)).right();
        
        add(detailsTable).expand().fill();
    }
}