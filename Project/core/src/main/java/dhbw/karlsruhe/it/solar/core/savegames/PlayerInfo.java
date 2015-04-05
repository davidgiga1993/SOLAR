package dhbw.karlsruhe.it.solar.core.savegames;

import javax.xml.bind.annotation.XmlElement;

import com.badlogic.gdx.graphics.Color;

import dhbw.karlsruhe.it.solar.player.Player;

public class PlayerInfo {
    
    @XmlElement(name = "Player Name")
    private String name;
    @XmlElement(name = "Player Color")
    private Color color;

    public PlayerInfo(Player player) {
        this.name = player.getName();
        this.color = player.getPlayerColor();
    }

}
