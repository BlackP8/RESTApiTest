package project.base;

import org.testng.annotations.BeforeMethod;
import universaltools.ConfigUtil;

/**
 * @author Pavel Romanov 13.02.2023
 */

public abstract class BaseTest {
    @BeforeMethod
    public void setup() {
        ConfigUtil.setConfig();
    }
}
