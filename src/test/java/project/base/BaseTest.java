package project.base;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import universaltools.ConfigUtil;

/**
 * @author Pavel Romanov 13.02.2023
 */

public abstract class BaseTest {
    private static final String URL_PARAM_NAME = "/url";
    private static final String STATUS_PARAM_NAME = "statusCode";

    @BeforeMethod
    public void setup(ITestContext context) {
        ConfigUtil.setConfig();
    }

//    @AfterMethod
//    public void quit() {
//        if (AqualityServices.isBrowserStarted()) {
//            browser.quit();
//        }
//    }
}
