package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arga on 22.02.2015.
 */
public class NavigationLabel extends Label {

    // TODO: implement expand/shrink indicator
    // TODO: implement selection and camera movement funcionality

    protected List<NavigationLabel> children = new ArrayList<NavigationLabel>();
    protected boolean childrenVisible = true;
    protected NavigationLabel parent;

    private BodyNavigationTable container;

    public NavigationLabel(CharSequence text, BodyNavigationTable container) {
        super(text, SolarEngine.get().styles.defaultLabelStyle);
        addListener(new NavigationLabelListener());
        this.container = container;
    }

    public void setChildren(List<NavigationLabel> children) {
        this.children = children;
        for (NavigationLabel child : children) {
            child.parent = this;
        }
    }

    private void onLeftClick() {
        toggleChildren();
    }

    private void onRightClick() {

    }

    protected void toggleChildren() {
        childrenVisible = childrenVisible ? false : true;
        setChildrenVisibility(childrenVisible);
        container.buildTable();
    }

    public void setChildrenVisibility(boolean newVisibiilty) {
        for (NavigationLabel child : children) {
            child.setVisible(newVisibiilty);
        }
    }

    /**
     * A NavigationLabel is only visible, if it's own visibility is set to true AND it's parent's visibility is true as well.
     * @return
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
                    onLeftClick();
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
