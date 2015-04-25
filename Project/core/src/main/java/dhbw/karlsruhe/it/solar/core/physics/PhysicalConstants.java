package dhbw.karlsruhe.it.solar.core.physics;

import dhbw.karlsruhe.it.solar.core.physics.SurfaceGravity.GravUnit;

/**
 * Created by Arga on 25.11.2014.
 */
public class PhysicalConstants {
	public static final float GRAVITATIONAL_CONSTANT = 0.00000000006673f;
	public static final float PI_SQUARE_TIMES_FOUR = 39.478417604357434475337963999505f;
	public static final SurfaceGravity EARTH_SURFACE_GRAVITY = new SurfaceGravity(9.80665f,GravUnit.MS2);
	public static final float WIENS_DISPLACEMENT_CONSTANT = 0.0028977721f;
	

    private PhysicalConstants() {
        
    }

}
