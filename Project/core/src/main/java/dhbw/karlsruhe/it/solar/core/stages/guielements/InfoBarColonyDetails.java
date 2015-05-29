package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.colony.Colony;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class InfoBarColonyDetails extends Table {
    
    private Colony colony;
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    private LabelStyle bold = Styles.BOLDLABEL_STYLE;
    private final Label colonyName = new Label("", bold);
    private final Label colonyOwner = new Label("", style);
    private final Label populationLabel = new Label("Population: ", style);
    private final Label populationValueLabel = new Label("", style);
    private final Label lifeSupportLabel = new Label("Life Support: ", style);
    private final Label lifeSupportValueLabel = new Label("", style);
    private final Label electricPowerLabel = new Label("Electric Power: ", style);
    private final Label electricPowerValueLabel = new Label("", style);

    public InfoBarColonyDetails() {
        generateColonyDetails();
    }

    private void generateColonyDetails() {
        add(colonyName).left();
        add(colonyOwner).expand().right(); 
        row();
        add(populationLabel).left();
        add(populationValueLabel).right(); 
        row();
        add(lifeSupportLabel).left();
        add(lifeSupportValueLabel).right(); 
        row();
        add(electricPowerLabel).left();
        add(electricPowerValueLabel).right(); 
    }

    public void reload() {
        if(null==colony) {
            return;
        }
        populationValueLabel.setText(colony.getPopulationInformation());
        lifeSupportValueLabel.setText(colony.getLifeSupportInformation());
        lifeSupportValueLabel.setStyle(colony.getLifeSupportDisplayStyle());
        electricPowerValueLabel.setText(colony.getPowerInformation());
        electricPowerValueLabel.setStyle(colony.getPowerDisplayStyle());
    }

    public InfoBarColonyDetails show(AstronomicalBody selectedBody) {
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
            lifeSupportLabel.setVisible(false);
            lifeSupportValueLabel.setVisible(false);
            electricPowerLabel.setVisible(false);
            electricPowerValueLabel.setVisible(false);
            return;
        }
        colonyName.setText(colony.getName());
        colonyOwner.setText(colony.getOwner().getName());
        colonyOwner.setStyle(colony.getOwner().getColorStyle());
        colonyOwner.setVisible(true);
        reload();
        populationLabel.setVisible(true);
        populationValueLabel.setVisible(true);
        lifeSupportLabel.setVisible(true);
        lifeSupportValueLabel.setVisible(true);
        electricPowerLabel.setVisible(true);
        electricPowerValueLabel.setVisible(true);
    }
}
