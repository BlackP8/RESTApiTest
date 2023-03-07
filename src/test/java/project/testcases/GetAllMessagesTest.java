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
    public void getMessages(String sortAttr) {
        Steps.checkOkStatus(Endpoints.POSTS.getStringValue());
        Steps.checkContentType(Endpoints.POSTS.getStringValue());
        Steps.checkMessageSorting(sortAttr, Endpoints.POSTS.getStringValue());
    }
}
