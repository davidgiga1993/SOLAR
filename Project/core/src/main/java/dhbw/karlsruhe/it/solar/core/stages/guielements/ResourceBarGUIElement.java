package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;
import dhbw.karlsruhe.it.solar.player.resources.Resource;

/**
 * Created by Arga on 16.11.2014.
 */
public class ResourceBarGUIElement {

	public final static SolarEngine engine = SolarEngine.get();

    private Table resourceBar;


    public ResourceBarGUIElement(Label.LabelStyle labelStyle, Stage stage) {
        resourceBar = new Table();

        // TODO: replace with actual resources.
        Resource re = new Resource() {

            int n = 0;

            @Override
            public int getValue() {
                n++;
                return n/10;
            }

            @Override
            public int getMaximum() {
                return 100;
            }

            public TextureRegion getIcon() {
                return TextureCacher.gameAtlas.findRegion("resource_placeholder");
            }
        };
        resourceBar.add(new ResourceGUIElement(re, stage));
    }
}
