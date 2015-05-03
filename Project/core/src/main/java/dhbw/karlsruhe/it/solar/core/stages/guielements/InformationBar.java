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
public class InformationBar extends Window implements Telegraph {
   
    public final static float IMAGE_WIDTH = 130;
    public final static float CELL_WIDTH = 215;
    public final static float MENUE_CELL_WIDTH = 60;
    public final static float PADDING = 20;
    public final static float MENUE_BUTTON_WIDTH = 58;
    public final static float MENUE_BUTTON_HEIGHT = 25;
    public final static float MENUE_BUTTON_PADDING = 1;
    public final static float ACTION_BUTTON_WIDTH = 130;
    public final static float ACTION_BUTTON_HEIGHT = 30;
    public final static float ACTION_BUTTON_PADDING = 5;
    public final static String TAB = "            ";
     
    private final Table contentTable = new Table();
    private final InfoBarManager manager = new InfoBarManager();
    
    private SolarActor selectedActor; 
    Image selectedImage = new Image();

    public InformationBar() {
        super("Information", Styles.TOOLTIPSKIN);
        SolarEngine.MESSAGE_DISPATCHER.addListener(this, SolarMessageType.PLAYER_SELECTION_CHANGED);
        
        add(contentTable).expandX().fillX();
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
        contentTable.add(new InfoBarOverviewTable(selectedActor)).width(InformationBar.CELL_WIDTH).padLeft(InformationBar.PADDING);
        contentTable.add(manager.displayContent(selectedActor)).expandX().fillX().padLeft(InformationBar.PADDING);
        contentTable.add(new InfoBarActionTable(selectedActor)).padLeft(PADDING).right();
    }

    private Image loadImage() {
        selectedImage.setDrawable(new TextureRegionDrawable(selectedActor.getSolarActorTexture()));
        return selectedImage;  
    }    
}
