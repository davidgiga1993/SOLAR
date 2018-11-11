package dhbw.karlsruhe.it.solar.player;

/**
 * Created by Arga on 29.11.2014.
 */
public interface Ownable {
    /**
     * This method checks if the Ownable is owned by the given player
     *
     * @param player
     * @return
     */
    boolean isOwnedBy(Player player);

    /**
     * Returns the owner of this object.
     *
     * @return
     */
    Player getOwner();
}
