package restframework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

/**
 * @author Pavel Romanov 01.03.2023
 */

public class RequestManager {
    private static final String CONTENT_TYPE_PARAM_NAME = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    public static Response executeGetQuery(String baseURL) {
        RestAssured.baseURI = baseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();
        return response;
    }

    public static Response executePostQuery(String baseURL, JSONObject object, String endPoint) {
        RestAssured.baseURI = baseURL;
        RequestSpecification request = RestAssured.given();
        // Add a header stating the Request body is a JSON
        request.header(CONTENT_TYPE_PARAM_NAME, CONTENT_TYPE_JSON); // Add the Json to the body of the request
        request.body(object.toJSONString()); // Post the request and check the response
        Response response = request.post(endPoint);
        return response;
    }
}