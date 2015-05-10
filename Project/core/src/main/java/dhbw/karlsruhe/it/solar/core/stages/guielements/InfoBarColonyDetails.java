package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Colony;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class InfoBarColonyDetails extends Table {
    
    private Colony colony;
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    private LabelStyle bold = Styles.BOLDLABEL_STYLE;
    private final Label colonyName = new Label("", bold);
    private final Label colonyOwner = new Label("", style);
    private final Label populationLabel = new Label("Population: ", style);
    private final Label populationValueLabel = new Label("", style);

    public InfoBarColonyDetails() {
        generateColonyDetails();
    }

    private void generateColonyDetails() {
        add(colonyName).left();
        add(colonyOwner).expand().right(); 
        row();
        add(populationLabel).left();
        add(populationValueLabel).right(); 
    }

    public void reload() {
        if(null==colony) {
            return;
        }
        populationValueLabel.setText(colony.getPopulationNumbers());
    }

    public Actor show(AstronomicalBody selectedBody) {
        this.colony = selectedBody.getColony();
        init();
        return this;
    }

    private void init() {
        if(null==colony) {
            colonyName.setText("Unclaimed");
            colonyOwner.setVisible(false);
            populationLabel.setVisible(false);
            populationValueLabel.setVisible(false);
            return;
        }
        colonyName.setText(colony.getName());
        colonyOwner.setText(colony.getOwner().getName());
        colonyOwner.setStyle(colony.getOwner().getColorStyle());
        colonyOwner.setVisible(true);
        populationLabel.setVisible(true);
        populationValueLabel.setText(colony.getPopulationNumbers());
        populationValueLabel.setVisible(true);
    }
}
