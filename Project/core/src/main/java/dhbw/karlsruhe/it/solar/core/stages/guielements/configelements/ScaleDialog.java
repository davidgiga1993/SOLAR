package dhbw.karlsruhe.it.solar.core.stages.guielements.configelements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.guielements.Tooltip;
import dhbw.karlsruhe.it.solar.core.usercontrols.Moon;
import dhbw.karlsruhe.it.solar.core.usercontrols.Planet;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;
import dhbw.karlsruhe.it.solar.core.usercontrols.Star;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 12.02.2015.
 */
public class ScaleDialog {

    private Stage stage;
    public Tooltip scaleDialog;
    public List<ScaleSlider> scaleSliders;

    public ScaleDialog(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        scaleDialog = new Tooltip("Scale Settings");
        scaleDialog.setWidth(340);
        scaleDialog.setHeight(330);
        scaleDialog.setMovable(true);
        scaleDialog.setResizable(true);
        Table contentTable = new Table();
        contentTable.setFillParent(true);

        Label shapeLabel = new Label("Body Size", SolarEngine.get().styles.defaultLabelStyle);
        shapeLabel.setAlignment(Align.center);
        Label orbitLabel = new Label("Orbit Size", SolarEngine.get().styles.defaultLabelStyle);
        orbitLabel.setAlignment(Align.center);
        Label presetLabel = new Label("Load a Preset", SolarEngine.get().styles.defaultLabelStyle);
        presetLabel.setAlignment(Align.center);

        scaleSliders = new ArrayList<ScaleSlider>();
        
        ScaleSlider starShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_STAR, 1, 100, Star.class);
        ScaleSlider planetShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_PLANET, 1, 2000, Planet.class);
    //    ScaleSlider asteroidShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_ASTEROID, 1, 10000, Asteroid.class);
        ScaleSlider moonShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_MOON, 1, 5000, Moon.class);
        ScaleSlider unitShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_UNITS, 40000, 40000000, Spaceship.class);

        ScaleSlider planetOrbitSlider = new OrbitScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_PLANET, 0.1f, 5, Planet.class);
     //   ScaleSlider asteroidOrbitSlider = new OrbitScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_ASTEROID, 0.1f, 200, Asteroid.class);
        ScaleSlider moonOrbitSlider = new OrbitScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_MOON, 0.1f, 200, Moon.class);

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
        for(int i = 0; i < 4; i++) {
            contentTable.add(scaleSliders.get(i)).fillX().height(25).row();
        }

        contentTable.add(orbitLabel).fillX().height(25).row();
        for(int i = 4; i < 6; i++) {
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

    public static void createScaleDialog(Stage stage) {
        ScaleDialog scaleDialog = new ScaleDialog(stage);
        scaleDialog.initialize();
        stage.addActor(scaleDialog.scaleDialog);
        scaleDialog.scaleDialog.setVisible(true);
    }
}
