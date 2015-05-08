package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;

/**
 * 
 * @author Andi
 * created 2015-05-08
 */
public class ResourceBar extends Table {
    
    private ResourceBarPlayerInfo playerInfo;
    private TimeTable timeTable;
    
    public ResourceBar(GameStartStage stage) {
        playerInfo = new ResourceBarPlayerInfo(stage);
        timeTable = new TimeTable();
        add(playerInfo).left();
        add().expandX().fillX();
        add(timeTable).right();
    }
    
    public void update() {
        playerInfo.update();        
    }

}
