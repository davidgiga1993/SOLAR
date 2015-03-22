package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.player.resources.Resource;

/**
 * Created by Arga on 29.11.2014.
 */
public class ResourceGUIElement extends HorizontalGroup implements GUIActor {

    protected Resource resource;

    protected GUILabel label;
    protected GUIImage sprite;

    public ResourceGUIElement(Resource resource, Stage stage) {
        super();
        this.resource = resource;

        label = new GUILabel(buildLabelString(), SolarEngine.get().getDefaultLabelStyle(), stage);
        label.tooltip.setTitle("Resource");
        sprite = new GUIImage(resource.getIcon());

        Container spriteContainer = new Container<GUIImage>(sprite);
        spriteContainer.padRight(5);
        spriteContainer.size(22, 22);

        this.addActor(spriteContainer);
        this.addActor(label);

        this.pad(5, 10, 5, 10);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        label.setText(buildLabelString());
    }

    protected CharSequence buildLabelString() {
        return resource.getValue() + " / " + resource.getMaximum();
    }
}
