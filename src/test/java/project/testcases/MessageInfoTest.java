package project.testcases;

import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import universaltools.DataProviderUtil;

/**
 * @author Pavel Romanov 02.03.2023
 */

public class MessageInfoTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "testData")
    public void attributesTest(String id, String userId, String statusCode) {
        Steps.checkStatus(statusCode, Endpoints.POSTS.getStringValue() + id);
        Steps.checkMessageParams(userId, id, Endpoints.POSTS.getStringValue() + id);
    }
}
