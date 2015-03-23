package dhbw.karlsruhe.it.solar.player;

import dhbw.karlsruhe.it.solar.player.resources.Resource;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Arga on 29.11.2014.
 */
public class Player {

    protected List<Resource> resources;
    private final int id;
    private final Color playerColor;
    private final String name;

    Player(int id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.playerColor = color;
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
    
    public Color getPlayerColor() {
        return playerColor;
    }
    
    public String getName() {
        return name;
    }
}
