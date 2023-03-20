package restframework.universalutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import project.models.User;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Pavel Romanov 03.03.2023
 */

@Slf4j
public class DataProviderUtil {
    private JSONObject jsonTestObject = null;
    private static String testFile = "test_file";

    @DataProvider(name = "testData")
    public Object[][] getData(ITestContext context) {
        String testParam = context.getCurrentXmlTest().getParameter(testFile);
        jsonTestObject = ConfigUtil.setTestData(testParam);
        HashMap<String, String> hashMap = new HashMap<>();
        if (jsonTestObject != null) {
            Set<String> jsonObjKeys = jsonTestObject.keySet();
            for (String jsonObjKey: jsonObjKeys) {
                hashMap.put(jsonObjKey, (String) jsonTestObject.get(jsonObjKey));
            }
        }
        else {
            log.error("Тестовые данные не определены.");
        }
        String[] testData = hashMap.values().toArray(new String[hashMap.size()]);
        Object[][] data = new Object[1][testData.length];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < testData.length; j++) {
                data[i][j] = testData[j];
            }
        }
        return data;
    }

    @DataProvider(name = "userData")
    public Object[][] getUserData(ITestContext context) {
        String testParam = context.getCurrentXmlTest().getParameter(testFile);
        jsonTestObject = ConfigUtil.setTestData(testParam);
        String userString = jsonTestObject.toJSONString();
        User user = null;
        try {
            user = new ObjectMapper().readValue(userString, User.class);
        }
        catch (JsonProcessingException e) {
            log.error("Ошибка конвертации объекта JSON.");
        }

        return new Object[][] {new Object[] {user}};
    }
}
