package dhbw.karlsruhe.it.solar.player;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

import dhbw.karlsruhe.it.solar.core.savegames.PlayerInfo;

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

    // TODO: old method, remove
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

    public void createNewPlayer(PlayerInfo player) {
        createPlayer(player.getPlayerName(), player.getPlayerColor());  
    }
    
    public void initPlayerOnThisPlatform(int number) {
        playerOnThisPlatform = playersInGame.get(number);
    }

    public Player getPlayerFromName(String nameOfOwner) {
        for (Player player : playersInGame) {
            if(player.getName().equals(nameOfOwner)) {
                return player;
            }
        }
        return null;
    }
}
