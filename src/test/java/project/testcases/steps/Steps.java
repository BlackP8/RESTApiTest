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
import java.util.stream.Collectors;

/**
 * @author Pavel Romanov 06.03.2023
 */

@Slf4j
public class Steps {
    private static final String URL_PARAM_NAME = "url";
    private static final String url = ConfigUtil.getConfProperty(URL_PARAM_NAME);

    public static void checkMessageBodySorted(String endPoint, int expectedStatusCode) {
        log.info("Проверяем, что сообщения сортируются по возрастанию по id и код состояния запроса.");
        Assert.assertTrue(SortUtil.isResponseSorted(ProjectApiUtil.getBodyAsList(url + endPoint, expectedStatusCode,
                                Post.class).stream().map(Post::getId).collect(Collectors.toList())),
                "Сообщения не отсортированы по возрастанию.");
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

    public static void checkEmptyBody(String emptyCheck, String endPoint, int expectedStatusCode) {
        log.info("Проверяем, что тело ответа пустое и код состояния запроса.");
        Assert.assertEquals(ProjectApiUtil.getStringBody(url + endPoint, expectedStatusCode), emptyCheck,
                "Тело ответа не пустое");
    }

    public static void checkJsonContentType(String endPoint, int expectedStatusCode) {
        log.info("Проверяем корректность типа списка в теле ответа.");
        Assert.assertTrue(JsonUtil.isContentTypeJson(ProjectApiUtil.getStringBody(url + endPoint, expectedStatusCode)),
                "Список в теле ответа неверного формата.");
    }

    public static void checkPostRequest(Post actualPost, Post postToSend) {
        log.info("Проверяем, что созданное сообщение соответствует нужным параметрам.");
        Assert.assertTrue(((!actualPost.getId().equals(null) && actualPost.getBody().equals(postToSend.getBody()))
                && (actualPost.getTitle().equals(postToSend.getTitle())) &&
                        (actualPost.getUserId().equals(postToSend.getUserId()))), "Информация в сообщении неверна.");
    }

    public static Post createPost(String endPoint, JSONObject postToSend, int expectedStatusCode) {
        log.info("Создаем новое сообщение и проверяем код состояния запроса.");
        return ProjectApiUtil.getSentPost(url + endPoint, postToSend, expectedStatusCode);
    }

    public static void checkUserFromList(String endPoint, int expectedStatusCode, User expectedUser) {
        log.info("Проверяем корректность параметров пользователя из списка и код состояния запроса.");
        Assert.assertEquals(ProjectApiUtil.getBodyAsList(url + endPoint, expectedStatusCode, User.class).stream()
                .filter(el -> el.getId().equals(expectedUser.getId())).collect(Collectors.toList()).get(0), expectedUser,
                "Ожидаемые и фактические пользовательские данные не совпадают.");
    }

    public static void checkUsersEqual(String endPoint, int expectedStatusCode, User expectedUser) {
        log.info("Проверяем корректность параметров пользователя и код состояния запроса.");
        Assert.assertEquals(ProjectApiUtil.getUser(url + endPoint, expectedStatusCode), expectedUser,
                "Ожидаемые и фактические пользовательские данные не совпадают.");
    }
}
