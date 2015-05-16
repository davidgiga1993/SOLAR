package dhbw.karlsruhe.it.solar.core.savegames;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.badlogic.gdx.graphics.Color;

import dhbw.karlsruhe.it.solar.core.resources.GlobalResource;
import dhbw.karlsruhe.it.solar.core.resources.GlobalResourceInterface;
import dhbw.karlsruhe.it.solar.player.Player;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({GlobalResource.class})
public class PlayerInfo {
    
    @XmlElement(name = "PlayerName")
    private String name;
    @XmlElement(name = "PlayerColor")
    private Color color;
    @XmlElement(name = "Player_Resources")
    private List<GlobalResource> resources;
    
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

    public List<GlobalResourceInterface> getResources() {
        List<GlobalResourceInterface> newList = new ArrayList<GlobalResourceInterface>();
        for(GlobalResource resource : resources) {
            newList.add((GlobalResourceInterface)resource);
        }
        return newList;
    }

}
