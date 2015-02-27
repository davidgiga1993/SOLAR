package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;

/**
 * Created by Arga on 22.02.2015.
 */
public class NavigationBar extends Window {

    protected Table layoutTable;

    private final Cell<ScrollPane> contentCell;
    private final ScrollPane shipPane;
    private final ScrollPane bodyPane;

    private final BodyNavigationTable bodyTable;
    private final ShipNavigationTable shipTable;


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

        bodyPane = new ScrollPane(bodyTable);
        shipPane = new ScrollPane(shipTable);

        // layout
        layoutTable = new Table();

        // 2 buttons up top
        layoutTable.add(bodyButton).top();
        layoutTable.add(shipButton).top();
        // content below
        layoutTable.row().colspan(2);
        layoutTable.add(bodyPane).expand().top().fill();

        // save contentCell to switch between tabs later on
        contentCell = layoutTable.getCell(bodyPane);

        this.add(layoutTable).expand().fill();
    }

    /**
     * Set's the contentCell to show the Bodies
     */
    private void onBodyClick() {
        contentCell.setActor(bodyPane).expand();
    }

    /**
     * Set's the contentCell to show the Ships
     */
    private void onShipClick() {
        contentCell.setActor(shipPane).expand();
    }

    // TODO: Message Framework integration, listen to actorAddedEvent
}
