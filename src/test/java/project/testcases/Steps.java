package project.testcases;

import org.testng.Assert;
import project.apiutil.APIManager;
import project.endpoints.Endpoints;
import project.models.User;
import universaltools.ConfigUtil;

/**
 * @author Pavel Romanov 06.03.2023
 */

public class Steps {
    private static final String URL_PARAM_NAME = "url";
    private static final String url = ConfigUtil.getConfProperty(URL_PARAM_NAME);

    public static void checkStatus(String statusCode, String endPoint) {
        Assert.assertEquals(APIManager.getStatus(url + endPoint), Integer.valueOf(statusCode),
                "Код состояния некорректен.");
    }

    public static void checkContentType(String contentType, String endPoint) {
        Assert.assertTrue(APIManager.isContentTypeCorrect(url + endPoint, contentType),
                "Список в теле ответа неверного формата.");
    }

    public static void checkMessageSorting(String sortAttr, String endPoint) {
        Assert.assertTrue(APIManager.isBodySorted(url + endPoint, sortAttr),
                "Сообщения не отсортированы по возрастанию.");
    }

    public static void checkMessageParams(String userId, String id, String endPoint) {
        Assert.assertTrue(APIManager.isMessageCorrect(url + endPoint, Integer.valueOf(userId), Integer.valueOf(id)),
                "Информация о сообщении неверна.");
    }

    public static void checkEmptyBody(String emptyCheck, String endPoint) {
        Assert.assertTrue(APIManager.isResponseBodyEmpty(url + endPoint, emptyCheck), "Тело ответа не пустое");
    }

    public static void sendPostRequest(String userId, String endPoint, String statusCode) {
        Assert.assertEquals(APIManager.createMessage(userId, url, endPoint), statusCode);
    }

    public static void checkPostResponse() {
        Assert.assertTrue(APIManager.isResponseCorrect());
    }

    public static void checkUsersEqual(String endPoint, User user) {
        Assert.assertTrue(APIManager.isUsersEqual(url + endPoint, user));
    }
}
