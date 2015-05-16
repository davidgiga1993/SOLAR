package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.resources.BaseResourceInterface;
import dhbw.karlsruhe.it.solar.core.resources.Credits;
import dhbw.karlsruhe.it.solar.core.resources.Population;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;
import dhbw.karlsruhe.it.solar.player.Player;

public class ResourceBarPlayerInfo extends Table {
    
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    private GameStartStage stage;
    private Player playerOnThisPlatform;
    private final Label playerNameLabel = new Label("", style);
    private final Label treasuryValueLabel = new Label("", style);
    private final Label populationValueLabel = new Label("", style);
    
    public ResourceBarPlayerInfo(GameStartStage stage) {
        this.stage = stage;
        determinePlayerOnThisPlatform();
    }

    private void determinePlayerOnThisPlatform() {
        if(playerOnThisPlatform == null) {
            playerOnThisPlatform = stage.getPlayerOnThisPlatform();
            if(playerOnThisPlatform != null) {
                add(playerOverview()).left().width(ConfigurationConstants.GUI_NAVIGATION_WIDTH);
                add(playerDetails()).padLeft(ConfigurationConstants.PADDING).width(ConfigurationConstants.CELL_WIDTH);
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
    }
    
    private Table playerOverview() {
        Table overview = new Table();
        overview.add(new Label("Playing as: ", style)).left();
        overview.add(playerNameLabel).expand().right();
        return overview;
    }
    
    private Table playerDetails() {
        Table details = new Table();
        details.add(new Label("Treasury: ", style)).left();
        details.add(treasuryValueLabel).expand().right();
        details.add(loadIcon(new Credits()));
        details.row();
        details.add(new Label("Population: ", style)).left();
        details.add(populationValueLabel).expand().right(); 
        details.add(loadIcon(new Population()));
        return details;
    }

    private Table loadIcon(BaseResourceInterface resource) {
        Table imageTable = new Table();
        Image selectedImage = new Image();
        selectedImage.setDrawable(new TextureRegionDrawable(resource.getIcon()));
        imageTable.add(selectedImage).width(ConfigurationConstants.ICON_SIZE).height(ConfigurationConstants.ICON_SIZE);
        return imageTable;  
    }
}
