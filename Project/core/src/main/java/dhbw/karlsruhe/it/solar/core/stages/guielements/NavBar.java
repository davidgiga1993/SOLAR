package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import dhbw.karlsruhe.it.solar.core.stages.listeners.ChangeLambdaListener;
import dhbw.karlsruhe.it.solar.core.stages.listeners.ScrollFocusOnMouseOverListener;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 22.02.2015.
 */
public class NavBar extends Window implements ScrollFocusable {

    private final Cell<ScrollPane> contentCell;
    private final ScrollPane shipPane;
    private final ScrollPane bodyPane;
    private final ScrollPane colonyPane;
    private final NavBarBodyTable bodyTable;
    private final NavBarShipTable shipTable;
    private final NavBarColonyTable colonyTable;
    private Table layoutTable;

    public NavBar() {
        super("Navigation", Styles.TOOLTIPSKIN);
        setMovable(false);

        // Tab Buttons
        TextButton bodyButton = new TextButton("Bodies", Styles.TOOLTIPSKIN);
        TextButton shipButton = new TextButton("Units", Styles.TOOLTIPSKIN);
        TextButton colonyButton = new TextButton("Colonies", Styles.TOOLTIPSKIN);

        // Button Listeners
        bodyButton.addListener(new ChangeLambdaListener(this::onBodyClick));
        shipButton.addListener(new ChangeLambdaListener(this::onShipClick));
        colonyButton.addListener(new ChangeLambdaListener(this::onColonyClick));

        // To be replaced with actual NavigationTables
        bodyTable = new NavBarBodyTable();
        shipTable = new NavBarShipTable();
        colonyTable = new NavBarColonyTable();

        bodyPane = new ScrollPane(bodyTable);
        shipPane = new ScrollPane(shipTable);
        colonyPane = new ScrollPane(colonyTable);

        bodyPane.setScrollingDisabled(true, false);
        shipPane.setScrollingDisabled(true, false);
        colonyPane.setScrollingDisabled(true, false);

        // layout
        layoutTable = new Table();

        // 2 buttons up top
        layoutTable.add(bodyButton).top();
        layoutTable.add(shipButton).top();
        layoutTable.add(colonyButton).top();
        // content below
        layoutTable.row().colspan(2);
        layoutTable.add(bodyPane).expand().top().fill();

        // save contentCell to switch between tabs later on
        contentCell = layoutTable.getCell(bodyPane);

        this.add(layoutTable).expand().fill();

        addListener(new ScrollFocusOnMouseOverListener());
    }

    /**
     * Sets the contentCell to show the Bodies
     */
    private void onBodyClick() {
        contentCell.setActor(bodyPane).expand();
    }

    /**
     * Sets the contentCell to show the Ships
     */
    private void onShipClick() {
        shipTable.buildShipList();
        contentCell.setActor(shipPane).expand();
    }
    
    /**
     * Sets the contentCell to show the Colonies
     */
    private void onColonyClick() {
        colonyTable.buildColonyList();
        contentCell.setActor(colonyPane).expand();
    }


    @Override
    public Actor getScrollTarget() {
        return contentCell.getActor();
    }
}
