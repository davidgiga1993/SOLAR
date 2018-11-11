package dhbw.karlsruhe.it.solar.core.stages.guielements.configelements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import dhbw.karlsruhe.it.solar.core.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.Moon;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.Planet;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.Star;
import dhbw.karlsruhe.it.solar.core.stages.guielements.Tooltip;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 12.02.2015.
 */
public class ScaleDialog {

    private Stage stage;
    private Tooltip scaleDialog;
    private List<ScaleSlider> scaleSliders;

    private ScaleDialog(Stage stage) {
        this.stage = stage;
    }

    public static void createScaleDialog(Stage stage) {
        ScaleDialog scaleDialog = new ScaleDialog(stage);
        scaleDialog.initialize();
        stage.addActor(scaleDialog.scaleDialog);
        scaleDialog.scaleDialog.setVisible(true);
    }

    private void initialize() {
        scaleDialog = new Tooltip("Scale Settings");
        scaleDialog.setWidth(340);
        scaleDialog.setHeight(330);
        scaleDialog.setMovable(true);
        scaleDialog.setResizable(true);
        Table contentTable = new Table();
        contentTable.setFillParent(true);

        Label shapeLabel = new Label("Body Size", Styles.DEFAULT_LABEL_STYLE);
        shapeLabel.setAlignment(Align.center);
        Label orbitLabel = new Label("Orbit Size", Styles.DEFAULT_LABEL_STYLE);
        orbitLabel.setAlignment(Align.center);
        Label presetLabel = new Label("Load a Preset", Styles.DEFAULT_LABEL_STYLE);
        presetLabel.setAlignment(Align.center);

        scaleSliders = new ArrayList<>();

        ScaleSlider starShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_STAR, 1, 50, Star.class);
        ScaleSlider planetShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_PLANET, 1, 100, Planet.class);
        //    ScaleSlider asteroidShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_ASTEROID, 1, 10000, Asteroid.class);
        ScaleSlider moonShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_MOON, 1, 100, Moon.class);
        ScaleSlider unitShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_UNITS, 10000, 1000000, SpaceUnit.class);

        ScaleSlider planetOrbitSlider = new OrbitScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_PLANET, 0.1f, 1, Planet.class);
        //   ScaleSlider asteroidOrbitSlider = new OrbitScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_ASTEROID, 0.1f, 200, Asteroid.class);
        ScaleSlider moonOrbitSlider = new OrbitScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_MOON, 0.1f, 10, Moon.class);

        scaleSliders.add(starShapeSlider);
        scaleSliders.add(planetShapeSlider);
        //  scaleSliders.add(asteroidShapeSlider);
        scaleSliders.add(moonShapeSlider);
        scaleSliders.add(unitShapeSlider);

        scaleSliders.add(planetOrbitSlider);
        //   scaleSliders.add(asteroidOrbitSlider);
        scaleSliders.add(moonOrbitSlider);

        ScalePresetButton preset1Button = new ScalePresetButton("Preset 1") {
            @Override
            public void onClick() {
                ScalePresetButton.loadPreset1();
                refreshSliders();
                ScalePresetButton.loadPreset1();
                refreshSliders();
            }
        };

        ScalePresetButton preset2Button = new ScalePresetButton("Preset 2") {
            @Override
            public void onClick() {
                ScalePresetButton.loadPreset2();
                refreshSliders();
                ScalePresetButton.loadPreset2();
                refreshSliders();
            }
        };

        ScalePresetButton preset3Button = new ScalePresetButton("Preset 3") {
            @Override
            public void onClick() {
                ScalePresetButton.loadPreset3();
                refreshSliders();
                ScalePresetButton.loadPreset3();
                refreshSliders();
            }
        };

        Table presetButtonTable = new Table();
        presetButtonTable.add(preset1Button);
        presetButtonTable.add(preset2Button);
        presetButtonTable.add(preset3Button);

        contentTable.add(shapeLabel).fillX().height(25).row();
        for (int i = 0; i < 4; i++) {
            contentTable.add(scaleSliders.get(i)).fillX().height(25).row();
        }

        contentTable.add(orbitLabel).fillX().height(25).row();
        for (int i = 4; i < 6; i++) {
            contentTable.add(scaleSliders.get(i)).fillX().height(25).row();
        }

        contentTable.add(presetLabel).fillX().height(25).row();
        contentTable.add(presetButtonTable).fillX().height(25).padTop(3).row();

        scaleDialog.addActor(contentTable);
        Color windowColor = scaleDialog.getColor();
        windowColor.set(windowColor.r, windowColor.g, windowColor.b, 0.8f);
        scaleDialog.setColor(windowColor);

        scaleDialog.updatePosition();
    }

    private void refreshSliders() {
        for (ScaleSlider scaleSlider : scaleSliders) {
            scaleSlider.refreshValues();
        }
    }
}
