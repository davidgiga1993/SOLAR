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
   
    private final static float IMAGE_SIZE = 130;
    public final static float CELL_SIZE = 215;
    public final static float PADDING = 20;
    public final static float BUTTON_WIDTH = 130;
    public final static float BUTTON_HEIGHT = 30;
    public final static float BUTTON_PADDING = 5;
    public final static String TAB = "            ";
     
    private Table contentTable = new Table();
    
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

    private void loadContent() {
        contentTable.add(loadImage()).width(IMAGE_SIZE).height(IMAGE_SIZE).left();
        contentTable.add(new InfoBarDetailsTable(selectedActor)).padLeft(PADDING).expandX().fillX();
        contentTable.add(new InfoBarActionTable(selectedActor)).padLeft(PADDING).right();
    }

    private Image loadImage() {
        selectedImage.setDrawable(new TextureRegionDrawable(selectedActor.getSolarActorTexture()));
        return selectedImage;  
    }
    
}
