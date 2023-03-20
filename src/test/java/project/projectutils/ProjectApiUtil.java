package project.projectutils;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import project.models.Post;
import project.models.User;
import restframework.FrameApiUtil;
import java.util.List;

/**
 * Класс-утилита для работы с результатами GET и POST запросов в рамках тест-кейсов задания
 * @author Pavel Romanov 02.03.2023
 */

public class ProjectApiUtil {
    /**
     * Метод, возвращающий список объектов из тела запроса
     * @param url
     * @param expectedStatusCode
     * @param type
     */
    public static <T> List<T> getBodyAsList(String url, int expectedStatusCode, Class<T> type) {
        Response getResponse = FrameApiUtil.get(url);
        checkStatusCode(getResponse, expectedStatusCode);
        return getResponse.jsonPath().getList("$", type);
    }

    /**
     * Метод, возвращающий тело GET запроса в виде строки
     * @param url
     * @param expectedStatusCode
     */
    public static String getStringBody(String url, int expectedStatusCode) {
        Response getResponse = FrameApiUtil.get(url);
        checkStatusCode(getResponse, expectedStatusCode);
        return getResponse.getBody().asString();
    }

    /**
     * Метод для получения поста из тела GET запроса
     * @param url
     * @param expectedStatusCode
     */
    public static Post getPost(String url, int expectedStatusCode) {
        Response getResponse = FrameApiUtil.get(url);
        checkStatusCode(getResponse, expectedStatusCode);
        return getResponse.getBody().as(Post.class);
    }

    /**
     * Метод для получения пользователя из GET запроса
     * @param url
     * @param expectedStatusCode
     */
    public static User getUser(String url, int expectedStatusCode) {
        Response getResponse = FrameApiUtil.get(url);
        checkStatusCode(getResponse, expectedStatusCode);
        return getResponse.getBody().as(User.class);
    }

    /**
     * Метод для получения поста, отправленного в POST запросе
     * @param url
     * @param postToSend
     * @param expectedStatusCode
     */
    public static Post getSentPost(String url, JSONObject postToSend, int expectedStatusCode) {
        Response postResponse = FrameApiUtil.post(url, postToSend);
        checkStatusCode(postResponse, expectedStatusCode);
        return postResponse.getBody().as(Post.class);
    }

    /**
     * Метод для проверки кода состояния запроса
     * @param response
     * @param expectedStatusCode
     */
    private static void checkStatusCode(Response response, int expectedStatusCode) {
        assert response.getStatusCode() == expectedStatusCode : "Код состояния некорректен.";
    }
}
