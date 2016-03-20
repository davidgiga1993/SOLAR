package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.resources.TotalPopulation;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;
import dhbw.karlsruhe.it.solar.player.Player;

class ResourceBarPlayerInfo extends Table {

    private final Table treasuryAlertIcons = new Table();
    private final Table populationAlertIcons = new Table();
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    private final Label playerNameLabel = new Label("", style);
    private final Label treasuryValueLabel = new Label("", style);
    private final Label populationValueLabel = new Label("", style);
    private GameStartStage stage;
    private Player playerOnThisPlatform;
    
    public ResourceBarPlayerInfo(GameStartStage stage) {
        this.stage = stage;
        determinePlayerOnThisPlatform();
    }

    private void determinePlayerOnThisPlatform() {
        if(playerOnThisPlatform == null) {
            playerOnThisPlatform = stage.getPlayerOnThisPlatform();
            if(playerOnThisPlatform != null) {
                add(playerOverview()).left().width(ConfigurationConstants.GUI_NAVIGATION_WIDTH);
                add(playerTreasury()).padLeft(ConfigurationConstants.PADDING).width(ConfigurationConstants.CELL_WIDTH);
                add(playerPopulation()).padLeft(ConfigurationConstants.PADDING).width(ConfigurationConstants.CELL_WIDTH);
                add().padLeft(ConfigurationConstants.PADDING).expandX().fillX();                            
            }
        }
    }

    public void update() {
        determinePlayerOnThisPlatform();
        playerNameLabel.setText(playerOnThisPlatform.getName());
        playerNameLabel.setStyle(playerOnThisPlatform.getColorStyle());
        treasuryValueLabel.setText(playerOnThisPlatform.getTreasury().toString());
        populationValueLabel.setText(playerOnThisPlatform.getTotalPopulation().toString());
        populationAlertIcons.clear();
        populationAlertIcons.add(playerOnThisPlatform.getPopulationAlerts());
    }
    
    private Table playerOverview() {
        Table overview = new Table();
        overview.add(new Label("Playing as: ", style)).left();
        overview.add(playerNameLabel).expand().right();
        return overview;
    }
    
    private Table playerTreasury() {
        Credits treasury = playerOnThisPlatform.getTreasury();
        Table treasuryTable = new Table();
        treasuryTable.add(treasury.getResourceBarTitle()).expandX().left();
        treasuryTable.row();
        treasuryTable.add(treasuryValueLabel).left();
        treasuryTable.row();
        treasuryTable.add(treasuryAlertIcons).left();
        return treasuryTable;
    }
    
    private Table playerPopulation() {
        TotalPopulation population = playerOnThisPlatform.getTotalPopulation();
        Table pop = new Table();
        pop.add(population.getResourceBarTitle()).expandX().left();
        pop.row();
        pop.add(populationValueLabel).left(); 
        pop.row();
        pop.add(populationAlertIcons).left();
        return pop;
    }
}
