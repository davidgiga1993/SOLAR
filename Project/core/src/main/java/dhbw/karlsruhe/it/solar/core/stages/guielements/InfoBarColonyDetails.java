package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.core.usercontrols.Colony;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class InfoBarColonyDetails extends Table {
    
    private Colony colony;
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    private LabelStyle bold = Styles.BOLDLABEL_STYLE;

    public InfoBarColonyDetails(Colony colony) {
        this.colony = colony;
        generateColonyDetails();
    }

    private void generateColonyDetails() {
        if(null==colony) {
            add(new Label("Unclaimed", bold));
            return;
        }
        add(new Label(colony.getName(),bold)).left();
        add(new Label(colony.getOwner().getName(),colony.getOwner().getColorStyle())).expand().right(); 
        row();
        add(new Label("Population: ",style)).left();
        add(new Label(colony.getPopulationNumbers(),style)).right(); 
    }
}
