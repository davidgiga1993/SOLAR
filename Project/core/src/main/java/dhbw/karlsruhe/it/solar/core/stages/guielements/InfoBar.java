package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
   
    public final static int IMAGE_WIDTH = 130;
    public final static int CELL_WIDTH = 215;
    public final static int MENUE_CELL_WIDTH = 60;
    public final static int PADDING = 20;
    public final static int MENUE_BUTTON_WIDTH = 58;
    public final static int MENUE_BUTTON_HEIGHT = 25;
    public final static int MENUE_BUTTON_PADDING = 1;
    public final static int ACTION_BUTTON_WIDTH = 130;
    public final static int ACTION_BUTTON_HEIGHT = 30;
    public final static int ACTION_BUTTON_PADDING = 5;
    public static final int MINIMUM_WIDTH = IMAGE_WIDTH + 4*PADDING + 2*CELL_WIDTH + MENUE_CELL_WIDTH + ACTION_BUTTON_WIDTH + 2*ACTION_BUTTON_PADDING;
    public final static String TAB = "            ";
     
    private final Table contentTable = new Table();
    private final InfoBarManager manager = new InfoBarManager();
    
    private SolarActor selectedActor; 
    Image selectedImage = new Image();

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
    
    public void onSelectionChange(Selection newSelection) {
        selectedActor = newSelection.getRepresentative();
        contentTable.clear();
        if(null!=selectedActor) {
            loadContent();
        }
    }

    public void loadContent() {
        contentTable.clear();
        contentTable.add(loadImage()).width(IMAGE_WIDTH).height(IMAGE_WIDTH).left();
        contentTable.add(new InfoBarOverviewTable(selectedActor)).width(InfoBar.CELL_WIDTH).padLeft(InfoBar.PADDING);
        contentTable.add(manager.displayContent(selectedActor)).expandX().fillX().padLeft(InfoBar.PADDING);
        contentTable.add(new InfoBarActionTable(selectedActor)).padLeft(PADDING).right();
    }

    private Image loadImage() {
        selectedImage.setDrawable(new TextureRegionDrawable(selectedActor.getSolarActorTexture()));
        return selectedImage;  
    }

    public InfoBar update() {
        manager.reload();
        return this;
    }

    public InfoBarManagerSettings getSettings() {
        return manager.getSettings();
    }

    public void initSettings(InfoBarManagerSettings settings) {
        manager.initSettings(settings);
    }    
}
