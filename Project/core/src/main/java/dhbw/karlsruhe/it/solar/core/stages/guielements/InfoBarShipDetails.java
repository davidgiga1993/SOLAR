package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class InfoBarShipDetails extends Table {
    
    private SpaceUnit unit;
    private LabelStyle style = Styles.MENUELABEL_STYLE;

    public InfoBarShipDetails(SpaceUnit unit) {
        this.unit = unit;
        generateUnitDetails();
    }

    private void generateUnitDetails() {
        add(new Label("Owner: ", style)).left();
        add(new Label(unit.getOwner().getName(), unit.getOwner().getColorStyle())).expand().right(); 
        row();
    }
}