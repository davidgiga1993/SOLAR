package dhbw.karlsruhe.it.solar.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.inputlisteners.GUIInputListener;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.guielements.BottomBarGUIElement;
import dhbw.karlsruhe.it.solar.core.stages.guielements.NavigationBarGUIElement;
import dhbw.karlsruhe.it.solar.core.stages.guielements.ResourceBarGUIElement;
import dhbw.karlsruhe.it.solar.core.stages.guielements.configelements.ScaleDialog;

public class GameHUDStage extends BaseGUIStage{
	

    private NavigationBarGUIElement navigationBar;
    private ResourceBarGUIElement resourceBar;
    private BottomBarGUIElement bottomBar;

    private Table guiTable;

	public GameHUDStage(final SolarEngine solarEngine) {
		super(solarEngine, "GameHUD");

        this.addListener(new GUIInputListener());

        guiTable = new Table();
        guiTable.setPosition(0,0);
        guiTable.setFillParent(false);
        guiTable.setWidth(Gdx.graphics.getWidth());
        guiTable.setHeight(Gdx.graphics.getHeight());
        guiTable.top().left();
        if(SolarEngine.DEBUG) {
            guiTable.debug();
        }

        navigationBar = new NavigationBarGUIElement(solarEngine.styles.defaultLabelStyle, this);
        resourceBar = new ResourceBarGUIElement(solarEngine.styles.defaultLabelStyle, this);
        bottomBar = new BottomBarGUIElement(solarEngine.styles.defaultLabelStyle);

        guiTable.add(resourceBar.resourceBar).align(Align.right).colspan(2).height(50).expandX();
        guiTable.row();
        guiTable.add(navigationBar.navigationBar).expandY().top().fill();
        guiTable.add(new Actor()).expandX();
        guiTable.row().maxHeight(75);
        guiTable.add(bottomBar.root).height(75).colspan(2).expandX().fill();


        addActor(guiTable);
        //navigationBar.addTo(this);
        //resourceBar.addTo(this);

        if(ConfigurationConstants.SCALE_DIALOG_ENABLED) {
            ScaleDialog.createScaleDialog(this);
        }

	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        guiTable.invalidateHierarchy();
        guiTable.setSize(width, height);
    }

}

