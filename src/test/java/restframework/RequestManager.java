package restframework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

/**
 * Универсальный класс для выполнения GET и POST запросов и получения их результата
 * @author Pavel Romanov 01.03.2023
 */

public class RequestManager {
    private static final String CONTENT_TYPE_PARAM_NAME = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * Метод для выполнения GET запроса и получения результата
     * @param baseURL
     */
    public static Response executeGetQuery(String baseURL) {
        RestAssured.baseURI = baseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();
        return response;
    }

    /**
     * Метод для выполнения POST запроса и получения результата
     * @param baseURL
     * @param object
     * @param endPoint
     */
    public static Response executePostQuery(String baseURL, JSONObject object, String endPoint) {
        RestAssured.baseURI = baseURL;
        RequestSpecification request = RestAssured.given();
        request.header(CONTENT_TYPE_PARAM_NAME, CONTENT_TYPE_JSON);
        request.body(object.toJSONString());
        Response response = request.post(endPoint);
        return response;
    }
}
