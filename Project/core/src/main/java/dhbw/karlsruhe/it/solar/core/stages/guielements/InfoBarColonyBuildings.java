package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.colony.Colony;
import dhbw.karlsruhe.it.solar.colony.ColonyBuildings;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

public class InfoBarColonyBuildings extends Table {
    
    private Colony colony;
    private ColonyBuildings buildings;
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    
    private final Label title = new Label("", Styles.BOLDLABEL_STYLE);
    private final Label colonyLabel = new Label("", style);
    private final Label infraLabel = new Label("Infrastructure: ", style);
    private final Label infraValueLabel = new Label("", style);
    private final TextButton buildInfra = new TextButton("+", Styles.TOOLTIPSKIN);
    
    public InfoBarColonyBuildings() {
        generateBuildingDetails();
        addBuildButtonListeners();
    }

    private void generateBuildingDetails() {
        add(title).left();
        add().expandX();
        add(colonyLabel).right(); 
        row();
        add(infraLabel).left();
        add(infraValueLabel).right().padRight(InfoBar.INNER_CELL_PADDING);
        add(buildInfra).width(InfoBar.BUILD_BUTTON_WIDTH).height(InfoBar.BUILD_BUTTON_HEIGHT).pad(InfoBar.BUILD_BUTTON_PADDING).right();
    }
    
    public void reload() {
        if(null==buildings) {
            return;
        }
        infraValueLabel.setText(String.valueOf(buildings.getNumberOfBuiltInfrastructure()));
    }

    public InfoBarColonyBuildings show(AstronomicalBody selectedBody) {
        this.colony = selectedBody.getColony();
        if(null!=colony) {
            this.buildings = colony.getColonyBuildings();            
        }
        init();
        return this;
    }
    
    private void init() {
        if(null==colony) {
            title.setText("Empty");
            colonyLabel.setVisible(false);
            infraLabel.setVisible(false);
            infraValueLabel.setVisible(false);
            buildInfra.setVisible(false);
            return;
        }
        title.setText("Buildings");
        colonyLabel.setText(colony.getName());
        colonyLabel.setStyle(colony.getOwner().getColorStyle());
        colonyLabel.setVisible(true);
        infraLabel.setVisible(true);
        infraValueLabel.setText(String.valueOf(buildings.getNumberOfBuiltInfrastructure()));
        infraValueLabel.setVisible(true);
        buildInfra.setVisible(true);
    }
    
    private void addBuildButtonListeners() {
        buildInfra.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 onBuildInfraClick();
            }
         });
    }

    private void onBuildInfraClick() {
        buildings.buildInfrastructure();
    }
}
