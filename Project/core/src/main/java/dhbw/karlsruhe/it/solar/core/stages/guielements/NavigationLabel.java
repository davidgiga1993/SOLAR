package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public class NavigationLabel extends Label {

    protected List<NavigationLabel> children = new ArrayList<NavigationLabel>();
    protected boolean childrenVisible = true;
    protected NavigationLabel parent;

    protected SolarActor actor;

    protected CharSequence name;
    protected String tab;

    private BodyNavigationTable container;

    public NavigationLabel(CharSequence text, String tab, SolarActor actor, BodyNavigationTable container) {
        super(tab + text, SolarEngine.get().styles.defaultLabelStyle);
        addListener(new NavigationLabelListener());
        this.name = text;
        this.tab = tab;
        this.actor = actor;
        this.container = container;
    }

    public void setChildren(List<NavigationLabel> children) {
        this.children = children;
        for (NavigationLabel child : children) {
            child.parent = this;
        }
        if (!children.isEmpty()) {
            setText(tab + "+ " + name);
        }
    }

    private void onLeftClick(InputEvent event) {
        // replace the actor with the actor represented by this NavigationLabel
        event.setTarget(actor);
        // and let the GameInputListener do his job
        GameStartStage.inputListener.interact(event);
    }

    private void onRightClick() {
    }

    protected void toggleChildren() {
        if (children == null || children.isEmpty()) {
            return;
        }

        childrenVisible = !childrenVisible;
        setChildrenVisibility(childrenVisible);
        if(childrenVisible) {
            setText(tab + "-  " + name);
        } else {
            setText(tab + "+ " + name);
        }
        container.buildTable();
    }

    public void setChildrenVisibility(boolean newVisibiilty) {
        for (NavigationLabel child : children) {
            child.setVisible(newVisibiilty);
        }
    }

    /**
     * A NavigationLabel is only visible, if it's own visibility is set to true AND it's parent's visibility is true as well.
     * @return visibility
     */
    @Override
    public boolean isVisible() {
        if(parent == null) {
            return super.isVisible();
        }
        return super.isVisible() && parent.isVisible();
    }

    private class NavigationLabelListener extends ClickListener {
        public NavigationLabelListener() {
            super();
            // listen to any button
            setButton(-1);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            switch(event.getButton()) {
                case Input.Buttons.LEFT:
                    // decide whether it was a click on the label or the expand indicator
                    if(!children.isEmpty() && x < getX() + 6 + tab.length() * 4) {
                        toggleChildren();
                    } else {
                        onLeftClick(event);
                    }
                    break;

                case Input.Buttons.RIGHT:
                    onRightClick();
                    break;

                default:
                    break;
            }
        }
    }
}
