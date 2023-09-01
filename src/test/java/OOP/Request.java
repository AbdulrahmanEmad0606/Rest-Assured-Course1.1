package OOP;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Request {
    protected  String baseUri;
    protected String endPoint;
    RequestSpecification requestSpecification;

    public Request(String baseUri, String endPoint) {
        this.baseUri = baseUri;
        this.endPoint = endPoint;
        requestSpecification = given().baseUri(baseUri).basePath(endPoint);
    }
    public void setHeader(String header,String value){
        requestSpecification.header(header,value);
    }
    // get the response
    public Response send(){
        return requestSpecification.get();
    }
}
