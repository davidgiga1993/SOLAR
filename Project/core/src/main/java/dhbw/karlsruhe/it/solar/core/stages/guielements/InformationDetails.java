package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Arga on 24.02.2015.
 */
public class InformationDetails extends Table {

    protected static final float NAME_WIDTH = 155;
    protected static final float VALUE_WIDTH = 100;

    public InformationDetails() {
        super();
        left();
        defaults().pad(3).width(NAME_WIDTH);
    }
}
