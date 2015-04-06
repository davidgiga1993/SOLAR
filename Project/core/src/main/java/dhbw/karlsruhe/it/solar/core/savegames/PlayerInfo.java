package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlElement;

import com.badlogic.gdx.graphics.Color;

import dhbw.karlsruhe.it.solar.player.Player;

public class PlayerInfo {
    
    @XmlElement(name = "PlayerName")
    private String name;
    @XmlElement(name = "PlayerColor")
    private Color color;
    
    public PlayerInfo() {
        
    }

    public void fillInInfos(Player player) {
        this.name = player.getName();
        this.color = player.getPlayerColor();
    }

    public String getPlayerName() {
        return name;
    }

    public Color getPlayerColor() {
        return color;
    }

}
