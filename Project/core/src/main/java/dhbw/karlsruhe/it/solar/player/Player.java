package dhbw.karlsruhe.it.solar.player;

import dhbw.karlsruhe.it.solar.player.resources.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 29.11.2014.
 */
public class Player {

    protected List<Resource> resources;
    private final int id;

    Player(int id) {
        this.id = id;
        resources = new ArrayList<Resource>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            return ((Player) obj).id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
