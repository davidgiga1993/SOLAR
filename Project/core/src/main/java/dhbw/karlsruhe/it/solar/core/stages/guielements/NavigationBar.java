package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by Arga on 22.02.2015.
 */
public class NavigationBar extends Window {

    protected Table layoutTable;
    protected Table bodyTable;
    protected Table shipTable;

    private final Cell<Table> contentCell;

    // TODO: NavigationBar - implement Scrolling functionality. Maybe in BodyNavigationTable / ShipNavigationTable

    public NavigationBar() {
        super("Navigation", SolarEngine.get().styles.tooltipSkin);
        setMovable(false);

        // Tab Buttons
        TextButton bodyButton = new TextButton("Bodies", SolarEngine.get().styles.tooltipSkin);
        TextButton shipButton = new TextButton("Ships", SolarEngine.get().styles.tooltipSkin);

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

        // To be replaced with actual NavigationTables
        bodyTable = new BodyNavigationTable();
        shipTable = new ShipNavigationTable();

        // layout
        layoutTable = new Table();

        // 2 buttons up top
        layoutTable.add(bodyButton).top();
        layoutTable.add(shipButton).top();
        // content below
        layoutTable.row().colspan(2);
        layoutTable.add(bodyTable).expand().top().fill();

        // save contentCell to switch between tabs later on
        contentCell = layoutTable.getCell(bodyTable);

        this.add(layoutTable).expand().fill();
    }

    /**
     * Set's the contentCell to show the Bodies
     */
    private void onBodyClick() {
        contentCell.setActor(bodyTable).expand();
    }

    /**
     * Set's the contentCell to show the Ships
     */
    private void onShipClick() {
        contentCell.setActor(shipTable).expand();
    }
}
