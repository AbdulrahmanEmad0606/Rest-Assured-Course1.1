import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestResponse {
    @Test
    public void testDeserialization(){
        RequestSpecification requestSpecification = given().baseUri("https://reqres.in/").basePath("api");
        Response response=requestSpecification.get("users/2");

        // get the response[JSON] and convert it to java class then store it in this obj[responseBody]
        ResponseBody responseBody=response.as(ResponseBody.class);
        Assert.assertEquals(responseBody.data.id,2);
        response.prettyPrint();
    }

    // another way by using asserting on JSON Format
    @Test
    public void testGet(){
        RestAssured.given().baseUri("https://reqres.in/").basePath("api/")
                .when().get("users/2")
                .then().assertThat().body("data.id",equalTo(2));
    }
}
