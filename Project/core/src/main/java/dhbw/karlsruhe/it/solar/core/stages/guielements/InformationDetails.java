package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by Arga on 24.02.2015.
 */
public class InformationDetails extends Label {

    public InformationDetails() {
        super("Details", SolarEngine.get().styles.defaultLabelStyle);
    }
}
