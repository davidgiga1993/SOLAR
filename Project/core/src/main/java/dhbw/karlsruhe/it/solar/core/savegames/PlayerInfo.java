package dhbw.karlsruhe.it.solar.core.savegames;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.badlogic.gdx.graphics.Color;

import dhbw.karlsruhe.it.solar.core.resources.BaseResource;
import dhbw.karlsruhe.it.solar.player.Player;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({BaseResource.class})
public class PlayerInfo {
    
    @XmlElement(name = "PlayerName")
    private String name;
    @XmlElement(name = "PlayerColor")
    private Color color;
    @XmlElement(name = "Player_Resources")
    private List<BaseResource> resources;
    
    public PlayerInfo() {
        
    }

    public void fillInInfos(Player player) {
        this.name = player.getName();
        this.color = player.getPlayerColor();
        this.resources = player.getResources();
    }

    public String getPlayerName() {
        return name;
    }

    public Color getPlayerColor() {
        return color;
    }

    public List<BaseResource> getResources() {
        return resources;
    }

}
