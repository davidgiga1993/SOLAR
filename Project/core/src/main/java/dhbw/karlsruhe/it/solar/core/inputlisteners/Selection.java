package dhbw.karlsruhe.it.solar.core.inputlisteners;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Actor;

import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.solar.SolarMessageType;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

import java.util.*;

/**
 * @author Soeren
 * This class represents the player's selection of SolarActors.
 * 
 */
public class Selection implements Telegraph {

	protected Set<SolarActor> selectedActors;
	protected List<SpaceUnit> cachedSpaceUnits;
	protected boolean spaceUnitDirtyFlag = true;
	
	public Selection() {
		selectedActors = new HashSet<SolarActor>();
		cachedSpaceUnits = new ArrayList<SpaceUnit>();
	}

	/**
	 * Empties the selection and marks previous selected SolarActors as unselected
	 */
	public void clear() {
		setUnselected(selectedActors);
		selectedActors.clear();
		spaceUnitDirtyFlag = true;
		selectionChanged();
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
		spaceUnitDirtyFlag = true;
		selectionChanged();
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
		spaceUnitDirtyFlag = true;
		selectionChanged();
	}

	/**
	 * Removes the given Object from the selection iff it is present.
	 * @param actor
	 */
	public void remove(SolarActor actor) {
		if (selectedActors.remove(actor)) {
			actor.deselect();
			spaceUnitDirtyFlag = true;
		}
		selectionChanged();
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
	
	public List<SpaceUnit> getSpaceUnits() {
		if (spaceUnitDirtyFlag) {
			cacheSpaceUnits();
		}
		return cachedSpaceUnits;
	}
	
	public Collection<SolarActor> getActors() {
		return selectedActors;
	}
	
	/**
	 * Rebuilds the cachedSpaceships list by cleaning and adding all Spaceships contained in
	 * selected Actors.
	 */
	private void cacheSpaceUnits() {
		cachedSpaceUnits.clear();
		for(Actor actor : selectedActors) {
			if (actor instanceof SpaceUnit) {
				cachedSpaceUnits.add((SpaceUnit) actor);
			}
		}
		spaceUnitDirtyFlag = false;
	}

	public SolarActor getRepresentative() {
		Iterator<SolarActor> iterator = selectedActors.iterator();
		if(iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}

	private void selectionChanged() {
		SolarEngine.MESSAGE_DISPATCHER.dispatchMessage(this, SolarMessageType.PLAYER_SELECTION_CHANGED);
	}

	public int getNumberOfSelectedUnits() {
		return selectedActors.size();
	}

	@Override
	public boolean handleMessage(Telegram telegram) {
		return false;
	}
}
