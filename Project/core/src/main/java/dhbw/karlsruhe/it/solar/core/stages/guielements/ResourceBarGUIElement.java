package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.core.resources.ResourceInterface;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.TextureCacher;

/**
 * Created by Arga on 16.11.2014.
 */
public class ResourceBarGUIElement {

    public static final SolarEngine ENGINE = SolarEngine.get();

    private Table resourceBar;


    public ResourceBarGUIElement(Label.LabelStyle labelStyle, Stage stage) {
        resourceBar = new Table();

        // TODO: replace with actual resources.
        ResourceInterface re = new ResourceInterface() {

            int n = 0;

            @Override
            public long getValue() {
                n++;
                return n/10;
            }

            @Override
            public long getMaximum() {
                return 100;
            }

            public TextureRegion getIcon() {
                return TextureCacher.GAMEATLAS.findRegion("resource_placeholder");
            }
        };
        resourceBar.add(new ResourceGUIElement(re, stage));
    }
}
