package dhbw.karlsruhe.it.solar.player;

import java.util.ArrayList;
import java.util.List;

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

    public Player createPlayer(String name) {
        Player newPlayer = new Player(count);
        count++;
        players.add(newPlayer);
        return newPlayer;
    }
}
