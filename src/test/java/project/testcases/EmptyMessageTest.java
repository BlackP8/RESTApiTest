package project.testcases;

import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import universaltools.DataProviderUtil;

/**
 * @author Pavel Romanov 03.03.2023
 */

public class EmptyMessageTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "testData")
    public void checkEmptyMessage(String emptyCheck, String id) {
        Steps.checkNotFoundStatus(Endpoints.POSTS.getStringValue() + id);
        Steps.checkEmptyBody(emptyCheck, Endpoints.POSTS.getStringValue() + id);
    }
}
