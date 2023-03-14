package project.testcases.steps;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import project.models.Post;
import project.projectutils.ProjectApiUtil;
import project.models.User;
import restframework.universalutils.ConfigUtil;
import restframework.universalutils.JsonUtil;

/**
 * @author Pavel Romanov 06.03.2023
 */

@Slf4j
public class Steps {
    private static final String URL_PARAM_NAME = "url";
    private static final String url = ConfigUtil.getConfProperty(URL_PARAM_NAME);

    public static void checkMessageBodySorted(String sortAttr, String endPoint, int expectedStatusCode) {
        log.info("Проверяем, что сообщения сортируются по возрастанию и код состояния запроса.");
        Assert.assertTrue(ProjectApiUtil.isBodySorted(url + endPoint, sortAttr, expectedStatusCode),
                "Сообщения не отсортированы по возрастанию.");
    }

    public static void checkMessageParams(int userId, int id, String endPoint, int expectedStatusCode) {
        log.info("Проверяем параметры сообщения и код состояния запроса.");
        Post post = ProjectApiUtil.getPost(url + endPoint, expectedStatusCode);
        Assert.assertTrue(((post.getId() == id) && (post.getUserId() == userId) && (!post.getBody().isEmpty()
                && (!post.getTitle().isEmpty()))), "Информация о сообщении неверна.");
    }

    public static void checkEmptyBody(String emptyCheck, String endPoint, int expectedStatusCode) {
        log.info("Проверяем, тело ответа пустое и код состояния запроса.");
        Assert.assertEquals(ProjectApiUtil.getStringBody(url + endPoint, expectedStatusCode), emptyCheck,
                "Тело ответа не пустое");
    }

    public static void checkJsonContentType(String endPoint, int expectedStatusCode) {
        log.info("Проверяем корректность типа списка в теле ответа.");
        Assert.assertTrue(JsonUtil.isContentTypeJson(ProjectApiUtil.getStringBody(url + endPoint, expectedStatusCode)),
                "Список в теле ответа неверного формата.");
    }

    public static void checkPostRequest(String endPoint, Post postToSend, int expectedStatusCode) {
        log.info("Создаем новое сообщение и проверяем код состояния запроса.");
        Post actualPost = ProjectApiUtil.getSentPost(url + endPoint, postToSend, expectedStatusCode);
        Assert.assertTrue(((!actualPost.getId().equals(null) && actualPost.getBody().equals(postToSend.getBody()))
                && (actualPost.getTitle().equals(postToSend.getTitle())) &&
                        (actualPost.getUserId().equals(postToSend.getUserId()))), "Информация в сообщении неверна.");
    }

    public static void checkUsersEqual(String endPoint, User user, int expectedStatusCode) {
        log.info("Проверяем корректность параметров пользователя и код состояния запроса.");
        Assert.assertEquals(ProjectApiUtil.getUser(url + endPoint, expectedStatusCode), user,
                "Ожидаемые и фактические пользовательские данные не совпадают.");
    }
}
