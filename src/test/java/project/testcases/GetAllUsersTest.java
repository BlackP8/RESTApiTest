package project.testcases;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import project.models.User;
import project.testcases.steps.Steps;
import restframework.universalutils.DataProviderUtil;

/**
 * @author Pavel Romanov 03.03.2023
 */

public class GetAllUsersTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "userData")
    public void allUsersTest(User user) {
        Steps.checkJsonContentType(Endpoints.ALL_USERS.getStringValue(), HttpStatus.SC_OK);
        Steps.checkUsersEqual(Endpoints.ALL_USERS.getStringValue() + user.getId(), user, HttpStatus.SC_OK);
    }
}
