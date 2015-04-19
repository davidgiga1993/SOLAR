package dhbw.karlsruhe.it.solar.core.stages.guielements;

import java.text.DecimalFormat;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.it.solar.core.astronomical_objects.Asteroid;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.astronomical_objects.Moon;
import dhbw.karlsruhe.it.solar.core.usercontrols.Styles;

/**
 * Old code extracted from BodyInformationDetails, displaying orbital information.
 * @author Andi
 * 2015-04-14
 */
public class OrbitalInformationDetails extends InformationDetails {
    
    private static final float UNIT_WIDTH = 20;

    private static Label orbitalRadiusName = new Label("Average Orbital Radius", Styles.DEFAULTLABEL_STYLE);
    private static Label orbitalPeriodName = new Label("Orbital Period", Styles.DEFAULTLABEL_STYLE);
    private static Label radiusName = new Label("Radius", Styles.DEFAULTLABEL_STYLE);
    private static Label massName = new Label("Mass", Styles.DEFAULTLABEL_STYLE);

    private static Label radiusValue = new Label("", Styles.DEFAULTLABEL_STYLE);
    private static Label massValue = new Label("", Styles.DEFAULTLABEL_STYLE);
    private static Label orbitalRadiusValue = new Label("", Styles.DEFAULTLABEL_STYLE);
    private static Label orbitalPeriodValue = new Label("", Styles.DEFAULTLABEL_STYLE);

    private static Label radiusUnit = new Label("km", Styles.DEFAULTLABEL_STYLE);
    private static Label massUnit = new Label("S", Styles.DEFAULTLABEL_STYLE);
    private static Label orbitalRadiusUnit = new Label("AU", Styles.DEFAULTLABEL_STYLE);
    private static Label orbitalPeriodUnit = new Label("d", Styles.DEFAULTLABEL_STYLE);

    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static DecimalFormat df4 = new DecimalFormat("#.####");
    private static DecimalFormat dfE = new DecimalFormat("0.##E0");
    
    static {
        radiusValue.setAlignment(Align.right);
        massValue.setAlignment(Align.right);
        orbitalRadiusValue.setAlignment(Align.right);
        orbitalPeriodValue.setAlignment(Align.right);
    }
    
    public OrbitalInformationDetails(AstronomicalBody body) {
        super();
        
        if(body instanceof Moon || body instanceof Asteroid) {
            initLabelsDefault(body);
        } else {
            initLabelsDefault(body);
        }
        
        orbitalInformation();
    }
    
    private void orbitalInformation() {
        add(radiusName);
        add(radiusValue).width(VALUE_WIDTH);
        add(radiusUnit).width(UNIT_WIDTH);
        row();
        add(massName);
        add(massValue).width(VALUE_WIDTH);
        add(massUnit).width(UNIT_WIDTH);
        row();
        add(orbitalRadiusName);
        add(orbitalRadiusValue).width(VALUE_WIDTH);
        add(orbitalRadiusUnit).width(UNIT_WIDTH);
        row();
        add(orbitalPeriodName);
        add(orbitalPeriodValue).width(VALUE_WIDTH);
        add(orbitalPeriodUnit).width(UNIT_WIDTH);
    }

    private void initLabelsDefault(AstronomicalBody body) {
        float radiusFloat = body.getRadius().asKilometres();
        float massFloat = body.getMass().asSolarMass();
        float orbitalRadiusFloat = body.getOrbitalRadius().asAstronomicalUnit();
        float orbitalPeriodFloat = body.getOrbitalPeriod().inDays();

        String radius = df2.format(radiusFloat);
        String mass = dfE.format(massFloat);
        mass = mass.replace("E", " x 10^");
        String orbitalRadius = df4.format(orbitalRadiusFloat);
        String orbitalPeriod = df2.format(orbitalPeriodFloat);

        radiusValue.setText(radius);
        massValue.setText(mass);
        orbitalRadiusValue.setText(orbitalRadius);
        orbitalPeriodValue.setText(orbitalPeriod);
    }
}
