
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("miscellaneousFCAndFullChestBonusCases.feature")
@SelectClasspathResource("multiplayerCases.feature")
@SelectClasspathResource("playerDieCases.feature")
@SelectClasspathResource("playerGetScoreCases.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "cucumberTest.testcode")
public class TestSuiteCucumber {
}
