package project.testcases.steps;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.testng.Assert;
import project.models.Post;
import project.projectutils.ProjectApiUtil;
import project.models.User;
import restframework.universalutils.ConfigUtil;
import restframework.universalutils.JsonUtil;
import restframework.universalutils.SortUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pavel Romanov 06.03.2023
 */

@Slf4j
public class Steps {
    private static final String URL_PARAM_NAME = "url";
    private static final String url = ConfigUtil.getConfProperty(URL_PARAM_NAME);

    public static void checkMessageBodySorted(List<String> listToCheck) {
        log.info("Проверяем, что сортировку ответа и код состояния запроса.");
        Assert.assertTrue(SortUtil.isResponseSorted(listToCheck), "Сообщения не отсортированы по возрастанию.");
    }

    public static List<String> getListOfPostId(String endPoint, int expectedStatusCode) {
        return ProjectApiUtil.getBodyAsList(url + endPoint, expectedStatusCode,
                Post.class).stream().map(Post::getId).collect(Collectors.toList());
    }

    public static void checkMessageParams(String userId, String id, Post post) {
        log.info("Проверяем параметры сообщения и код состояния запроса.");
        Assert.assertTrue(((post.getId().equals(id)) && (post.getUserId().equals(userId)) && (!post.getBody().isEmpty()
                && (!post.getTitle().isEmpty()))), "Информация о сообщении неверна.");
    }

    public static Post getActualPost(String endPoint, int expectedStatusCode) {
        log.info("Получаем информацию о сообщении.");
        return ProjectApiUtil.getPost(url + endPoint, expectedStatusCode);
    }

    public static void checkResponseBody(String body, String emptyCheck) {
        log.info("Проверяем тело ответа и код состояния запроса.");
        Assert.assertEquals(body, emptyCheck, "Тело ответа не пустое");
    }

    public static void checkJsonContentType(String body) {
        log.info("Проверяем, что тело ответа в формате JSON.");
        Assert.assertTrue(JsonUtil.isContentTypeJson(body), "Список в теле ответа неверного формата.");
    }

    public static String getResponseBody(String endPoint, int expectedStatusCode) {
        return ProjectApiUtil.getStringBody(url + endPoint, expectedStatusCode);
    }

    public static void checkPostParams(Post actualPost, Post postToSend) {
        log.info("Проверяем, что созданное сообщение соответствует нужным параметрам.");
        Assert.assertTrue(((!actualPost.getId().equals(null) && actualPost.getBody().equals(postToSend.getBody()))
                && (actualPost.getTitle().equals(postToSend.getTitle())) &&
                        (actualPost.getUserId().equals(postToSend.getUserId()))), "Информация в сообщении неверна.");
    }

    public static Post createPost(String endPoint, JSONObject postToSend, int expectedStatusCode) {
        log.info("Создаем новое сообщение и проверяем код состояния запроса.");
        return ProjectApiUtil.getSentPost(url + endPoint, postToSend, expectedStatusCode);
    }

    public static User getUserFromList(String endPoint, int expectedStatusCode, User expectedUser) {
        log.info("Получаем пользователя из cписка пользователей.");
        return ProjectApiUtil.getBodyAsList(url + endPoint, expectedStatusCode, User.class).stream()
                .filter(el -> el.getId().equals(expectedUser.getId())).collect(Collectors.toList()).get(0);
    }

    public static User getActualUser(String endPoint, int expectedStatusCode) {
        log.info("Получаем пользователя из запроса и код состояния запроса.");
        return ProjectApiUtil.getUser(url + endPoint, expectedStatusCode);
    }

    public static void checkUsersEqual(User actualUser, User expectedUser) {
        log.info("Проверяем корректность параметров пользователя и код состояния запроса.");
        Assert.assertEquals(actualUser, expectedUser, "Ожидаемые и фактические пользовательские данные не совпадают.");
    }
}
