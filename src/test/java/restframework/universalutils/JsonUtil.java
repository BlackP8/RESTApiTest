package restframework.universalutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Pavel Romanov 13.03.2023
 */

@Slf4j
public class JsonUtil {
    public static boolean isContentTypeJson(String responseBody) {
        try {
            JSONArray json = (JSONArray) new JSONParser().parse(responseBody);
            return true;
        }
        catch (ParseException e) {
            return false;
        }
    }

    public static <T> JSONObject getJsonFromModel(T obj) {
        JSONObject json = null;
        try {
            String jsonInString = new ObjectMapper().writeValueAsString(obj);
            json = (JSONObject) new JSONParser().parse(jsonInString);
        }
        catch (JsonProcessingException | ParseException e) {
            log.error(e.getMessage());
        }
        return json;
    }
}
