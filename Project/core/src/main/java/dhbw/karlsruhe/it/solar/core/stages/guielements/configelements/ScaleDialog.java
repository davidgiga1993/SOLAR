package dhbw.karlsruhe.it.solar.core.stages.guielements.configelements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.guielements.Tooltip;
import dhbw.karlsruhe.it.solar.core.usercontrols.*;

/**
 * Created by Arga on 12.02.2015.
 */
public class ScaleDialog {

    private Stage stage;
    public Tooltip scaleDialog;

    public ScaleDialog(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        scaleDialog = new Tooltip("Scale Settings");
        scaleDialog.setWidth(350);
        scaleDialog.setHeight(300);
        scaleDialog.setMovable(true);
        Table contentTable = new Table();
        contentTable.setFillParent(true);

        Label shapeLabel = new Label("Body Size", SolarEngine.get().styles.defaultLabelStyle);
        Label orbitLabel = new Label("Orbit Size", SolarEngine.get().styles.defaultLabelStyle);

        ScaleSlider starShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_STAR, 1, 100, Star.class);
        ScaleSlider planetShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_PLANET, 1, 5000, Planet.class);
        ScaleSlider asteroidShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_ASTEROID, 1, 100000, Asteroid.class);
        ScaleSlider moonShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_MOON, 1, 10000, Moon.class);
        ScaleSlider unitShapeSlider = new ShapeScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_UNITS, 40000, 40000000, Spaceship.class);

        ScaleSlider planetOrbitSlider = new OrbitScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_PLANET, 0.1f, 10, Planet.class);
        ScaleSlider asteroidOrbitSlider = new OrbitScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_ASTEROID, 0.1f, 10, Asteroid.class);
        ScaleSlider moonOrbitSlider = new OrbitScaleSlider(stage, ConfigurationConstants.SCALE_FACTOR_MOON, 0.1f, 1000, Moon.class);

        contentTable.add(shapeLabel).fillX().height(25).row();
        contentTable.add(starShapeSlider).fillX().height(25).row();
        contentTable.add(planetShapeSlider).fillX().height(25).row();
        contentTable.add(moonShapeSlider).fillX().height(25).row();
        contentTable.add(asteroidShapeSlider).fillX().height(25).row();
        contentTable.add(unitShapeSlider).fillX().height(25).row();

        contentTable.add(orbitLabel).fillX().height(25).row();
        contentTable.add(planetOrbitSlider).fillX().height(25).row();
        contentTable.add(asteroidOrbitSlider).fillX().height(25).row();
        contentTable.add(moonOrbitSlider).fillX().height(25).row();



        scaleDialog.addActor(contentTable);
        Color windowColor = scaleDialog.getColor();
        windowColor.set(windowColor.r, windowColor.g, windowColor.b, 0.8f);
        scaleDialog.setColor(windowColor);

        scaleDialog.updatePosition();
    }

    public static void createScaleDialog(Stage stage) {
        ScaleDialog scaleDialog = new ScaleDialog(stage);
        scaleDialog.initialize();
        stage.addActor(scaleDialog.scaleDialog);
        scaleDialog.scaleDialog.setVisible(true);
    }
}
