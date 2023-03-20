package restframework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

/**
 * Универсальный класс для выполнения GET и POST запросов и получения их результата
 * @author Pavel Romanov 01.03.2023
 */

public class FrameApiUtil {
    private static final String CONTENT_TYPE_PARAM_NAME = "Content-Type";

    /**
     * Метод для выполнения GET запроса и получения результата
     * @param url
     */
    public static Response get(String url) {
        Response response = RestAssured.given().get(url);
        return response;
    }

    /**
     * Метод для выполнения POST запроса и получения результата
     * @param url
     * @param object
     */
    public static Response post(String url, JSONObject object) {
        Response response = RestAssured.given().header(CONTENT_TYPE_PARAM_NAME, ContentType.JSON)
                .body(object.toJSONString()).post(url);
        return response;
    }
}
