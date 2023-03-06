package project.testcases;

import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import universaltools.DataProviderUtil;

/**
 * @author Pavel Romanov 01.03.2023
 */
public class GetAllMessagesTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "testData")
    public void getMessages(String sortAttr, String contentType, String statusCode) {
        Steps.checkStatus(statusCode, Endpoints.POSTS.getStringValue());
        Steps.checkContentType(contentType, Endpoints.POSTS.getStringValue());
        Steps.checkMessageSorting(sortAttr, Endpoints.POSTS.getStringValue());
    }
}
