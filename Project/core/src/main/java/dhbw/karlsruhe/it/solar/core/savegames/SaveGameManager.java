package dhbw.karlsruhe.it.solar.core.savegames;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlSeeAlso;

import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;

public class SaveGameManager {
    
    private static final String path = "C:\\Users\\Andi\\Desktop\\Studienarbeit\\SaveGames\\SolarSystem.xml";
    private GameStartStage stage;
    
    public SaveGameManager(GameStartStage stage) {
        this.stage = stage;
    }

    public void loadGameFromXML() {
        File file = new File(path);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SaveGame.class);           
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SaveGame save = (SaveGame) jaxbUnmarshaller.unmarshal(file);
            handOverToGameStartStage(save);
        } catch (JAXBException e) {
            System.out.println(e);
        }       
    }

    public void saveToXML() {
        SaveGame save = new SaveGame();
        save.assignInformation(stage);
        
        File file = new File(path);
        try {
            JAXBContext jc = JAXBContext.newInstance( SaveGame.class );
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal( save, file );
        } catch (JAXBException e) {
            System.out.println(e);
        }
    }
    
    private void handOverToGameStartStage(SaveGame save) {
        // TODO Auto-generated method stub
        
    }
}
