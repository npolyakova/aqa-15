package tests.ru;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;

public class StepDefinitions {

    RequestSpecification request;

    static Response response;

    Long id;

    @Before
    public static void setUri() {
        response = null;
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @BeforeStep
    public void setSpec() {
        request = RestAssured
                .given()
                .contentType("application/json");
    }

    @Given("user is logged out")
    public void userLogOut() {
        request
                .get("/user/logout")
                .then()
                .statusCode(200);
    }

    @When("the user log in again")
    public void userLogIn() {
        response = request
                .param("username", "string")
                .param("password", "string")
                .get("/user/login");
    }

    @Then("user is authorized successfully")
    public void checkAuth() {
        response
                .then()
                .statusCode(200);
    }

    @Given("user created a pet")
    public void createPet() {
        response = request
                .body("{" +
                        "  \"id\": 0," +
                        "  \"category\": {" +
                        "    \"id\": 0," +
                        "    \"name\": \"myCategoryName\"" +
                        "  }," +
                        "  \"name\": \"MyLittleCat\"," +
                        "  \"photoUrls\": [" +
                        "    \"string\"" +
                        "  ]," +
                        "  \"tags\": [" +
                        "    {" +
                        "      \"id\": 0," +
                        "      \"name\": \"someNewTag\"" +
                        "    }" +
                        "  ]," +
                        "  \"status\": \"available\"" +
                        "}")
                .post("/pet");

        id = response
                .then()
                .statusCode(200)
                .body("name", equalTo("MyLittleCat"))
                .extract()
                .path("id");
    }

    @When("user save updated pet info")
    public void updateInfo() {
        request
                .body("{" +
                        "  \"id\": 0," +
                        "  \"category\": {" +
                        "    \"id\": 0," +
                        "    \"name\": \"myCategoryName\"" +
                        "  }," +
                        "  \"name\": \"doggie\"," +
                        "  \"photoUrls\": [" +
                        "    \"string\"" +
                        "  ]," +
                        "  \"tags\": [" +
                        "    {" +
                        "      \"id\": 0," +
                        "      \"name\": \"someNewTag\"" +
                        "    }" +
                        "  ]," +
                        "  \"status\": \"available\"" +
                        "}")
                .put("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("doggie"));
    }

    @Then("pet info is updated")
    public void getPet() {
        request
                .get("/pet/{id}", id)
                .then()
                .statusCode(200)
                .body("name", equalTo("doggie"));

    }
}
