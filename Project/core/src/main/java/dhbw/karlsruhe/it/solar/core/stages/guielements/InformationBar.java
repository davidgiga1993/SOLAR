package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import dhbw.karlsruhe.it.solar.core.inputlisteners.Selection;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * 
 * @author Andi
 * 2015-04-19
 */
public class InformationBar extends Window implements Telegraph {
   
    private final static float IMAGE_SIZE = 120;
    private final static float CELL_SIZE = 180;
    private final static float ACTION_TABLE_SIZE = 400;
    private final static float CELL_PADDING = 20;
    
    private Table contentTable = new Table();
    
    private SolarActor selectedActor; 
    Image selectedImage = new Image();

    public InformationBar() {
        super("Information", Styles.TOOLTIPSKIN);
        SolarEngine.MESSAGE_DISPATCHER.addListener(this, SolarMessageType.PLAYER_SELECTION_CHANGED);
        
        add(contentTable).expand();
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
            contentTable.add(loadImage()).width(IMAGE_SIZE).height(IMAGE_SIZE);
            contentTable.add(loadOverview()).width(CELL_SIZE).padLeft(CELL_PADDING);
            contentTable.add().width(CELL_SIZE).padLeft(CELL_PADDING);
            contentTable.add().width(ACTION_TABLE_SIZE).padLeft(CELL_PADDING);
            return;
        }
        contentTable.add().width(IMAGE_SIZE).height(IMAGE_SIZE);
        contentTable.add().width(CELL_SIZE).height(IMAGE_SIZE).padLeft(CELL_PADDING);
        contentTable.add().width(CELL_SIZE).height(IMAGE_SIZE).padLeft(CELL_PADDING);
        contentTable.add().width(ACTION_TABLE_SIZE).height(IMAGE_SIZE).padLeft(CELL_PADDING);
    }

    private Table loadOverview() {
        Table overview = new Table();
        overview.add(new Label(selectedActor.getName(),Styles.DEFAULTLABEL_STYLE)).expand().top().left();
        overview.row();
        overview.add(new Label("Type: ",Styles.DEFAULTLABEL_STYLE)).left();
        overview.add(new Label(selectedActor.getTypeName(),Styles.DEFAULTLABEL_STYLE)).right();
        overview.row();
        generateMissionInfo(overview);
        overview.row().height(CELL_PADDING*2);
        overview.add(new InfoBarNavigationLabel("Show On Map", "", selectedActor)).left();
        return overview;
    }

    private void generateMissionInfo(Table overview) {
        if(((Orbiter)selectedActor).isInOrbit()) {
            overview.add(new Label("In Orbit of: ",Styles.DEFAULTLABEL_STYLE)).left();
            overview.add(new Label(((Orbiter)selectedActor).getNameOfPrimary(),Styles.DEFAULTLABEL_STYLE)).right();
            overview.row();
            overview.add(new Label("Orbital Period: ",Styles.DEFAULTLABEL_STYLE)).left();
            overview.add(new Label(((Orbiter)selectedActor).getOrbitalPeriod().toString(),Styles.DEFAULTLABEL_STYLE)).right();
            return;
        }
        overview.add(new Label("En route to: ",Styles.DEFAULTLABEL_STYLE)).left();
        overview.add(new Label(((SpaceUnit)selectedActor).getMission(),Styles.DEFAULTLABEL_STYLE)).right();
        overview.row();
        overview.add(new Label("ETA: ",Styles.DEFAULTLABEL_STYLE)).left();
        //TODO: Implementiere ETA-Funktionalität aus der AI-Berechnung raus (Schätzung okay)
        overview.add(new Label("Unknown",Styles.DEFAULTLABEL_STYLE)).right();
    }

    private Image loadImage() { 
        selectedImage.setDrawable(new TextureRegionDrawable(selectedActor.getSolarActorTexture()));
        return selectedImage;  
    }
    
}
