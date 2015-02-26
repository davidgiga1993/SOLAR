package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import dhbw.karlsruhe.it.solar.core.inputlisteners.Selection;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Spaceship;

/**
 * Created by Arga on 24.02.2015.
 */
public class InformationBar extends Window {

    protected Table contentTable = new Table();
    protected Cell<InformationOverview> overviewCell;
    protected Cell<InformationDetails> detailsCell;
    protected Cell<InformationActions> actionCell;

    GameStartStage gameStage = (GameStartStage) SolarEngine.get().stageManager.getStage("GameStartStage");

    private final InformationOverview overview;
    private final InformationDetails details;
    private final InformationActions actions;

    public InformationBar() {
        super("Information", SolarEngine.get().styles.tooltipSkin);

        overview = new InformationOverview(gameStage.selectedActors.getRepresentative());
        details = new InformationDetails();
        actions = new InformationActions();

        contentTable.add(overview).left();
        contentTable.add(details).expand().fill();
        contentTable.add(actions).right();

        overviewCell = contentTable.getCell(overview);
        detailsCell = contentTable.getCell(details);
        actionCell = contentTable.getCell(actions);

        add(contentTable).expand().fill();
    }

    public void onSelectionChange(Selection newSelection) {
        SolarActor actor = newSelection.getRepresentative();
        overview.changeActor(actor);

        InformationDetails newDetails;
        if (actor instanceof Spaceship) {
            newDetails = new ShipInformationDetails((Spaceship) actor);
        } else if(actor instanceof AstronomicalBody) {
            newDetails = new BodyInformationDetails((AstronomicalBody) actor);
        } else {
            newDetails = new InformationDetails();
        }
        detailsCell.setActor(newDetails);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // TODO: After refactoring Selection this should be called on demand, and not every frame. for now it's ok.
        onSelectionChange(gameStage.selectedActors);
    }
}