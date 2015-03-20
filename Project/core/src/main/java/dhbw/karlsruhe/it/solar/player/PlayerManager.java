package dhbw.karlsruhe.it.solar.player;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Arga on 29.11.2014.
 */
public class PlayerManager {
    protected List<Player> players;
    // making this static will ensure the id's are unique.
    private static int count = 0;

    public PlayerManager() {
        players = new ArrayList<Player>();
    }

    public Player createPlayer(String name, Color color) {
        Player newPlayer = new Player(count, name, color);
        count++;
        players.add(newPlayer);
        return newPlayer;
    }
}
