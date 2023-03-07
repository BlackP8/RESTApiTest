package project.testcases;

import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import project.models.User;
import universaltools.DataProviderUtil;

/**
 * @author Pavel Romanov 06.03.2023
 */

public class UserInfoTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "userData")
    public void testUserInfo(User user) {
        Steps.checkOkStatus(Endpoints.USERS.getStringValue());
        Steps.checkUsersEqual(Endpoints.USERS.getStringValue() + user.getId(), user);
    }
}
