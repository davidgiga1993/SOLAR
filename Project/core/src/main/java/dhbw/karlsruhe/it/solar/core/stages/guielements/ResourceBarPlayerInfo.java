package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

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
        if(playerOnThisPlatform!=null) {
            add(new Label("Playing as: ", style)).left();
            add(new Label(playerOnThisPlatform.getName(), playerOnThisPlatform.getColorStyle())).expand().right(); 
            row(); 
            add(new Label("Total Population: ", style)).left();
            add(new Label(playerOnThisPlatform.getTotalPopulation().toString(), style)).expand().right(); 
        }
    }
}
