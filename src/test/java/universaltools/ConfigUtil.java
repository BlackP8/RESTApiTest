package universaltools;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
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
    private static JSONParser parser = new JSONParser();
    private static JSONObject jsonConfObject;
    private static JSONObject jsonTestObject;
    private static JSONArray jsonArray;

    public static void setConfig() {
        try(BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_CONFIG_FILE))) {
            Object obj = parser.parse(reader);
            jsonConfObject = (JSONObject) obj;
        }
        catch (IOException | ParseException e) {
            log.error(e.getMessage());
        }
    }

    public static JSONObject setTestData(String filePath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Object obj = parser.parse(reader);
            jsonTestObject = (JSONObject)obj;
            return jsonTestObject;
        }
        catch (IOException | ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static JSONArray getArrayOfData(String filePath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Object obj = parser.parse(reader);
            jsonArray = (JSONArray) obj;
            return jsonArray;
        }
        catch (IOException | ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static String getConfProperty(String key) {
        return (String) jsonConfObject.get(key);
    }
}