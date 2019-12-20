package GitHubAPITest.GitHubAPITest;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;

/**
 * @author Ekta Sachdev
 * 
 *         This runner class will initiate Cucumber and call the integration
 *         test feature file to run the test cases. After test run, HTML reports
 *         will be generated under "reports" folder.
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/GitHubSearchIntegrationTest.feature", plugin = { "pretty",
		"html:reports" })
public class GitHubSearchIntegrationTest {

	/**
	 * This method will perform initial setup needed for Rest Assured
	 */
	@BeforeClass
	public static void setUp() {
		// github base uri
		RestAssured.baseURI = "https://api.github.com";
		// github base port
		RestAssured.port = 443;
	}
}