package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Created by Arga on 22.02.2015.
 */
public class NavigationBar extends Window {

    protected Table layoutTable;

    private final Cell<ScrollPane> contentCell;
    private final ScrollPane shipPane;
    private final ScrollPane bodyPane;
    private final ScrollPane colonyPane;

    private final BodyNavigationTable bodyTable;
    private final ShipNavigationTable shipTable;
    private final ColonyNavigationTable colonyTable;


    public NavigationBar() {
        super("Navigation", Styles.TOOLTIPSKIN);
        setMovable(false);

        // Tab Buttons
        TextButton bodyButton = new TextButton("Bodies", Styles.TOOLTIPSKIN);
        TextButton shipButton = new TextButton("Units", Styles.TOOLTIPSKIN);
        TextButton colonyButton = new TextButton("Colonies", Styles.TOOLTIPSKIN);

        // Button Listeners
        bodyButton.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
                onBodyClick();
           }
        });
        shipButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onShipClick();
            }
        });
        colonyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onColonyClick();
            }
        });

        // To be replaced with actual NavigationTables
        bodyTable = new BodyNavigationTable();
        shipTable = new ShipNavigationTable();
        colonyTable = new ColonyNavigationTable();

        bodyPane = new ScrollPane(bodyTable);
        shipPane = new ScrollPane(shipTable);
        colonyPane = new ScrollPane(colonyTable);

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

}
