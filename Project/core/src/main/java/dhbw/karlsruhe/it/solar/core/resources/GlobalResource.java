package dhbw.karlsruhe.it.solar.core.resources;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dhbw.karlsruhe.it.solar.config.ConfigurationConstants;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Superclass for globally stored resources which are not bound to a specific place but to a player.
 *
 * @author Andi
 * created 2015-05-16
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({TotalPopulation.class, Credits.class})
public abstract class GlobalResource extends BaseResource implements GlobalResourceInterface {

    @Override
    public void empty() {
        value = 0;
    }

    @Override
    public long getNumber() {
        return value;
    }

    @Override
    public Table getResourceBarTitle() {
        Table title = new Table();
        title.add(loadIcon()).left();
        title.add(new Label(getNameOfResourceBarTitle(), Styles.MENUELABEL_STYLE)).expandX().fillX().padLeft(ConfigurationConstants.INNER_CELL_PADDING);
        return title;
    }

    @Override
    public String toString() {
        if (inTrillions(value) > 0.1f) {
            return constructResourceStatement("tr", inTrillions(value));
        }
        if (inBillions(value) > 0.1f) {
            return constructResourceStatement("bi", inBillions(value));
        }
        if (inMillions(value) > 0.1f) {
            return constructResourceStatement("mi", inMillions(value));
        }
        if (inThousands(value) > 0.1f) {
            return constructResourceStatement("k", inThousands(value));
        }
        return constructResourceStatement("", value);
    }

    protected abstract String constructResourceStatement(String unit, float value);

    protected abstract String getNameOfResourceBarTitle();
}
