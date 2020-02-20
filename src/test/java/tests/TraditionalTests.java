package tests;

import helperClasses.DriverFactory;
import helperClasses.BaseTestClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static helperClasses.DriverFactory.openURL;
import static helperClasses.SeleniumUtil.*;
import static helperClasses.TestProperty.URL;

public class TraditionalTests extends BaseTestClass {
    @BeforeTest
    public void setUp() {
        openURL(URL);

    }

    @Test
    public void validateLoaded() {
        waitUntilLoaded();
    }

}
