package OOP;
public class GetRequest extends Request{
    public GetRequest(String baseUri, String endPoint) {
        super(baseUri, endPoint);
    }
    public void setQueryParameter(String queryParameter,String value){
        requestSpecification.param(queryParameter,value);
    }
}
