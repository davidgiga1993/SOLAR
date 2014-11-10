package dhbw.karlsruhe.it.solar.core.inputlisteners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;

/**
 * @author Soeren
 * This class represents the player's selection of SolarActors.
 * 
 */
public class Selection {

	protected List<SolarActor> selectedActors;
	protected List<Spaceship> cachedSpaceships;
	protected boolean spaceshipDirtyFlag = true;
	
	public Selection() {
		selectedActors = new ArrayList<SolarActor>();
		cachedSpaceships = new ArrayList<Spaceship>();
	}
	
	/**
	 * @see java.util.List.contains()
	 */
	public boolean contains(Object actor) {
		return selectedActors.contains(actor);
	}
	
	/**
	 * Empties the selection and marks previous selected SolarActors as unselected
	 */
	public void clear() {
		setUnselected(selectedActors);
		selectedActors.clear();
		spaceshipDirtyFlag = true;
	}
	
	/**
	 * Clears the current selection and selects the given actors
	 * @param actors
	 * @see clear()
	 */
	public void create(List<SolarActor> actors) {
		clear();
		selectedActors = actors;
		setSelected(actors);
	}
	
	/**
	 * Adds the given actor to the selection and marks it as selected
	 * @param actor
	 */
	public void add(SolarActor actor) {
		selectedActors.add(actor);
		actor.select();
		spaceshipDirtyFlag = true;
	}
	
	/**
	 * Convenience method
	 * @see add(SolarActor)
	 */
	public void add(Actor actor) {
		if (actor instanceof SolarActor)
			add((SolarActor) actor);
	}
	
	/**
	 * @see add(SolarActor)
	 */
	public void add(Collection<SolarActor> actors) {
		for(SolarActor actor : actors) {
			if(!selectedActors.contains(actor)) {
				selectedActors.add(actor);
				actor.select();
			}
		}
		spaceshipDirtyFlag = true;
	}

	private void setSelected(Collection<SolarActor> actors) {
		for(SolarActor a : actors) {
			a.select();
		}
	}
	
	private void setUnselected(Collection<SolarActor> actors) {
		for (SolarActor a : actors) {
			a.deselect();
		}
	}
	
	public List<Spaceship> getSpaceships() {
		if (spaceshipDirtyFlag) {
			cacheSpaceships();
		}
		return cachedSpaceships;
	}
	
	public List<SolarActor> getActors() {
		return selectedActors;
	}
	
	/**
	 * Rebuilds the cachedSpaceships list by cleaning and adding all Spaceships contained in
	 * selected Actors.
	 */
	private void cacheSpaceships() {
		cachedSpaceships.clear();
		for(Actor actor : selectedActors) {
			if (actor instanceof Spaceship) {
				cachedSpaceships.add((Spaceship) actor);
			}
		}
		spaceshipDirtyFlag = false;
	}
}
