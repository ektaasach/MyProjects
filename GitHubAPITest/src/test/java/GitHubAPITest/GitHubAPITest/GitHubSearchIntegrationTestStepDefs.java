package GitHubAPITest.GitHubAPITest;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Ekta Sachdev
 * 
 *         This is the Step definition file that defines the implementation for
 *         the steps mentioned in the feature file.
 *
 */
public class GitHubSearchIntegrationTestStepDefs {

	// request object
	private RequestSpecification request;
	// response object
	private Response response;

	/**
	 * Given Step Definition Implementation Method for use case of sending query
	 * parameter to github search api.
	 * 
	 * @param paramName
	 * @param paramValue
	 * @param scenarioName
	 */
	@Given("^User send query param (.*) with value (.*) to search GitHub using (.*)$")
	public void setQueryParam(String paramName, String paramValue, String scenarioName) {
		request = RestAssured.given();
		request = request.queryParam(paramName, paramValue);
	}

	/**
	 * Given Step Definition Implementation Method for use case of sending page
	 * parameter to github search api.
	 * 
	 * @param pageParam
	 * @param pageNumber
	 * @param paramName
	 * @param paramValue
	 * @param scenarioName
	 */
	@Given("^User send page parameter (.*) with value (\\d+) along with query param (.*) having value (.*) to search GitHub using (.*)$")
	public void setQueryParamWithPageNumber(String pageParam, int pageNumber, String paramName, String paramValue,
			String scenarioName) {
		request = RestAssured.given();
		request.log().all();
		request = request.queryParam(paramName, paramValue).and().queryParam(pageParam, pageNumber);
	}

	/**
	 * Given Step Definition Implementation Method for use case of sending sort
	 * parameter to github search api.
	 * 
	 * @param sortParam
	 * @param sortOption
	 * @param order
	 * @param paramName
	 * @param paramValue
	 * @param scenarioName
	 */
	@Given("^User send sort criteria (.*) with value (.*) and (.*) along with query param (.*) having value (.*) to search GitHub using (.*)$")
	public void setQueryParamWithSortOption(String sortParam, String sortOption, String order, String paramName,
			String paramValue, String scenarioName) {
		request = RestAssured.given();
		// request.log().all();
		request = request.queryParam(paramName, paramValue).and().queryParam(sortParam, sortOption).and()
				.queryParam("order", order);
	}

	/**
	 * When Step Definition Implementation Method.
	 * 
	 * @param paramPath
	 */
	@When("^User makes a GET request with path param (.*)$")
	public void getRequest(String paramPath) {
		response = request.get(paramPath);
	}

	/**
	 * Then Step Definition Implementation method to verify response status code for
	 * github search api.
	 * 
	 * @param statusCode
	 */
	@Then("^User should receive HTTP statuscode (\\d+)$")
	public void checkStatusCode(int statusCode) {
		response.then().statusCode(Matchers.equalTo(statusCode)).assertThat().log().body(true);
	}

	/**
	 * Then Step Definition Implementation method to verify response body content
	 * for github search api.
	 * 
	 * @param expectedResponse
	 */
	@Then("^response should contain (.*)$")
	public void responseCheck(String expectedResponse) {
		if (response.getStatusCode() == 200) {
			response.then().assertThat().body("total_count", Matchers.equalTo(Integer.parseInt(expectedResponse)));
		} else {
			response.then().assertThat().body("message", Matchers.equalTo(expectedResponse));
		}
	}

	/**
	 * Then Step Definition Implementation method to verify response body content
	 * size for github search api.
	 * 
	 * @param expectedItemCount
	 */
	@Then("^repsonse should contain (\\d+) items$")
	public void itemCountCheck(int expectedItemCount) {
		response.then().assertThat().body("items.size()", Matchers.equalTo(expectedItemCount));
	}

	/**
	 * Then Step Definition Implementation method to verify response body content
	 * order for github search api.
	 * 
	 * @param expectedOrder
	 */
	@Then("^response should have items in (.*) order$")
	public void itemOrderCheck(String expectedOrder) {
		List<String> jsonResponseList = response.jsonPath().getList("items.created_at");
		if (expectedOrder.equalsIgnoreCase("desc"))
			Assert.assertTrue("", jsonResponseList.get(0).compareTo(jsonResponseList.get(1)) < 0);
		else
			Assert.assertTrue("", jsonResponseList.get(0).compareTo(jsonResponseList.get(1)) > 0);
	}

}
