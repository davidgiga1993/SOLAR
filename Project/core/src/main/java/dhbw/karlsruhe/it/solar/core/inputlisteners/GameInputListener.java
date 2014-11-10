package dhbw.karlsruhe.it.solar.core.inputlisteners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import dhbw.karlsruhe.it.solar.core.commands.MoveCommand;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;

public class GameInputListener extends InputListener {
	
	protected GameStartStage stage;
	protected SolarEngine se = (SolarEngine) Gdx.app.getApplicationListener();
	
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
		// TODO Auto-generated method stub
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
		switch(button) {
		case Input.Buttons.LEFT:
			stage.selectedActors.clear();
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
			stage.updateSelection();
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
		if (se.isShiftPressed()) {
			// TODO: Move Selection Logic to some safe spot.
		}
	}

}
