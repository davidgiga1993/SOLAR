package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import dhbw.karlsruhe.it.solar.core.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.inputlisteners.GUIInputListener;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.guielements.InfoBar;
import dhbw.karlsruhe.it.solar.core.stages.guielements.InfoBarManagerSettings;
import dhbw.karlsruhe.it.solar.core.stages.guielements.NavBar;
import dhbw.karlsruhe.it.solar.core.stages.guielements.ResourceBar;
import dhbw.karlsruhe.it.solar.core.stages.guielements.configelements.ScaleDialog;

public class GameHUDStage extends BaseGUIStage {

    private NavBar navigationBar;
    private ResourceBar resourceBar;
    private InfoBar bottomBar;

    private Table guiTable;

    public GameHUDStage(final SolarEngine solarEngine) {
        super(solarEngine, "GameHUD");
    }

    public static int calculateInfoBarMaxWidth() {
        int currentWidth = Gdx.graphics.getWidth();
        if (currentWidth > InfoBar.MINIMUM_WIDTH) {
            return currentWidth;
        }
        return InfoBar.MINIMUM_WIDTH;
    }

    public void init() {
        addListener(new GUIInputListener());

        guiTable = new Table();
        guiTable.setPosition(0, 0);
        guiTable.setFillParent(true);
        guiTable.setWidth(Gdx.graphics.getWidth());
        guiTable.setHeight(Gdx.graphics.getHeight());
        guiTable.top().left();
        if (SolarEngine.DEBUG) {
            guiTable.debug();
        }

        navigationBar = new NavBar();
        resourceBar = new ResourceBar(se.getGameStage());
        bottomBar = new InfoBar();

        guiTable.add(resourceBar).align(Align.right).colspan(2).height(ConfigurationConstants.RESOURCE_BAR_HEIGHT).expandX().fillX();
        guiTable.row();
        guiTable.add(navigationBar).expandY().width(ConfigurationConstants.GUI_NAVIGATION_WIDTH).maxHeight(calculateNavbarMaxHeight()).top().fill().left();
        guiTable.add(new Actor()).expandX();
        guiTable.row().maxHeight(ConfigurationConstants.INFO_BAR_HEIGHT);
        guiTable.add(bottomBar).height(ConfigurationConstants.INFO_BAR_HEIGHT).maxWidth(calculateInfoBarMaxWidth()).colspan(2).expandX().fill().left();


        addActor(guiTable);

        if (ConfigurationConstants.SCALE_DIALOG_ENABLED) {
            ScaleDialog.createScaleDialog(this);
        }
    }

    private int calculateNavbarMaxHeight() {
        return Gdx.graphics.getHeight() - (ConfigurationConstants.RESOURCE_BAR_HEIGHT + ConfigurationConstants.INFO_BAR_HEIGHT);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        guiTable.getCell(navigationBar).maxHeight(calculateNavbarMaxHeight());
        guiTable.getCell(bottomBar.update()).maxWidth(calculateInfoBarMaxWidth());
    }

    public InfoBarManagerSettings getSettings() {
        return bottomBar.getSettings();
    }

    public void initSettings(InfoBarManagerSettings settings) {
        bottomBar.initSettings(settings);
    }

    public void update() {
        resourceBar.update();
        bottomBar.update();
    }
}

