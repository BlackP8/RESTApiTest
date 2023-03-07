package project.apiutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.models.Message;
import project.models.User;
import restframework.RequestManager;
import universaltools.DataGeneratorUtil;

import java.util.Iterator;
import java.util.List;

/**
 * Класс-утилита для работы с результатами GET и POST запросов в рамках тест-кейсов задания
 * @author Pavel Romanov 02.03.2023
 */

@Slf4j
public class APIManager {
    private static boolean result;
    private static final int PARAMS_LENGTH = 10;
    private static Response postResponse;
    private static Message message;

    /**
     * Метод для получения кода состояния GET запроса
     * @param url
     */
    public static Integer getStatus(String url) {
        return RequestManager.executeGetQuery(url).getStatusCode();
    }

    /**
     * Метод для проверки, что тело ответа имеет тип JSON
     * @param url
     */
    public static boolean isContentTypeJSON(String url) {
        result = false;
        if (RequestManager.executeGetQuery(url).getContentType().contains(ContentType.JSON.toString())) {
            result = true;
        }
        return result;
    }

    /**
     * Метод, проверяющий, что данные внутри тела ответа отсортированы по требуемому параметру
     * @param url
     * @param sortParameter
     */
    public static boolean isBodySorted(String url, String sortParameter) {
        result = false;
        List<Integer> jsonResponse = RequestManager.executeGetQuery(url).jsonPath().getList(sortParameter);
        Iterator<Integer> iterator = jsonResponse.iterator();
        Integer current, previous = iterator.next();

        while (iterator.hasNext()) {
            current = iterator.next();
            if (previous.compareTo(current) < 0) {
                result = true;
            }
            previous = current;
        }
        return result;
    }

    /**
     * Метод для получения тела ответа от GET запроса
     * @param url
     */
    private static ResponseBody getResponseBody(String url) {
        ResponseBody body = RequestManager.executeGetQuery(url).getBody();
        return body;
    }

    /**
     * Метод для получения сообщения из тела GET запроса
     * @param url
     */
    public static Message getMessage(String url) {
        Message message = getResponseBody(url).as(Message.class);
        return message;
    }

    /**
     * Метод для получения пользователя из GET запроса
     * @param url
     */
    public static User getUser(String url) {
        User user = getResponseBody(url).as(User.class);
        return user;
    }

    /**
     * Метод для сравнения пользотеля из тестовых данных и из опроса
     * @param url
     * @param expectedUser
     */
    public static boolean isUsersEqual(String url, User expectedUser) {
        return expectedUser.equals(getUser(url));
    }

    /**
     * Метод для сравнения параметров сообщения из тестовых данных и GET запроса
     * @param url
     * @param userId
     * @param id
     */
    public static boolean isMessageCorrect(String url, int userId, int id) {
        result = false;
        Message message = getMessage(url);
        if ((message.getId() == id) && (message.getUserId() == userId) && (!message.getBody().isEmpty()
        && (!message.getTitle().isEmpty()))) {
            result = true;
        }
        return result;
    }

    /**
     * Метод, проверяющий пустое ли тело запроса
     * @param url
     * @param empty
     */
    public static boolean isResponseBodyEmpty(String url, String empty) {
        return getResponseBody(url).asString().equals(empty);
    }

    /**
     * Метод для создания объекта сообщения и выполнения POST запроса
     * @param userId
     * @param url
     * @param endPoint
     */
    public static Integer createMessage(String userId, String url, String endPoint) {
        message = Message.builder().userId(Integer.valueOf(userId))
                .title(DataGeneratorUtil.generateRandomString(PARAMS_LENGTH))
                .body(DataGeneratorUtil.generateRandomString(PARAMS_LENGTH)).build();
        postResponse = null;
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();

        try {
            String jsonInString = mapper.writeValueAsString(message);
            JSONObject json = (JSONObject) parser.parse(jsonInString);
            postResponse = RequestManager.executePostQuery(url, json, endPoint);
        }
        catch (JsonProcessingException | ParseException e) {
            log.error(e.getMessage());
        }
        return postResponse.getStatusCode();
    }

    /**
     * Метод для проверки корректности POST запроса
     */
    public static boolean isPostResponseCorrect() {
        result = false;
        ResponseBody body = postResponse.getBody();
        if ((message.getTitle().equals(body.as(Message.class).getTitle())) &&
                (message.getBody().equals(body.as(Message.class).getBody())) &&
                        (message.getUserId().equals(body.as(Message.class).getUserId())) &&
                (!body.as(Message.class).getId().equals(null))) {
            result = true;
        }
        return result;
    }
}
