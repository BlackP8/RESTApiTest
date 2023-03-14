package project.testcases;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import project.testcases.steps.Steps;
import restframework.universalutils.DataProviderUtil;

/**
 * @author Pavel Romanov 03.03.2023
 */

public class EmptyPostTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "testData")
    public void checkEmptyMessage(String emptyCheck, String id) {
        String endpoint = String.format(Endpoints.POST_BY_ID.getStringValue(), id);
        Steps.checkEmptyBody(emptyCheck, endpoint, HttpStatus.SC_NOT_FOUND);
    }
}
