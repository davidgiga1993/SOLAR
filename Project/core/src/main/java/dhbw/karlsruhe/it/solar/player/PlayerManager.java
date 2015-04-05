package dhbw.karlsruhe.it.solar.player;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Arga on 29.11.2014.
 */
public class PlayerManager {
    // making this static will ensure the id's are unique.
    private static int count = 0;
    protected List<Player> playersInGame;
    private Player playerOnThisPlatform;

    public PlayerManager() {
        playersInGame = new ArrayList<Player>();
    }

    public Player createPlayer(String name, Color color) {
        Player newPlayer = new Player(count, name, color);
        count++;
        playersInGame.add(newPlayer);
        return newPlayer;
    }

    public void initializePlayers() {
        createPlayer("Player One", Color.GREEN);
        createPlayer("Player Two(AI)", Color.RED);
        createPlayer("Player Three(AI)", Color.YELLOW);
        playerOnThisPlatform = playersInGame.get(0);
    }

    public Player getPlayerOnThisPlatform() {
        return playerOnThisPlatform;
    }
    
    public Player getPlayerNumber(int number) {
        return playersInGame.get(number);
    }

    public boolean isThisPlayerOnThisPlatform(Player player) {
        return playerOnThisPlatform.equals(player);
    }

    public List<Player> getPlayersInGame() {
        return playersInGame;
    }
}
