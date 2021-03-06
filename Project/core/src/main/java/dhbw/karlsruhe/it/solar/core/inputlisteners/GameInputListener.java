package dhbw.karlsruhe.it.solar.core.inputlisteners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.commands.MoveCommand;
import dhbw.karlsruhe.it.solar.core.commands.MoveToAstronomicalBodyCommand;
import dhbw.karlsruhe.it.solar.core.commands.MoveToKineticObjectCommand;
import dhbw.karlsruhe.it.solar.core.savegames.SaveGameManager;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.stages.guielements.GUIActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.player.Ownable;

public class GameInputListener extends InputListener {

    private static final int CAMERA_TRANSLATION_FACTOR = 5 * 60; // 60fps
    private GameStartStage stage;
    private SolarEngine se = SolarEngine.get();

    public GameInputListener(GameStartStage gameStage) {
        stage = gameStage;
    }

    @Override
    public boolean keyTyped(InputEvent event, char character) {
        // TODO Auto-generated method stub
        switch (character) {
            case 'p':
                GameStartStage.togglePause();
                break;
            case 'k':
                GameStartStage.changeTimeSpeed(-0.1f);
                break;
            case 'l':
                GameStartStage.changeTimeSpeed(0.1f);
                break;
            default:
                break;
        }

        return super.keyTyped(event, character);
    }

