package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.inputlisteners.Selection;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 24.02.2015.
 * @author Andi
 * Substantially revised by Andi on: 2015-04-19
 */
public class InfoBar extends Window implements Telegraph {
   
    public final static int MENUE_CELL_WIDTH = 60;
    public final static int MENUE_BUTTON_WIDTH = 58;
    public final static int MENUE_BUTTON_HEIGHT = 25;
    public final static int MENUE_BUTTON_PADDING = 1;
    public final static int BUILD_BUTTON_WIDTH = 17;
    public final static int BUILD_BUTTON_HEIGHT = 17;
    public final static int BUILD_BUTTON_PADDING = 1;
    public final static int ACTION_BUTTON_WIDTH = 130;
    public final static int ACTION_BUTTON_HEIGHT = 30;
    public final static int ACTION_BUTTON_PADDING = 5;
    public static final int MINIMUM_WIDTH = ConfigurationConstants.GUI_NAVIGATION_WIDTH + 4*ConfigurationConstants.PADDING + 2*ConfigurationConstants.CELL_WIDTH + MENUE_CELL_WIDTH + ACTION_BUTTON_WIDTH + 2*ACTION_BUTTON_PADDING;
    public final static String TAB = "            ";
    private final static int IMAGE_WIDTH = 135;
    private final static int IMAGE_HEIGHT = 135;
    private final Table contentTable = new Table();
    private final InfoBarOverviewTable overview = new InfoBarOverviewTable();
    private final InfoBarManager manager = new InfoBarManager();

    private SolarActor selectedActor;
    private Image selectedImage = new Image();

    public InfoBar() {
        super("Information", Styles.TOOLTIPSKIN);
        SolarEngine.MESSAGE_DISPATCHER.addListener(this, SolarMessageType.PLAYER_SELECTION_CHANGED);
        
        add(contentTable).expandX().fillX().left();
    }

    @Override
    public boolean handleMessage(Telegram telegram) {
        if(telegram.message == SolarMessageType.PLAYER_SELECTION_CHANGED) {
            onSelectionChange((Selection) telegram.sender);
            return true;
        }
        return false;
    }

    private void onSelectionChange(Selection newSelection) {
        selectedActor = newSelection.getRepresentative();
        contentTable.clear();
        if(null!=selectedActor) {
            loadContent();
        }
    }

    private void loadContent() {
        contentTable.clear();
        contentTable.add(loadImage()).width(ConfigurationConstants.GUI_NAVIGATION_WIDTH).left();
        contentTable.add(overview.displayOverview(selectedActor)).width(ConfigurationConstants.CELL_WIDTH).padLeft(ConfigurationConstants.PADDING);
        contentTable.add(manager.displayContent(selectedActor)).expand().fill().padLeft(ConfigurationConstants.PADDING);
        contentTable.add(new InfoBarActionTable(selectedActor)).padLeft(ConfigurationConstants.PADDING).right();
    }

    private Table loadImage() {
        Table imageTable = new Table();
        selectedImage.setDrawable(new TextureRegionDrawable(selectedActor.getSolarActorTexture()));
        imageTable.add(selectedImage).width(IMAGE_WIDTH).height(IMAGE_HEIGHT);
        return imageTable;  
    }

    public InfoBar update() {
        if(null!=selectedActor) {
            overview.reload();
            manager.reload();
        }
        return this;
    }

    public InfoBarManagerSettings getSettings() {
        return manager.getSettings();
    }

    public void initSettings(InfoBarManagerSettings settings) {
        manager.initSettings(settings);
    }    
}
