package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;
import dhbw.karlsruhe.it.solar.player.Player;

public class ResourceBarPlayerInfo extends Table {
    
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    private GameStartStage stage;
    private Player playerOnThisPlatform;
    
    public ResourceBarPlayerInfo(GameStartStage stage) {
        this.stage = stage;
        determinePlayerOnThisPlatform();
    }

    private void determinePlayerOnThisPlatform() {
        playerOnThisPlatform = stage.getPlayerOnThisPlatform();
    }

    public void update() {
        determinePlayerOnThisPlatform();
        clear();
        setDebug(true);
        if(playerOnThisPlatform!=null) {
            add(playerOverview()).left().width(ConfigurationConstants.GUI_NAVIGATION_WIDTH);
            add(playerDetails()).padLeft(ConfigurationConstants.PADDING).width(ConfigurationConstants.CELL_WIDTH);
            add().padLeft(ConfigurationConstants.PADDING).expandX().fillX();
        }
    }
    
    private Table playerOverview() {
        Table overview = new Table();
        overview.add(new Label("Playing as: ", style)).left();
        overview.add(new Label(playerOnThisPlatform.getName(), playerOnThisPlatform.getColorStyle())).expand().right();
        return overview;
    }
    
    private Table playerDetails() {
        Table details = new Table();
        details.add(new Label("Treasury: ", style)).left();
        details.add(new Label(playerOnThisPlatform.getTreasury().toString(), style)).expand().right(); 
        details.row();
        details.add(new Label("Population: ", style)).left();
        details.add(new Label(playerOnThisPlatform.getPopulation().toString(), style)).expand().right(); 
        return details;
    }
}
