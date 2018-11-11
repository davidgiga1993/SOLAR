package dhbw.karlsruhe.it.solar.core.savegames;

import com.badlogic.gdx.graphics.Color;
import dhbw.karlsruhe.it.solar.core.resources.GlobalResource;
import dhbw.karlsruhe.it.solar.core.resources.GlobalResourceInterface;
import dhbw.karlsruhe.it.solar.core.player.Player;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

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

    public void fillInInfo(Player player) {
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
        return new ArrayList<>(resources);
    }

}
