package restframework.universalutils;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Pavel Romanov 27.02.2023
 */

@Slf4j
public class ConfigUtil {
    private static final String PATH_TO_CONFIG_FILE = "src/test/resources/config.json";
    private static JSONObject jsonConfObject;
    private static JSONObject jsonTestObject;

    public static void setConfig() {
        jsonConfObject = setJSONObject(jsonConfObject, PATH_TO_CONFIG_FILE);
    }

    public static JSONObject setTestData(String filePath) {
        return setJSONObject(jsonTestObject, filePath);
    }

    private static JSONObject setJSONObject(JSONObject object, String pathToFile) {
        try(BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            object = (JSONObject) new JSONParser().parse(reader);
        }
        catch (IOException | ParseException e) {
            log.error(e.getMessage());
        }
        return object;
    }

    public static String getConfProperty(String key) {
        return (String) jsonConfObject.get(key);
    }
}
