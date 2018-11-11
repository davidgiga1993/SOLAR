package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

class InfoBarShipDetails extends Table {

    private SpaceUnit unit;
    private LabelStyle style = Styles.MENU_LABEL_STYLE;

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
