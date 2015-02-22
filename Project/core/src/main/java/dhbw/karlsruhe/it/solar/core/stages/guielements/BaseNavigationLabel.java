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
public class BaseNavigationLabel extends Label {
    protected List<BaseNavigationLabel> children = new ArrayList<BaseNavigationLabel>();
    protected boolean childrenVisible = true;
    protected BaseNavigationLabel parent;
    protected SolarActor actor;
    protected CharSequence name;
    protected String tab;

    public BaseNavigationLabel(CharSequence text, String tab, SolarActor actor) {
        super(tab + text, SolarEngine.get().styles.defaultLabelStyle);
        this.actor = actor;
        this.tab = tab;
        this.name = text;
        addListener(new NavigationLabelListener());
    }

    public void setChildren(List<BaseNavigationLabel> children) {
        this.children = children;
        for (BaseNavigationLabel child : children) {
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

    }

    public void setChildrenVisibility(boolean newVisibiilty) {
        for (BaseNavigationLabel child : children) {
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

    protected class NavigationLabelListener extends ClickListener {
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
