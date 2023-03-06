package project.apiutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
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
 * @author Pavel Romanov 02.03.2023
 */

@Slf4j
public class APIManager {
    private static boolean result;
    private static final int PARAMS_LENGTH = 10;
    private static Response response;
    private static Message message;

    public static Integer getStatus(String url) {
        return RequestManager.executeGetQuery(url).getStatusCode();
    }

    public static boolean isContentTypeCorrect(String url, String expectType) {
        result = false;
        if (RequestManager.executeGetQuery(url).getContentType().contains(expectType)) {
            result = true;
        }
        return result;
    }

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

    private static ResponseBody getResponseBody(String url) {
        ResponseBody body = RequestManager.executeGetQuery(url).getBody();
        return body;
    }

    public static Message getMessage(String url) {
        Message message = getResponseBody(url).as(Message.class);
        return message;
    }

    public static User getUser(String url) {
        User user = getResponseBody(url).as(User.class);
        return user;
    }

    public static boolean isUsersEqual(String url, User expectedUser) {
        return expectedUser.equals(getUser(url));
    }

    public static boolean isMessageCorrect(String url, int userId, int id) {
        result = false;
        Message message = getMessage(url);
        if ((message.getId() == id) && (message.getUserId() == userId) && (!message.getBody().isEmpty()
        && (!message.getTitle().isEmpty()))) {
            result = true;
        }
        return result;
    }

    public static boolean isResponseBodyEmpty(String url, String empty) {
        return getResponseBody(url).asString().equals(empty);
    }

    public static Integer createMessage(String userId, String url, String endPoint) {
        message = Message.builder().userId(Integer.valueOf(userId))
                .title(DataGeneratorUtil.generateRandomString(PARAMS_LENGTH))
                .body(DataGeneratorUtil.generateRandomString(PARAMS_LENGTH)).build();
        Response response = null;
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        try {
            String jsonInString = mapper.writeValueAsString(message);
            JSONObject json = (JSONObject) parser.parse(jsonInString);
            response = RequestManager.executePostQuery(url, json, endPoint);
        }
        catch (JsonProcessingException | ParseException e) {
            log.error(e.getMessage());
        }
        return response.getStatusCode();
    }

    public static boolean isResponseCorrect() {
        ResponseBody body = response.getBody();
        return message.equals(body.as(Message.class));
    }
}
