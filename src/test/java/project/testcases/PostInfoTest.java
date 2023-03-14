package project.testcases;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import project.testcases.steps.Steps;
import restframework.universalutils.DataProviderUtil;

/**
 * @author Pavel Romanov 02.03.2023
 */

public class PostInfoTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "testData")
    public void attributesTest(String id, String userId) {
        String endpoint = String.format(Endpoints.POST_BY_ID.getStringValue(), id);
        Steps.checkMessageParams(Integer.valueOf(userId), Integer.valueOf(id), endpoint, HttpStatus.SC_OK);
    }
}
