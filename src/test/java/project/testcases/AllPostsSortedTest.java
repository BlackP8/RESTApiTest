package project.testcases;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import project.testcases.steps.Steps;
import restframework.universalutils.DataProviderUtil;

/**
 * @author Pavel Romanov 01.03.2023
 */

public class AllPostsSortedTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "testData")
    public void testPosts() {
        Steps.checkJsonContentType(Steps.getResponseBody(Endpoints.ALL_POSTS.getStringValue(), HttpStatus.SC_OK));
        Steps.checkMessageBodySorted(Steps.getListOfPostId(Endpoints.ALL_POSTS.getStringValue(), HttpStatus.SC_OK));
    }
}
