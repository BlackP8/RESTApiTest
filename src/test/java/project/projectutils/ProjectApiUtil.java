package project.projectutils;

import io.restassured.response.Response;
import project.models.Post;
import project.models.User;
import restframework.FrameApiUtil;
import restframework.universalutils.JsonUtil;
import restframework.universalutils.SortUtil;
import java.util.stream.Collectors;

/**
 * Класс-утилита для работы с результатами GET и POST запросов в рамках тест-кейсов задания
 * @author Pavel Romanov 02.03.2023
 */

public class ProjectApiUtil {
    /**
     * Метод, проверяющий, что данные внутри тела ответа отсортированы по требуемому параметру
     * @param url
     * @param sortAttr
     */
    public static boolean isBodySorted(String url, String sortAttr, int expectedStatusCode) {
        Response getResponse = FrameApiUtil.get(url);
        checkStatusCode(getResponse, expectedStatusCode);
        return SortUtil.isResponseSorted(getResponse.jsonPath().getList(sortAttr).stream()
                .map(Object::toString).collect(Collectors.toUnmodifiableList()));
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
     */
    public static Post getPost(String url, int expectedStatusCode) {
        Response getResponse = FrameApiUtil.get(url);
        Post post = getResponse.getBody().as(Post.class);
        checkStatusCode(getResponse, expectedStatusCode);
        return post;
    }

    /**
     * Метод для получения пользователя из GET запроса
     * @param url
     */
    public static User getUser(String url, int expectedStatusCode) {
        Response getResponse = FrameApiUtil.get(url);
        User user = getResponse.getBody().as(User.class);
        checkStatusCode(getResponse, expectedStatusCode);
        return user;
    }

    /**
     * Метод для получения поста, отправленного в POST запросе
     * @param url
     * @param postToSend
     * @param expectedStatusCode
     */
    public static Post getSentPost(String url, Post postToSend, int expectedStatusCode) {
        Response postResponse = FrameApiUtil.post(url, JsonUtil.getJsonFromModel(postToSend));
        Post actualPost = postResponse.getBody().as(Post.class);
        checkStatusCode(postResponse, expectedStatusCode);
        return actualPost;
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
