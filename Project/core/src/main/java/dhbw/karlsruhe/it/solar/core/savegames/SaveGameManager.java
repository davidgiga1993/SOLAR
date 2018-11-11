package dhbw.karlsruhe.it.solar.core.savegames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import dhbw.karlsruhe.it.solar.config.DynamicOptions;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Handles the saving and loading process of the game which creates XML save files out of the game state and back.
 *
 * @author Andi
 * created 2015-04-06
 */
public class SaveGameManager {

    private GameStartStage stage;

    public SaveGameManager(GameStartStage stage) {
        this.stage = stage;
    }

    public void loadCurrentGame() {
        String path = DynamicOptions.getValue(DynamicOptions.SAVEGAME_FILE_CURRENT);
        loadFile(path);
    }

    public void loadNewGame() {
        String path = DynamicOptions.getValue(DynamicOptions.SAVEGAME_FILE_NEWGAME);
        loadFile(path);
    }

    private void loadFile(String saveGame) {
        File file = openFile(saveGame);
        loadGameFromXML(file);
    }

    private void saveFile(String saveGame) {
        File file = openFile(saveGame);
        saveToXML(file);
    }

    private File openFile(String saveGame) {
        FileHandle classpath = Gdx.files.local(saveGame);
        return classpath.file();
    }

    private void loadGameFromXML(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SaveGame.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SaveGame save = (SaveGame) jaxbUnmarshaller.unmarshal(file);
            handOverToGameStartStage(save);
        } catch (JAXBException e) {
            Gdx.app.error("Loading", "Loading game from XML failed", e);
        }
    }

    public void saveCurrentGame() {
        String path = DynamicOptions.getValue(DynamicOptions.SAVEGAME_FILE_CURRENT);
        saveFile(path);
    }

    private void saveToXML(File file) {
        SaveGame save = new SaveGame();
        save.assignInformation(stage);

        try {
            JAXBContext jc = JAXBContext.newInstance(SaveGame.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(save, file);
        } catch (JAXBException e) {
            Gdx.app.error("Saving", "Saving game to XML failed", e);
        }
    }

    private void handOverToGameStartStage(SaveGame save) {
        stage.initSettings(save.getSettings());
        stage.initGameTime(save.getGameTimeElapsed());
        stage.initPlayers(save.getPlayers());
        stage.initAstroBodies(save.getAstroBodies());
        stage.initUnits(save.getSpaceUnits());
        stage.assignMission(save.getMissions());
    }

    public boolean isThereACurrentSaveGame() {
        String path = DynamicOptions.getValue(DynamicOptions.SAVEGAME_FILE_CURRENT);
        File file = openFile(path);
        return (file.exists() && !file.isDirectory());
    }
}
