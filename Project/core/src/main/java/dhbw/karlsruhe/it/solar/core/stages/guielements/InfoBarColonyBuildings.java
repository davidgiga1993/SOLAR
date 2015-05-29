package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.colony.Colony;
import dhbw.karlsruhe.it.solar.colony.ColonyBuildings;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.commands.ConstructBuildingCommand;
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
    private final Label reactorLabel = new Label("Fission Reactors: ", style);
    private final Label reactorValueLabel = new Label("", style);
    private final TextButton buildReactor = new TextButton("+", Styles.TOOLTIPSKIN);
    
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
        add(infraValueLabel).right().padRight(ConfigurationConstants.INNER_CELL_PADDING);
        add(buildInfra).width(InfoBar.BUILD_BUTTON_WIDTH).height(InfoBar.BUILD_BUTTON_HEIGHT).pad(InfoBar.BUILD_BUTTON_PADDING).right();
        row();
        add(reactorLabel).left();
        add(reactorValueLabel).right().padRight(ConfigurationConstants.INNER_CELL_PADDING);
        add(buildReactor).width(InfoBar.BUILD_BUTTON_WIDTH).height(InfoBar.BUILD_BUTTON_HEIGHT).pad(InfoBar.BUILD_BUTTON_PADDING).right();
    }
    
    public void reload() {
        if(null==buildings) {
            return;
        }
        infraValueLabel.setText(String.valueOf(buildings.getNumberOfBuiltInfrastructure()));
        reactorValueLabel.setText(String.valueOf(buildings.getNumberOfBuiltFissionReactors()));
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
            reactorLabel.setVisible(false);
            reactorValueLabel.setVisible(false);
            buildReactor.setVisible(false);
            return;
        }
        title.setText("Buildings");
        colonyLabel.setText(colony.getName());
        colonyLabel.setStyle(colony.getOwner().getColorStyle());
        colonyLabel.setVisible(true);
        infraLabel.setVisible(true);
        reload();
        infraValueLabel.setVisible(true);
        buildInfra.setVisible(true);    
        reactorLabel.setVisible(true);
        reactorValueLabel.setVisible(true);
        buildReactor.setVisible(true);
    }
    
    private void addBuildButtonListeners() {
        buildInfra.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 onBuildInfraClick();
            }
         });
        buildReactor.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 onBuildReactorClick();
            }
         });
    }

    private void onBuildInfraClick() {
        ConstructBuildingCommand build = new ConstructBuildingCommand(colony);
        build.infrastructure();
    }
    
    private void onBuildReactorClick() {
        ConstructBuildingCommand build = new ConstructBuildingCommand(colony);
        build.fissionReactor();
    }
}
