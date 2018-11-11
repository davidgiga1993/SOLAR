package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dhbw.karlsruhe.it.solar.core.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andi
 * created 2015-05-04
 */
public abstract class BaseResource implements BaseResourceInterface {

    public final static long THOUSAND = 1000;
    public final static long MILLION = THOUSAND * THOUSAND;
    final static long BILLION = THOUSAND * MILLION;
    final static long TRILLION = THOUSAND * BILLION;

    @XmlElement(name = "Value")
    long value;
    @XmlElement(name = "Values_Of_Last_Month")
    List<Long> valuesOfLastMonth = new ArrayList<>();
    @XmlElement(name = "Change_Last_Month")
    float changeLastMonth;
    @XmlElement(name = "Time_Of_Last_Resource_Update")
    private Time oldGameTime;

    @Override
    public Table loadIcon() {
        Table imageTable = new Table();
        Image selectedImage = new Image();
        selectedImage.setDrawable(new TextureRegionDrawable(getIcon()));
        imageTable.add(selectedImage).width(ConfigurationConstants.ICON_SIZE).height(ConfigurationConstants.ICON_SIZE);
        return imageTable;
    }

    boolean isANewDay() {
        if (oldGameTime == null || (int) GameStartStage.GAME_TIME.getGameTimeElapsed().inDays() != (int) oldGameTime.inDays()) {
            oldGameTime = new Time(GameStartStage.GAME_TIME.getGameTimeElapsed().inDays(), Time.TimeUnit.DAYS);
            return true;
        }
        return false;
    }

    void updateValuesOfLastMonthList() {
        valuesOfLastMonth.add(value);
        if (valuesOfLastMonth.size() > 31) {
            valuesOfLastMonth.remove(0);
        }
    }

    String formatValue(float number) {
        return String.format("%.02f", number);
    }

    float inThousands(float value) {
        return value / (float) THOUSAND;
    }

    float inMillions(float value) {
        return value / (float) MILLION;
    }

    float inBillions(float value) {
        return value / (float) BILLION;
    }

    float inTrillions(float value) {
        return value / (float) TRILLION;
    }
}
