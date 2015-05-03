package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * 
 * @author Andi
 * created 2015-05-03
 */
public class InfoBarMenueButtons extends Table {
    
    private final TextButton displayExtraData = new TextButton("Data", Styles.TOOLTIPSKIN);
    private final TextButton displayLifeRating = new TextButton("LR", Styles.TOOLTIPSKIN);
    private final TextButton displayColonyDetails = new TextButton("Colony", Styles.TOOLTIPSKIN);
    
    private final InfoBarManager infoBarManager;

    public InfoBarMenueButtons(InfoBarManager infoBarManager) {
        this.infoBarManager = infoBarManager;
        addAstroButtonListeners();
    }
    
    public InfoBarMenueButtons displayAstroBodyButtons() {
        addAstroButtons();
        return this;
    }

    public InfoBarMenueButtons displaySpaceUnitButtons() {
        add(displayExtraData).width(InformationBar.MENUE_BUTTON_WIDTH).height(InformationBar.MENUE_BUTTON_HEIGHT).pad(InformationBar.MENUE_BUTTON_PADDING);
        row();
        return this;
    }

    public InfoBarMenueButtons empty() {
        add().expand().fill();
        return this;
    }

    private void addAstroButtons() {
        add(displayExtraData).width(InformationBar.MENUE_BUTTON_WIDTH).height(InformationBar.MENUE_BUTTON_HEIGHT).pad(InformationBar.MENUE_BUTTON_PADDING);
        row();
        add(displayLifeRating).width(InformationBar.MENUE_BUTTON_WIDTH).height(InformationBar.MENUE_BUTTON_HEIGHT).pad(InformationBar.MENUE_BUTTON_PADDING);
        row();   
        add(displayColonyDetails).width(InformationBar.MENUE_BUTTON_WIDTH).height(InformationBar.MENUE_BUTTON_HEIGHT).pad(InformationBar.MENUE_BUTTON_PADDING);
        row();   
    }

    private void addAstroButtonListeners() {
        displayExtraData.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 onDisplayExtraDataClick();
            }
         });  
        displayLifeRating.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 onDisplayLifeRatingClick();
            }
         });   
        displayColonyDetails.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 onDisplayColonyDetailsClick();
            }
         });   
    }
 
    private void onDisplayExtraDataClick() {
        infoBarManager.onDisplayExtraDataClick();
    }    

    private void onDisplayLifeRatingClick() {
        infoBarManager.onDisplayLifeRatingClick(); 
    }
    
    private void onDisplayColonyDetailsClick() {
        infoBarManager.onDisplayColonyDetailsClick();
    }
}
