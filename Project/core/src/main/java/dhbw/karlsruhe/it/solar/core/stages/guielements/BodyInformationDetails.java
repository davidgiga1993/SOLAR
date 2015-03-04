package dhbw.karlsruhe.it.solar.core.stages.guielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import dhbw.karlsruhe.it.solar.core.solar.SolarEngine;
import dhbw.karlsruhe.it.solar.core.usercontrols.Asteroid;
import dhbw.karlsruhe.it.solar.core.usercontrols.AstronomicalBody;
import dhbw.karlsruhe.it.solar.core.usercontrols.Moon;

import java.text.DecimalFormat;

/**
 * Created by Arga on 24.02.2015.
 */
public class BodyInformationDetails extends InformationDetails {

    private static final float UNIT_WIDTH = 20;

    private static Label orbitalRadiusName = new Label("Average Orbital Radius", SolarEngine.get().styles.defaultLabelStyle);
    private static Label orbitalPeriodName = new Label("Orbital Period", SolarEngine.get().styles.defaultLabelStyle);
    private static Label radiusName = new Label("Radius", SolarEngine.get().styles.defaultLabelStyle);
    private static Label massName = new Label("Mass", SolarEngine.get().styles.defaultLabelStyle);

    private static Label radiusValue = new Label("", SolarEngine.get().styles.defaultLabelStyle);
    private static Label massValue = new Label("", SolarEngine.get().styles.defaultLabelStyle);
    private static Label orbitalRadiusValue = new Label("", SolarEngine.get().styles.defaultLabelStyle);
    private static Label orbitalPeriodValue = new Label("", SolarEngine.get().styles.defaultLabelStyle);

    private static Label radiusUnit = new Label("km", SolarEngine.get().styles.defaultLabelStyle);
    private static Label massUnit = new Label("S", SolarEngine.get().styles.defaultLabelStyle);
    private static Label orbitalRadiusUnit = new Label("AU", SolarEngine.get().styles.defaultLabelStyle);
    private static Label orbitalPeriodUnit = new Label("d", SolarEngine.get().styles.defaultLabelStyle);

    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static DecimalFormat df4 = new DecimalFormat("#.####");
    private static DecimalFormat dfE = new DecimalFormat("0.##E0");

    static {
        radiusValue.setAlignment(Align.right);
        massValue.setAlignment(Align.right);
        orbitalRadiusValue.setAlignment(Align.right);
        orbitalPeriodValue.setAlignment(Align.right);
    }

    public BodyInformationDetails(AstronomicalBody body) {
        super();

        if(body instanceof Moon || body instanceof Asteroid) {
            initLabelsDefault(body);
        } else {
            initLabelsDefault(body);
        }

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
        float orbitalPeriodFloat = body.getOrbitalPeriodInDays();

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