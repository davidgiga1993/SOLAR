package dhbw.karlsruhe.it.solar.core.stages.guielements;

import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

/**
 * Created by Arga on 22.02.2015.
 */
public class BodyNavigationLabel extends BaseNavigationLabel {

    private BodyNavigationTable container;

    public BodyNavigationLabel(CharSequence name, String tab, SolarActor actor, BodyNavigationTable container) {
        super(name, tab, actor);
        this.container = container;
    }

    @Override
    protected void toggleChildren() {
        super.toggleChildren();
        container.buildTable();
    }
}
