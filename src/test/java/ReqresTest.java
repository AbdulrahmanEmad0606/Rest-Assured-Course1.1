import OOP.GetRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqresTest {
    @Test
    public void testGet(){
      //  RestAssured.given().baseUri("https://reqres.in/api/users/2").when().get().then().assertThat().statusCode(201);
        // re-use after applying OOP and deserialization
        GetRequest getUser = new GetRequest("https://reqres.in/","api/users/2");
        Response response1 = getUser.send();
        response1.prettyPrint();
        ResponseBody responseBody=response1.as(ResponseBody.class);
        Assert.assertEquals(responseBody.data.last_name,"Weaver");
    }
    @Test
    public void testGetList(){
        RestAssured.given()
                .baseUri("https://reqres.in/").basePath("/api").param("page","2")
                .when().get("/users")
                .then().assertThat().statusCode(200)
                .log().all();
    }
    @Test
    public void testPost(){
        UserData ahmed = new UserData("Ahmed","Test Lead");

        // to change any value we can override the value

        ahmed.job="QC Engineer";

        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        RestAssured.given().baseUri("https://reqres.in/").basePath("api/")
                .contentType("application/json")
                .body(ahmed)
                .when().post("/users")
                .then().assertThat().statusCode(201)
                .log().all();
    }
    @Test
    public void testPut(){
        UserData ahmed = new UserData("Ahmed","Senior QC");
        String body = "{\n" +
                "    \"name\": \"Mohamed\",\n" +
                "    \"job\": \"TL\"\n" +
                "}";
        RestAssured.given().baseUri("https://reqres.in/").basePath("api/")
                .contentType("application/json")
                .body(ahmed)
                .when().put("/users/2")
                .then().assertThat().statusCode(200)
                .log().all();
    }
    @Test
    public void testDelete(){
        RestAssured.given().baseUri("https://reqres.in/").basePath("api/")
                .when().delete("users/2")
                .then().assertThat().statusCode(204)
                .log().all();
    }
}
