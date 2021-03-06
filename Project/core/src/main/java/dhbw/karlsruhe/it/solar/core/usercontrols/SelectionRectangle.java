package dhbw.karlsruhe.it.solar.core.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import dhbw.karlsruhe.it.solar.core.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Length;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarShapeRenderer;
import dhbw.karlsruhe.it.solar.core.utils.LengthConverter;

public class SelectionRectangle extends Actor implements ShapeRenderable {

    private boolean visible;
    private Vector2 startPosition;
    private Vector2 mousePosition;
    private boolean isInitialized = false;

    private Label widthLabel = new Label("", Styles.DEFAULT_LABEL_STYLE);
    private Label heightLabel = new Label("", Styles.DEFAULT_LABEL_STYLE);

    public SelectionRectangle() {
        this.visible = false;
        this.setName("SelectionRectangle");
        this.setSize(0, 0);
        widthLabel.setAlignment(Align.center);
        heightLabel.setAlignment(Align.center);
        widthLabel.setColor(0f, 1f, 0f, 1f);
        heightLabel.setColor(0f, 1f, 0f, 1f);
        hideLabels();
    }


    @Override
    public void drawLines(ShapeRenderer libGDXShapeRenderer, SolarShapeRenderer solarShapeRenderer) {
        if (visible) {
            libGDXShapeRenderer.setColor(Color.GREEN);
            libGDXShapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        }
    }

    public void updateEnd(float mouseX, float mouseY) {
        // Recalculates position, width and height based on original starting position and current cursor position
        if (startPosition.x > mouseX) {
            setX(mouseX);
            setWidth(startPosition.x - mouseX);
        } else {
            setX(startPosition.x);
            setWidth(mouseX - startPosition.x);
        }

        if (startPosition.y > mouseY) {
            setY(mouseY);
            setHeight(startPosition.y - mouseY);
        } else {
            setY(startPosition.y);
            setHeight(mouseY - startPosition.y);
        }

        mousePosition = new Vector2(mouseX, mouseY);
        updateLabels();
    }


    public void setStart(float x, float y) {
        if (!isInitialized) {
            Stage stage = SolarEngine.get().getStage("GameHUD");
            stage.addActor(widthLabel);
            stage.addActor(heightLabel);
            isInitialized = true;
        }
        setPosition(x, y);
        setStartPosition(new Vector2(x, y));
        visible = true;
        setWidth(0);
        setHeight(0);
        System.out.println(x + "\t|\t" + y);
    }

    public void hide() {
        visible = false;
        hideLabels();
    }

    public Vector2 getStartPosition() {
        return startPosition;
    }

    private void setStartPosition(Vector2 position) {
        startPosition = position;
    }

    public Vector2 getEndPosition() {
        return mousePosition;
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    private void hideLabels() {
        this.heightLabel.setVisible(false);
        this.widthLabel.setVisible(false);
    }

    private void showLabels() {
        this.heightLabel.setVisible(true);
        this.widthLabel.setVisible(true);
    }

    private void updateLabels() {
        widthLabel.setText(calculateDistance(getWidth()));
        heightLabel.setText(calculateDistance(getHeight()));

        updateLabelPosition(getX(), getY());

        showLabels();
    }

    private String calculateDistance(float length) {
        Length physicalLength = LengthConverter.toPhysical(length, ConfigurationConstants.SCALE_FACTOR_PLANET);
        return physicalLength.toString();
    }

    private void updateLabelPosition(float x, float y) {
        Vector3 newPosition = new Vector3(x, y, 0);
        Vector3 endpoint = new Vector3(x + getWidth(), y + getHeight(), 0);

        getStage().getCamera().project(newPosition);
        getStage().getCamera().project(endpoint);

        widthLabel.setPosition(newPosition.x, newPosition.y - 20);
        heightLabel.setPosition(newPosition.x - 40, newPosition.y);

        widthLabel.setWidth(endpoint.x - widthLabel.getX());
        heightLabel.setHeight(endpoint.y - heightLabel.getY());
    }

}
