package dhbw.karlsruhe.it.solar.core.inputlisteners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import dhbw.karlsruhe.it.solar.core.commands.MoveCommand;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.stages.guielements.GUIActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

public class GameInputListener extends InputListener {
	
	protected GameStartStage stage;
	protected SolarEngine se = SolarEngine.get();
	
	public GameInputListener(GameStartStage gameStage) {
		stage = gameStage;
	}

	@Override
	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		// TODO Auto-generated method stub
		super.enter(event, x, y, pointer, fromActor);
	}

	@Override
	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		// TODO Auto-generated method stub
		super.exit(event, x, y, pointer, toActor);
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		return super.keyDown(event, keycode);
	}

	@Override
	public boolean keyTyped(InputEvent event, char character) {
		// TODO Auto-generated method stub
		return super.keyTyped(event, character);
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		// TODO Auto-generated method stub
		return super.keyUp(event, keycode);
	}

	@Override
	public boolean mouseMoved(InputEvent event, float x, float y) {
		// TODO Auto-generated method stub
		return super.mouseMoved(event, x, y);
	}

	@Override
	public boolean scrolled(InputEvent event, float x, float y, int amount) {
		// TODO Auto-generated method stub
		return super.scrolled(event, x, y, amount);
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if (event.getTarget() instanceof GUIActor){
			return false;
		}
		switch(button) {
		case Input.Buttons.LEFT:
			stage.selectionRectangle.setStart(x,y);
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
			stage.selectionRectangle.updateEnd(x, y);
		}
		super.touchDragged(event, x, y, pointer);
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		switch(button) {
		case Input.Buttons.LEFT:
			stage.selectionRectangle.hide();
			interact(event);
			break;
		case Input.Buttons.MIDDLE:
			break;
		case Input.Buttons.RIGHT:
			new MoveCommand(stage.selectedActors.getSpaceships(), x, y).execute();
			break;
		default:
			super.touchUp(event, x, y, pointer, button);
		}
	}

	/**
	 * This method handles interact inputs. As of now it will Move the camera if ALT is pressed, or otherwise update the selection
	 * @param event
	 */
	public void interact(InputEvent event) {
		if (Gdx.input.isKeyPressed(Keys.ALT_LEFT)) {
			moveCamera(event.getTarget());
		} else {
			updateSelection();
		}

	}

	/**
	 * Updates the selection according to the currently pressed modifiers
	 */
	private void updateSelection() {
		// first select the selection state (add, remove, create)
		SelectionState state;
		if (se.isShiftPressed()) {
			state = SelectionState.ADD;
		} else if (se.isControlPressed()) {
			state = SelectionState.REMOVE;
		} else {
			// Create: clear & add
			stage.selectedActors.clear();
			state = SelectionState.ADD;
		}

		// iterate every actor of the stage
		SolarActor sa;
		for (Actor a : stage.getActors()) {
			// cast so we can use insideRectangle method
			if (a instanceof SolarActor) {
				sa = (SolarActor) a;
				// insideRectangle to evaluate whether the actor is inside the selection.
				if (sa.insideRectangle(stage.selectionRectangle.getRectangle())) {
					// proceed according to state
					switch(state) {
						case ADD:
							stage.selectedActors.add(sa);
							break;
						case REMOVE:
							stage.selectedActors.remove(sa);
							break;
					}
				}
			}
		}

	}

	/**
	 * Moves the camera above the given target
	 * @param target
	 */
	private void moveCamera(Actor target) {
		if(target instanceof Group) {
			// if the user clicked into free space the target is the group containing all actors.
			// moving the camera to the groups origin will result in a movement to the center
			return;
		}
		se.camera.translate(target.getX() + target.getOriginX() - se.camera.position.x, target.getY() + target.getOriginY() - se.camera.position.y);
	}

	/**
	 * Handle continous input e.g. moving the camera
	 */
	public void handleContinousInput() {
		//if (Keyboard.isKeyDown(Keyboard.KEY_ADD) ||Keyboard.isKeyDown(13))
		if (Gdx.input.isKeyPressed(Keys.PLUS) || Gdx.input.isKeyPressed(Keys.EQUALS) || Gdx.input.isKeyPressed(Keys.E))
        {
			// using a linear zoom is necessary because the perception of the world changes with it's zoom
			// using a constant zoom would feel good while having a close look at things, but very slow when watching the whole solar system
            se.camera.zoom *= 0.98f;
        }
        if (Gdx.input.isKeyPressed(Keys.MINUS) || Gdx.input.isKeyPressed(Keys.Q))
        {
        	se.camera.zoom *= 1.02f;
        }
        if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W))
        {
			// same goes for camera translation, it has to be a function of the current zoom, since we don't want to wait for
			// hours to move the camera a few pixels while looking at the whole solar system
        	se.camera.translate(0, 5 * se.camera.zoom, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S))
        {
        	se.camera.translate(0, -5 * se.camera.zoom, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A))
        {
        	se.camera.translate(-5 * se.camera.zoom, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D))
        {
        	se.camera.translate(5 * se.camera.zoom, 0, 0);
        }
	}

	private enum SelectionState {
		ADD,
		REMOVE
	}

}
