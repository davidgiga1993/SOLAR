package dhbw.karlsruhe.it.solar.core.inputlisteners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import dhbw.karlsruhe.it.solar.core.ai.KinematicObject;
import dhbw.karlsruhe.it.solar.core.commands.MoveCommand;
import dhbw.karlsruhe.it.solar.core.commands.MoveToAstronomicalBodyCommand;
import dhbw.karlsruhe.it.solar.core.commands.MoveToKineticObjectCommand;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.stages.guielements.GUIActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.player.Ownable;

public class GameInputListener extends InputListener {

	public static final int CAMERA_TRANSLATION_FACTOR = 5 * 60; // 60fps
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
        if(character == 'p') {
            GameStartStage.gameSpeed = GameStartStage.gameSpeed == 1 ? 0 : 1;
        }
		return super.keyTyped(event, character);
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		// TODO Auto-generated method stub
        if (keycode == Keys.ESCAPE) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    GameStartStage.endGame();
                }
            });
            return true;
        }
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

    private void navigate(InputEvent event, float x, float y) {
        Actor target = event.getTarget();
        if(target instanceof AstronomicalBody) {
            new MoveToAstronomicalBodyCommand(stage.selectedActors.getSpaceships(), (AstronomicalBody) target, stage.getHumanPlayer()).execute();
            return;
        }
        if(target instanceof KinematicObject) {
            new MoveToKineticObjectCommand(stage.selectedActors.getSpaceships(), (KinematicObject) target, stage.getHumanPlayer()).execute();
            return;
        }
        new MoveCommand(stage.selectedActors.getSpaceships(), x, y, stage.getHumanPlayer()).execute();
    }

    /**
	 * This method handles interact inputs. As of now it will Move the camera if ALT is pressed, or otherwise update the selection
	 * @param event
	 */
	public void interact(InputEvent event, float x, float y) {
		if (Gdx.input.isKeyPressed(Keys.ALT_LEFT) | Gdx.input.isKeyPressed(Keys.ALT_RIGHT)) {
			moveCamera(event.getTarget(), x, y);
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
			stage.selectedActors.add(event.getTarget());
		} else if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			state = SelectionState.REMOVE;
			stage.selectedActors.remove(event.getTarget());
		} else {
			// Create: clear & add
			stage.selectedActors.clear();
			state = SelectionState.ADD;
			stage.selectedActors.add(event.getTarget());
		}

		// iterate every actor of the stage
		SolarActor sa;
		for (Actor a : stage.getActors()) {
			// cast so we can use insideRectangle method
			if (a instanceof SolarActor) {
				sa = (SolarActor) a;
				// insideRectangle to evaluate whether the actor is inside the selection.
				if (sa instanceof Ownable && !((Ownable) sa).isOwnedBy(stage.getHumanPlayer())) {
					break;
				}
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
	private void moveCamera(Actor target, float x, float y) {
		if(target instanceof Group) {
			se.camera.translate(x - se.camera.position.x, y - se.camera.position.y);
			return;
		}
		se.camera.translate(target.getX() + target.getOriginX() - se.camera.position.x, target.getY() + target.getOriginY() - se.camera.position.y);
        se.camera.zoom = target.getWidth() / 25;
	}

	/**
	 * Handle continous input e.g. moving the camera
	 * @param delta
	 */
	public void handleContinousInput(float delta) {
		// delta / (1/60) = delta * 60
        float cameraModifier = 0.02f * (delta * 60);
		if (Gdx.input.isKeyPressed(Keys.PLUS) || Gdx.input.isKeyPressed(Keys.EQUALS) || Gdx.input.isKeyPressed(Keys.E))
        {
			// using a linear zoom is necessary because the perception of the world changes with it's zoom
			// using a constant zoom would feel good while having a close look at things, but very slow when watching the whole solar system
            se.camera.zoom *= 1 - cameraModifier;
        }
        if (Gdx.input.isKeyPressed(Keys.MINUS) || Gdx.input.isKeyPressed(Keys.Q))
        {
        	se.camera.zoom *= 1 + cameraModifier;
        }
		float x = 0;
		float y = 0;
        if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W))
        {
			// same goes for camera translation, it has to be a function of the current zoom, since we don't want to wait for
			// hours to move the camera a few pixels while looking at the whole solar system
			y += CAMERA_TRANSLATION_FACTOR;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S))
        {
			y -= CAMERA_TRANSLATION_FACTOR;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A))
        {
			x -= CAMERA_TRANSLATION_FACTOR;
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D))
        {
			x += CAMERA_TRANSLATION_FACTOR;
        }
		x *= delta;
		y *= delta;
		se.camera.translate(x * se.camera.zoom, y * se.camera.zoom, 0);
	}

	private enum SelectionState {
		ADD,
		REMOVE
	}

}
