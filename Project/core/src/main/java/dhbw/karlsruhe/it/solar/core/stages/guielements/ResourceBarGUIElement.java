package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by Arga on 16.11.2014.
 */
public class ResourceBarGUIElement {

    public final SolarEngine engine = SolarEngine.get();

    public Table resourceBar;


    public ResourceBarGUIElement(Label.LabelStyle labelStyle) {
        resourceBar = new Table();

        resourceBar.row();
        resourceBar.add(new Label("", labelStyle)).uniform();
        resourceBar.add(new Label("Value", labelStyle)).uniform();
        resourceBar.add(new Label("Raise Rate", labelStyle)).uniform();
        resourceBar.row();
        resourceBar.add(new Label("Credits", labelStyle)).uniform();
        resourceBar.add(new Label(String.valueOf(engine.Service.credits.getValue()), labelStyle));
        resourceBar.add(new Label(String.valueOf(engine.Service.credits.getRaiseRate()), labelStyle));

    }
}
