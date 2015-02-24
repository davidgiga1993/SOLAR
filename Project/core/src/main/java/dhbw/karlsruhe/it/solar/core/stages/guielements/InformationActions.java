package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by Arga on 24.02.2015.
 */
public class InformationActions extends Table {

    private static final int ACTION_CELL_WIDTH = 128;
    private static final int ACTION_CELL_HEIGHT = 32;

    public InformationActions() {
        super();
        Label landColonyLabel = new Label("Land Colony", SolarEngine.get().styles.defaultLabelStyle);
        Label enterParkingOrbitLabel = new Label("Enter Parking Orbit", SolarEngine.get().styles.defaultLabelStyle);
        Label partyLabel = new Label("Party!", SolarEngine.get().styles.defaultLabelStyle);
        Label patrolLabel = new Label("Patrol", SolarEngine.get().styles.defaultLabelStyle);
        Label returnToBaseLabel = new Label("Return To Base", SolarEngine.get().styles.defaultLabelStyle);
        Label emptyLabel = new Label("", SolarEngine.get().styles.defaultLabelStyle);

        defaults().width(ACTION_CELL_WIDTH).height(ACTION_CELL_HEIGHT);

        add(landColonyLabel);
        add(enterParkingOrbitLabel);
        add(partyLabel);
        row();
        add(patrolLabel);
        add(returnToBaseLabel);
        add(emptyLabel);

    }
}