    @Override
    public boolean keyUp(InputEvent event, int keyCode) {
        // TODO Auto-generated method stub
        if (keyCode == Keys.ESCAPE) {
            Gdx.app.postRunnable(() -> {
                GameStartStage.stopTime();
                SaveGameManager save = new SaveGameManager(stage);
                save.saveCurrentGame();
                GameStartStage.endGame();
            });
            return true;
        }
        return super.keyUp(event, keyCode);
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, int amount) {
        float amountF = (float) amount / 10;
        modifyZoom(1 + amountF);
        return super.scrolled(event, x, y, amount);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (event.getTarget() instanceof GUIActor) {
            return false;
        }
        switch (button) {
            case Input.Buttons.LEFT:
                stage.startOfSelectionRectangle(x, y);
                break;
            case Input.Buttons.MIDDLE:
                break;
            case Input.Buttons.RIGHT:
                break;
            default:
                return super.touchDown(event, x, y, pointer, button);
        }
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            stage.updateEndOfSelectionRectangle(x, y);
        }
        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        switch (button) {
            case Input.Buttons.LEFT:
                stage.hideSelectionRectangle();
                interact(event, x, y);
                break;
            case Input.Buttons.MIDDLE:
                break;
            case Input.Buttons.RIGHT:
                navigate(event, x, y);
                break;
            default:
                super.touchUp(event, x, y, pointer, button);
        }
    }

    public void navigate(InputEvent event, float x, float y) {
        Actor target = event.getTarget();
        if (target instanceof AstronomicalBody) {
            new MoveToAstronomicalBodyCommand(stage.getSelectedSpaceUnits(), (AstronomicalBody) target, stage.getPlayerOnThisPlatform()).execute();
            return;
        }
        if (target instanceof KinematicObject) {
            new MoveToKineticObjectCommand(stage.getSelectedSpaceUnits(), (KinematicObject) target, stage.getPlayerOnThisPlatform()).execute();
            return;
        }
        new MoveCommand(stage.getSelectedSpaceUnits(), x, y, stage.getPlayerOnThisPlatform()).execute();
    }

    /**
     * This method handles interact inputs. As of now it will Move the camera if ALT is pressed, or otherwise update the selection
     *
     * @param event
     */
    public void interact(InputEvent event, float x, float y) {
        if (Gdx.input.isKeyPressed(Keys.ALT_LEFT) | Gdx.input.isKeyPressed(Keys.ALT_RIGHT)) {
            moveCamera(event.getTarget(), x, y, Gdx.input.isKeyPressed(Keys.CONTROL_LEFT));
        } else {
            updateSelection(event);
        }

    }

    /**
     * Updates the selection according to the currently pressed modifiers
     */
    private void updateSelection(InputEvent event) {
        // first select the selection state (add, remove, create)
        // and handle the event's target actor
        SelectionState state;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
            state = SelectionState.ADD;
            stage.addToSelectedActors(event.getTarget());
        } else if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
            state = SelectionState.REMOVE;
            stage.removeFromSelectedActors(event.getTarget());
        } else {
            // Create: clear & add
            stage.clearSelectedActors();
            state = SelectionState.ADD;
            stage.addToSelectedActors(event.getTarget());
        }

        // iterate every actor of the stage
        SolarActor sa;
        for (Actor a : stage.getActors()) {
            // cast so we can use insideRectangle method
            if (a instanceof SolarActor) {
                sa = (SolarActor) a;
                // insideRectangle to evaluate whether the actor is inside the selection.
                if (sa.insideRectangle(stage.getSelectionRectangle())) {
                    updateSelectionRectangleForUnits(state, sa);
                    updateSelectionRectangleForColonies(state, sa);
                }
            }
        }

    }

    private void updateSelectionRectangleForUnits(SelectionState state,
                                                  SolarActor sa) {
        //Actor will only be added to selection if owned by the player
        if (sa instanceof Ownable && ((Ownable) sa).isOwnedBy(stage.getPlayerOnThisPlatform())) {
            // proceed according to state
            updateSelection(state, sa);
        }
    }

    private void updateSelectionRectangleForColonies(SelectionState state, SolarActor sa) {
        if (sa instanceof AstronomicalBody && ((AstronomicalBody) sa).isColonized() && ((AstronomicalBody) sa).isColonyOwnedBy(stage.getPlayerOnThisPlatform())) {
            // proceed according to state
            updateSelection(state, sa);
        }
    }

    private void updateSelection(SelectionState state, SolarActor sa) {
        switch (state) {
            case ADD:
                stage.addToSelectedActors(sa);
                break;
            case REMOVE:
                stage.removeFromSelectedActors(sa);
                break;
            default:
                break;
        }
    }

    /**
     * Moves the camera above the given target
     *
     * @param target
     */
    private void moveCamera(Actor target, float x, float y, boolean shouldModifyZoom) {
        if (target instanceof Group) {
            se.moveSolarCamera(x, y);
            return;
        }
        se.moveSolarCamera(target);
        if (shouldModifyZoom) {
            se.zoomSolarCameraTo(target.getWidth() / 25);
        }
    }

    /**
     * Handle continuous input e.g. moving the camera
     *
     * @param delta
     */
    public void handleContinuousInput(float delta) {
        // delta / (1/60) = delta * 60
        float cameraModifier = 0.02f * (delta * 60);
        handleZoomIn(cameraModifier);
        handleZoomOut(cameraModifier);
        float x = 0;
        float y = 0;
        y = handleKeyUp(y);
        y = handleKeyDown(y);
        x = handleKeyLeft(x);
        x = handleKeyRight(x);
        x *= delta;
        y *= delta;
        se.translateSolarCamera(x * se.getSolarCameraZoom(), y * se.getSolarCameraZoom());
    }

    private float handleKeyRight(float x) {
        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
            x += CAMERA_TRANSLATION_FACTOR;
        }
        return x;
    }

    private float handleKeyLeft(float x) {
        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
            x -= CAMERA_TRANSLATION_FACTOR;
        }
        return x;
    }

    private float handleKeyDown(float y) {
        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
            y -= CAMERA_TRANSLATION_FACTOR;
        }
        return y;
    }

    private float handleKeyUp(float y) {
        if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
            // same goes for camera translation, it has to be a function of the current zoom, since we don't want to wait for
            // hours to move the camera a few pixels while looking at the whole solar system
            y += CAMERA_TRANSLATION_FACTOR;
        }
        return y;
    }

    private void handleZoomOut(float cameraModifier) {
        if (Gdx.input.isKeyPressed(Keys.MINUS) || Gdx.input.isKeyPressed(Keys.Q)) {
            modifyZoom(1 + cameraModifier);
        }
    }

    private void handleZoomIn(float cameraModifier) {
        if (Gdx.input.isKeyPressed(Keys.PLUS) || Gdx.input.isKeyPressed(Keys.EQUALS) || Gdx.input.isKeyPressed(Keys.E)) {
            modifyZoom(1 - cameraModifier);
        }
    }

    private void modifyZoom(float cameraModifier) {
        // using a linear zoom is necessary because the perception of the world changes with it's zoom
        // using a constant zoom would feel good while having a close look at things, but very slow when watching the whole solar system
        se.setZoomSolarCameraTo(se.getSolarCameraZoom() * cameraModifier);
    }

    public void moveCamera(SolarActor target) {
        moveCamera(target, 0, 0, true);
    }

    private enum SelectionState {
        ADD,
        REMOVE
    }

}
