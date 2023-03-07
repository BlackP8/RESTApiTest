package project.testcases;

import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import project.models.User;
import universaltools.DataProviderUtil;

/**
 * @author Pavel Romanov 03.03.2023
 */

public class GetAllUsersTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "userData")
    public void allUsersTest(User user) {
        Steps.checkOkStatus(Endpoints.USERS.getStringValue());
        Steps.checkContentType(Endpoints.USERS.getStringValue());
        Steps.checkUsersEqual(Endpoints.USERS.getStringValue() + user.getId(), user);
    }
}
