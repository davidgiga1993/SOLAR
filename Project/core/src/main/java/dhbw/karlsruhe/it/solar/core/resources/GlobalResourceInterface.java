package dhbw.karlsruhe.it.solar.core.resources;

/**
 * Handles the outward behavior of globally stored resources which are not bound to a specific place but to a player.
 * @author Andi
 * created 2015-05-16
 */
public interface GlobalResourceInterface {
    
    /**
     * Updates the statistics of the resource if enough time has passed.
     */
    public void updateStatistic();

}
