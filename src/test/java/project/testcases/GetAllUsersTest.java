package project.testcases;

import org.testng.annotations.Parameters;
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
    @Parameters({"statusCode", "type"})
    public void allUsersTest(User user, String statusCode, String type) {
        Steps.checkStatus(statusCode, Endpoints.USERS.getStringValue());
        Steps.checkContentType(type, Endpoints.USERS.getStringValue());
        Steps.checkUsersEqual(Endpoints.USERS.getStringValue() + user.getId(), user);
    }
}
