package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.inputlisteners.GUIInputListener;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.guielements.InformationBar;
import dhbw.karlsruhe.it.solar.core.stages.guielements.NavigationBar;
import dhbw.karlsruhe.it.solar.core.stages.guielements.TimeTable;
import dhbw.karlsruhe.it.solar.core.stages.guielements.configelements.ScaleDialog;

public class GameHUDStage extends BaseGUIStage{

    private static final int INFOBAR_HEIGHT = 160;
    private NavigationBar navigationBar;
    private Table resourceBar;
    private InformationBar bottomBar;

    private Table guiTable;

public GameHUDStage(final SolarEngine solarEngine) {
super(solarEngine, "GameHUD");
}

    public void init() {
        addListener(new GUIInputListener());

        guiTable = new Table();
        guiTable.setPosition(0,0);
        guiTable.setFillParent(true);
        guiTable.setWidth(Gdx.graphics.getWidth());
        guiTable.setHeight(Gdx.graphics.getHeight());
        guiTable.top().left();
        if(SolarEngine.DEBUG) {
            guiTable.debug();
        }

        navigationBar = new NavigationBar();
        resourceBar = new TimeTable();
        bottomBar = new InformationBar();

        guiTable.add(resourceBar).align(Align.right).colspan(2).height(50).expandX();
        guiTable.row();
        int maxHeight = Gdx.graphics.getHeight() - (50+150);
        guiTable.add(navigationBar).expandY().width(ConfigurationConstants.GUI_NAVIGATION_WIDTH).maxHeight(maxHeight).top().fill();
        guiTable.add(new Actor()).expandX();
        guiTable.row().maxHeight(INFOBAR_HEIGHT);
        guiTable.add(bottomBar).height(INFOBAR_HEIGHT).colspan(2).expandX().fill();


        addActor(guiTable);

        if(ConfigurationConstants.SCALE_DIALOG_ENABLED) {
            ScaleDialog.createScaleDialog(this);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        int maxHeight = Gdx.graphics.getHeight() - (50+75);
        guiTable.getCell(navigationBar).maxHeight(maxHeight);
    }
}

