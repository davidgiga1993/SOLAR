package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import dhbw.karlsruhe.it.solar.core.stages.GameStartStage;
import dhbw.karlsruhe.it.solar.core.usercontrols.SolarActor;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public class NavBarBaseLabel extends Label {
    protected List<NavBarBaseLabel> children = new ArrayList<NavBarBaseLabel>();
    protected boolean childrenVisible = true;
    protected NavBarBaseLabel parent;
    protected SolarActor actor;
    protected CharSequence name;
    protected String tab;
    private NavBarBaseTable container;

    public NavBarBaseLabel(CharSequence text, String tab, SolarActor actor, NavBarBaseTable container) {
        super(tab + text, Styles.MENUELABEL_STYLE);
        this.actor = actor;
        this.tab = tab;
        this.name = text;
        this.container = container;
        addListener(new NavigationLabelListener());
    }
    
    public NavBarBaseLabel(CharSequence text, SolarActor actor, LabelStyle style) {
        super(text, style);
        this.actor = actor;
        this.tab = "";
        this.name = text;
        addListener(new NavigationLabelListener());
    }

    public void setChildren(List<NavBarBaseLabel> children) {
        this.children = children;
        for (NavBarBaseLabel child : children) {
            child.parent = this;
        }
        if (!children.isEmpty()) {
            setText(tab + "+ " + name);
        }
    }

    protected void onLeftClick(InputEvent event) {
        // replace the actor with the actor represented by this NavigationLabel
        event.setTarget(actor);
        // and let the GameInputListener do his job
        GameStartStage.inputListenerInteract(event);
    }

    private void onRightClick(InputEvent event) {
        event.setTarget(actor);
        GameStartStage.inputListenerNavigate(event);
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
        for (NavBarBaseLabel child : children) {
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
                    handleLeftClickButton(event, x);
                    break;
                case Input.Buttons.RIGHT:
                    onRightClick(event);
                    break;

                default:
                    break;
            }
        }

        /**
         * Decides whether it was a click on the label or the expand indicator
         * @param event
         * @param x
         */
        private void handleLeftClickButton(InputEvent event, float x) {
            if(!children.isEmpty() && x < getX() + 6 + tab.length() * 4) {
                toggleChildren();
            } else {
                onLeftClick(event);
            }
        }
    }

    public boolean isOfActor(Actor actor) {
        if(name.equals(actor.getName())) {
            return true;
        }
        return false;
    }

    public void setActor(SolarActor actor) {
        this.actor = actor;
    }
}
