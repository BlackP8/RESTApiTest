package project.testcases;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import project.models.User;
import project.testcases.steps.Steps;
import restframework.universalutils.DataProviderUtil;

/**
 * @author Pavel Romanov 06.03.2023
 */

public class UserInfoTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "userData")
    public void testUserInfo(User user) {
        String endpoint = String.format(Endpoints.USER_BY_ID.getStringValue(), user.getId());
        User actualUser = Steps.getActualUser(endpoint, HttpStatus.SC_OK);
        Steps.checkUsersEqual(actualUser, user);
    }
}
