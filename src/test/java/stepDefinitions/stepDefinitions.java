package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class stepDefinitions {

    Response response;

    @Given("^Open localhost$")
    public void openLocalhost() {
        RestAssured.baseURI = "http://localhost:3000";
    }

    @Given("^Send GET request to the \"([^\"]*)\"$")
    public void sendGETRequestToThe(String uri) throws Throwable {
        RequestSpecification request = RestAssured.given();
        response = request.get(uri);
    }

    @Then("^Check that status code is positive (\\d+)$")
    public void checkThatStatusCodeIsPositive(int code) {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @Then("^Check that status code is negative (\\d+)$")
    public void checkThatStatusCodeIsNegative(int code) {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @Then("^Check the status line \"([^\"]*)\"$")
    public void checkTheStatusLine(String status) throws Throwable {
        Assert.assertEquals(status, response.getStatusLine());
    }

    @Given("^Get all headers of the \"([^\"]*)\"$")
    public void getAllHeadersOfThe(String uri) throws Throwable {
        RequestSpecification request = RestAssured.given();
        Response response = request.get(uri);
        Headers allHeaders = response.headers();

        for(Header header : allHeaders)
        { System.out.println(header.getName() + " : " + header.getValue()); }
    }

    @Then("^Check some \"([^\"]*)\" \"([^\"]*)\"$")
    public void checkSome(String name, String value) throws Throwable {
        Assert.assertEquals(value, response.header(name));
    }

    @Then("^Check some nonexistent \"([^\"]*)\" value is null")
    public void checkSomeNonexistentValueIsNull(String name) throws Throwable {
        Assert.assertEquals(null, response.header(name));
    }

    @Given("^Get full body of the \"([^\"]*)\"$")
    public void getFullBodyOfThe(String uri) throws Throwable {
        RequestSpecification request = RestAssured.given();
        Response response = request.get(uri);
        ResponseBody body = response.getBody();
        System.out.println("Response Body is: " + body.asString());
    }

    @And("^Get one \"([^\"]*)\" of the \"([^\"]*)\"$")
    public void getOneOfThe(String value, String body) throws Throwable {
        JsonPath jsonPath = response.jsonPath();
        System.out.println(body + jsonPath.get(value));

    }

    @Then("^Validate the \"([^\"]*)\" of \"([^\"]*)\"$")
    public void validateTheOf(String value, String expValue) throws Throwable {
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expValue, jsonPath.get(value));
    }

    @Then("^Get one incorrect \"([^\"]*)\" of the body and result is null$")
    public void getOneIncorrectOfTheBodyAndResultIsNull(String value) throws Throwable {
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(null, jsonPath.get(value));
    }
}


