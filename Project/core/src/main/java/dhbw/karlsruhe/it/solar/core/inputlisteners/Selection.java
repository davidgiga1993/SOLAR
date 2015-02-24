package dhbw.karlsruhe.it.solar.core.inputlisteners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;

import java.util.*;

/**
 * @author Soeren
 * This class represents the player's selection of SolarActors.
 * 
 */
public class Selection {

	protected Set<SolarActor> selectedActors;
	protected List<Spaceship> cachedSpaceships;
	protected boolean spaceshipDirtyFlag = true;
	
	public Selection() {
		selectedActors = new HashSet<SolarActor>();
		cachedSpaceships = new ArrayList<Spaceship>();
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
		selectedActors.addAll(actors);
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
	 * @see dhbw.karlsruhe.it.solar.core.inputlisteners.Selection.add(dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor add)
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

	/**
	 * Removes the given Object from the selection iff it is present.
	 * @param actor
	 */
	public void remove(SolarActor actor) {
		if (selectedActors.remove(actor)) {
			actor.deselect();
			spaceshipDirtyFlag = true;
		}
	}

	public void remove(Actor actor) {
		if (actor instanceof SolarActor) {
			remove((SolarActor) actor);
		}
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
	
	public Collection<SolarActor> getActors() {
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

	public SolarActor getRepresentative() {
		Iterator<SolarActor> iterator = selectedActors.iterator();
		if(iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}

	public int getNumberOfSelectedUnits() {
		return selectedActors.size();
	}

}
