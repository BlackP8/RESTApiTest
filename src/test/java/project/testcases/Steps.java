package project.testcases;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import project.apiutil.APIManager;
import project.models.User;
import universaltools.ConfigUtil;

/**
 * @author Pavel Romanov 06.03.2023
 */

@Slf4j
public class Steps {
    private static final String URL_PARAM_NAME = "url";
    private static final String url = ConfigUtil.getConfProperty(URL_PARAM_NAME);

    public static void checkOkStatus(String endPoint) {
        log.info("Проверяем код состояния OK.");
        Assert.assertEquals(APIManager.getStatus(url + endPoint), Integer.valueOf(HttpStatus.SC_OK),
                "Код состояния некорректен.");
    }

    public static void checkNotFoundStatus(String endPoint) {
        log.info("Проверяем код состояния Not Found.");
        Assert.assertEquals(APIManager.getStatus(url + endPoint), Integer.valueOf(HttpStatus.SC_NOT_FOUND),
                "Код состояния некорректен.");
    }

    public static void checkContentType(String endPoint) {
        log.info("Проверяем корректность типа списка в теле ответа.");
        Assert.assertTrue(APIManager.isContentTypeJSON(url + endPoint),
                "Список в теле ответа неверного формата.");
    }

    public static void checkMessageSorting(String sortAttr, String endPoint) {
        log.info("Проверяем, что сообщения сортируются по возрастанию.");
        Assert.assertTrue(APIManager.isBodySorted(url + endPoint, sortAttr),
                "Сообщения не отсортированы по возрастанию.");
    }

    public static void checkMessageParams(String userId, String id, String endPoint) {
        log.info("Проверяем параметры сообщения.");
        Assert.assertTrue(APIManager.isMessageCorrect(url + endPoint, Integer.valueOf(userId), Integer.valueOf(id)),
                "Информация о сообщении неверна.");
    }

    public static void checkEmptyBody(String emptyCheck, String endPoint) {
        log.info("Проверяем, тело ответа пустое.");
        Assert.assertTrue(APIManager.isResponseBodyEmpty(url + endPoint, emptyCheck), "Тело ответа не пустое");
    }

    public static void sendPostRequest(String userId, String endPoint) {
        log.info("Создаем новое сообщение и проверяем код состояния запроса.");
        Assert.assertEquals(APIManager.createMessage(userId, url, endPoint), Integer.valueOf(HttpStatus.SC_CREATED));
    }

    public static void checkPostResponse() {
        log.info("Проверяем корректность параметров добавленного сообщения.");
        Assert.assertTrue(APIManager.isPostResponseCorrect());
    }

    public static void checkUsersEqual(String endPoint, User user) {
        log.info("Проверяем корректность параметров пользователя.");
        Assert.assertTrue(APIManager.isUsersEqual(url + endPoint, user));
    }
}
