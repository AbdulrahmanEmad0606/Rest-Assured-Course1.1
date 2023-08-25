import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ReqresTest {
    @Test
    public void testGet(){
        RestAssured.given().baseUri("https://reqres.in/api/users/2").when().get().then().assertThat().statusCode(201);
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
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        RestAssured.given().baseUri("https://reqres.in/").basePath("api/")
                .contentType("application/json")
                .body(body)
                .when().post("/users")
                .then().assertThat().statusCode(201)
                .log().all();
    }
    @Test
    public void testPut(){
        String body = "{\n" +
                "    \"name\": \"Mohamed\",\n" +
                "    \"job\": \"TL\"\n" +
                "}";
        RestAssured.given().baseUri("https://reqres.in/").basePath("api/")
                .contentType("application/json")
                .body(body)
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
