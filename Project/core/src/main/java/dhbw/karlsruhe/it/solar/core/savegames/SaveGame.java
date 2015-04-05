package dhbw.karlsruhe.it.solar.core.savegames;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.SpaceUnit;

/**
 * Tries to save current game state to a XML file.
 * @author Andi
 * created: 2015-04-05
 */
@XmlRootElement( name = "SaveGame")
@XmlSeeAlso({ AstroBodyInfo.class, SpaceUnitInfo.class})
public class SaveGame {
    private static final String path = "C:\\Users\\Andi\\Desktop\\Studienarbeit\\SaveGames\\SolarSystem.xml";
    
    private Array<Actor> saveGameActors;
    private List<SpaceUnit> spaceUnits;
    private List<AstronomicalBody> spaceObjects;
    @XmlElement(name ="Astronomical Body")
    private List<AstroBodyInfo> bodies;
    @XmlElement(name ="Space Unit")
    private List<SpaceUnitInfo> units;
    
    public SaveGame() {
    }
    
    public void assignInformation(GameStartStage stage) {
        this.saveGameActors = stage.getActors();
        
        spaceObjects = filterOutAstroBodies();
        bodies = createAstroBodyInfo();
        spaceUnits = filterOutUnits();
        units = createSpaceUnitInfo();
    }
    
    public void saveToXML() {
        File file = new File(path);
        try {
            JAXBContext jc = JAXBContext.newInstance( SaveGame.class );
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal( this, file );
        } catch (JAXBException e) {
            System.out.println(e);
        }
    }

    private List<AstronomicalBody> filterOutAstroBodies() {
        List<AstronomicalBody> newList = new ArrayList<AstronomicalBody>();
        for (Actor actor : saveGameActors) {
            if(actor instanceof AstronomicalBody) {
                newList.add((AstronomicalBody)actor);
            }
        }
        return newList;
    }
    
    private List<AstroBodyInfo> createAstroBodyInfo() {
        List<AstroBodyInfo> newList = new ArrayList<AstroBodyInfo>();
        for (AstronomicalBody body : spaceObjects) {
            newList.add(new AstroBodyInfo(body));
        }
        return newList;
    }
    
    private List<SpaceUnit> filterOutUnits() {
        List<SpaceUnit> newList = new ArrayList<SpaceUnit>();
        for (Actor actor : saveGameActors) {
            if(actor instanceof SpaceUnit) {
                newList.add((SpaceUnit)actor);
            }
        }
        return newList;
    }
    
    private List<SpaceUnitInfo> createSpaceUnitInfo() {
        List<SpaceUnitInfo> newList = new ArrayList<SpaceUnitInfo>();
        for (SpaceUnit unit : spaceUnits) {
            newList.add(new SpaceUnitInfo(unit));
        }
        return newList;
    }
}
