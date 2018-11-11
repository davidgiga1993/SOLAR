package dhbw.karlsruhe.it.solar.colony;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dhbw.karlsruhe.it.solar.core.physics.Power;
import dhbw.karlsruhe.it.solar.core.physics.Time;
import dhbw.karlsruhe.it.solar.core.resources.Credits;

interface Building {

    /**
     * Defines the running costs in Credits this building causes.
     *
     * @param deltaT
     * @return Upkeep costs over the time interval
     */
    Credits payUpKeep(Time deltaT);

    /**
     * States how many instances of that building type are currently in operation in the colony.
     * Buildings can be off-line for various reasons, for example due to lack of electricity.
     *
     * @return
     */
    int getNumberOfBuildingsBuilt();

    /**
     * States how many instances of that building type are currently in operation in the colony.
     * Buildings can be off-line for various reasons, for example due to lack of electricity.
     *
     * @return
     */
    long getNumberOfBuildingsOnline();

    /**
     * Returns the Texture's icon as a TextureRegion
     *
     * @return
     */
    TextureRegion getIcon();

    /**
     * Orders the game to add a new building of that type to the colony.
     */
    void build();

    /**
     * Returns the electric power consumption of all buildings of this type in this colony.
     *
     * @return
     */
    Power getElectricPowerConsumption();

    /**
     * Returns the production capacity of this building.
     *
     * @return
     */
    long getCapacityPerBuilding();
}
