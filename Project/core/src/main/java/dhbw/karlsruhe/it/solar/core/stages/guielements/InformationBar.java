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
    private final static float CELL_SIZE = 200;
    private final static float ACTION_TABLE_SIZE = 420;
    private final static float CELL_PADDING = 20;
    private final static float BUTTON_WIDTH = 130;
    private final static float BUTTON_HEIGHT = 30;
    private final static float BUTTON_PADDING = 5;
    
    private Table contentTable = new Table();
    private final InfoBarOverviewTable overviewTable = new InfoBarOverviewTable();
    private final InfoBarDetailsTable detailsTable = new InfoBarDetailsTable();
    private final InfoBarActionTable actionTable = new InfoBarActionTable(BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_PADDING);
    
    private SolarActor selectedActor; 
    Image selectedImage = new Image();

    public InformationBar() {
        super("Information", Styles.TOOLTIPSKIN);
        SolarEngine.MESSAGE_DISPATCHER.addListener(this, SolarMessageType.PLAYER_SELECTION_CHANGED);
        
        add(contentTable).expand();
        contentTable.setDebug(true);
        loadEmptyContent();
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
        contentTable.setDebug(true);
        if(null!=selectedActor) {
            overviewTable.update(selectedActor);
            detailsTable.update(selectedActor);
            actionTable.update(selectedActor);
            loadContent();
        }
        if(null==selectedActor) {
            actionTable.hideAllButtons();
            loadEmptyContent();            
        }
    }

    private void loadContent() {
        contentTable.add(loadImage()).width(IMAGE_SIZE).height(IMAGE_SIZE);
        contentTable.add(overviewTable).width(CELL_SIZE).padLeft(CELL_PADDING);
        contentTable.add(detailsTable).width(CELL_SIZE).padLeft(CELL_PADDING);
        contentTable.add(actionTable).width(ACTION_TABLE_SIZE).padLeft(CELL_PADDING);
    }

    private void loadEmptyContent() {
        contentTable.add().width(IMAGE_SIZE).height(IMAGE_SIZE);
        contentTable.add().width(CELL_SIZE).padLeft(CELL_PADDING);
        contentTable.add().width(CELL_SIZE).padLeft(CELL_PADDING);
        contentTable.add().width(ACTION_TABLE_SIZE).padLeft(CELL_PADDING);
    }

    private Image loadImage() { 
        selectedImage.setDrawable(new TextureRegionDrawable(selectedActor.getSolarActorTexture()));
        return selectedImage;  
    }
    
}
