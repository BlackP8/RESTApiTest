package project.testcases;

import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import universaltools.DataProviderUtil;

/**
 * @author Pavel Romanov 06.03.2023
 */

public class CreateMessageTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "testData")
    public void postRequestTest(String userId, String statusCode) {
        Steps.sendPostRequest(userId, Endpoints.POSTS.getStringValue(), statusCode);
        Steps.checkPostResponse();
    }
}
