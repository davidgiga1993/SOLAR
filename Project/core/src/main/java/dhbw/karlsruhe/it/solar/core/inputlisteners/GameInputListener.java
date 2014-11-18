package dhbw.karlsruhe.it.solar.core.inputlisteners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

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
		stage.selectionRectangle.updateEnd(x, y);
		super.touchDragged(event, x, y, pointer);
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		switch(button) {
		case Input.Buttons.LEFT:
			stage.selectionRectangle.hide();
			updateSelection();
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
	 * Handle continous input e.g. moving the camera
	 */
	public void handleContinousInput() {
    	if(Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
    		System.out.println("pressed");
    		Gdx.input.isKeyPressed(Keys.PLUS);
    	}
        if (Gdx.input.isKeyPressed(Keys.PLUS) || Gdx.input.isKeyPressed(Keys.STAR))
        {
            se.camera.zoom -= 0.10f;
        }
        if (Gdx.input.isKeyPressed(Keys.MINUS))
        {
        	se.camera.zoom += 0.10f;
        }
        if (Gdx.input.isKeyPressed(Keys.UP))
        {
        	se.camera.translate(0, 10, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN))
        {
        	se.camera.translate(0, -10, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT))
        {
        	se.camera.translate(-10, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
        {
        	se.camera.translate(10, 0, 0);
        }
	}

	private enum SelectionState {
		ADD,
		REMOVE
	}

}
