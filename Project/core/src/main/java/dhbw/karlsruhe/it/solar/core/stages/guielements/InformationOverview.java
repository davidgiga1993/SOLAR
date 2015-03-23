package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 24.02.2015.
 */
public class InformationOverview extends Table {

    public static final float ICON_SIZE = 64;
    public static final float TEXT_WIDTH = 128;

    SolarActor actor;
    Label name;
    Image icon = new Image();

    public InformationOverview(SolarActor actor) {
        super();

        defaults().padLeft(5).padRight(5);

        this.actor = actor;
        icon.setScaling(Scaling.fit);
        icon.setAlign(Align.center);

        if(actor != null) {
            loadContent();
        } else {
            insertPlaceholder();
        }
    }

    private void insertPlaceholder() {
        add().width(ICON_SIZE + TEXT_WIDTH);
    }

    private void loadContent() {
        loadIcon(actor.getSolarActorTexture());
        name = new Label(actor.getName(), Styles.DEFAULTLABEL_STYLE);
        add(name).width(TEXT_WIDTH);
    }

    private void loadIcon(TextureRegion textureRegion) {
        // if there's no texture, insert an empty cell.
        if (textureRegion == null) {
            add().width(ICON_SIZE).height(ICON_SIZE);
            return;
        }
        TextureRegionDrawable drawable = new TextureRegionDrawable(textureRegion);
        icon.setDrawable(drawable);
        add(icon).width(ICON_SIZE).height(ICON_SIZE);
    }

    public void changeActor(SolarActor actor) {
        clear();
        this.actor = actor;
        if(actor != null) {
            loadContent();
        } else {
            insertPlaceholder();
        }
    }

}
