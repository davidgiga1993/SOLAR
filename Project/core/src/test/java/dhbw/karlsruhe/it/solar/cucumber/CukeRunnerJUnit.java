package dhbw.karlsruhe.it.solar.cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@Cucumber.Options(
        format = {"pretty", "html:target/cucumber"},
        features = {"src/test/resources"}
)
public class CukeRunnerJUnit {

}
