package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import dhbw.karlsruhe.it.solar.core.space_units.SpaceUnit;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.Orbiter;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;
import dhbw.karlsruhe.it.solar.core.usercontrols.SystemRoot;

/**
 * Created by Arga on 24.02.2015.
 * Substantially revised by Andi on: 2015-04-19
 */
public class InfoBarOverviewTable extends Table {

    private final TextButton showOnMap = new TextButton("Show On Map", Styles.TOOLTIPSKIN);
    private SolarActor selectedActor;
    private LabelStyle style = Styles.MENUELABEL_STYLE;
    private final Label missionTypeLabel = new Label("", style);
    private final NavBarBaseLabel missionTypeTargetLabel = new NavBarBaseLabel("", null, style);
    private final Label missionTimeLabel = new Label("", style);
    private final Label missionTimeValueLabel = new Label("", style);
    private final Label missionDistanceLabel = new Label("", style);
    private final Label missionDistanceValueLabel = new Label("", style);

    public InfoBarOverviewTable() {
        showOnMap.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onShowOnMapClick();
            }
        });
    }

    public InfoBarOverviewTable displayOverview(SolarActor selectedActor) {
        this.selectedActor = selectedActor;
        clear();
        addNameAndTypeLabels();
        addMissionInfoLabels();
        addShowOnMapButton();
        return this;
    }

    private void addShowOnMapButton() {
        add(showOnMap).align(Align.center).colspan(2).width(InfoBar.ACTION_BUTTON_WIDTH).height(InfoBar.ACTION_BUTTON_HEIGHT).pad(InfoBar.ACTION_BUTTON_PADDING);
    }

    private void addNameAndTypeLabels() {
        add(new NavBarBaseLabel(selectedActor.getName(), selectedActor, Styles.BOLDLABEL_STYLE)).left();
        row();
        add(new Label("Type: ", style)).left();
        add(new Label(selectedActor.getTypeName(), style)).right();
        row();
    }

    private void addMissionInfoLabels() {
        add(missionTypeLabel).left();
        add(missionTypeTargetLabel).right().expand();
        row();
        add(missionTimeLabel).left();
        add(missionTimeValueLabel).right();
        row();
        add(missionDistanceLabel).left();
        add(missionDistanceValueLabel).right();
        row();
    }

    private void onShowOnMapClick() {
        GameStartStage.inputListenerMoveCamera(selectedActor);
    }

    public void reload() {
        reloadMissionInfo();
    }

    private void reloadMissionInfo() {
        if (((Orbiter) selectedActor).isInOrbit()) {
            missionTypeLabel.setText("In Orbit of: ");
            missionTypeTargetLabel.setText(((Orbiter) selectedActor).getNameOfPrimary());
            setNavBarBaseLabelActor();
            missionTimeLabel.setText("Orbital Period: ");
            missionTimeValueLabel.setText(((Orbiter) selectedActor).getOrbitalPeriod().toString());
            missionDistanceLabel.setText("Semi-major Axis: ");
            missionDistanceValueLabel.setText(((Orbiter) selectedActor).getOrbitalRadius().toString());
            return;
        }
        if (selectedActor instanceof SpaceUnit) {
            missionTypeLabel.setText("En route to: ");
            missionTypeTargetLabel.setText(((SpaceUnit) selectedActor).getMissionDescription());
            missionTypeTargetLabel.setActor(((SpaceUnit) selectedActor).getMissionTargetActor());
            missionTimeLabel.setText("ETA: ");
            //TODO: Implementiere ETA-Funktionalität aus der AI-Berechnung raus (Schätzung okay)          
            missionTimeValueLabel.setText("Unknown");
            missionDistanceLabel.setText("Distance: ");
            missionDistanceValueLabel.setText(((SpaceUnit) selectedActor).getMissionDistanceValue());
            return;
        }
    }

    private void setNavBarBaseLabelActor() {
        if (((Orbiter) selectedActor).getPrimary() instanceof SystemRoot) {
            missionTypeTargetLabel.setActor(selectedActor);
            return;
        }
        missionTypeTargetLabel.setActor(((Orbiter) selectedActor).getPrimary());
    }
}
