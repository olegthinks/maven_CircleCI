package tests;

import HelperClasses.DriverFactory;
import HelperClasses.BaseTestClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static HelperClasses.SeleniumUtil.logStringIntoConsole;
import static HelperClasses.TestProperty.URL;

public class TraditionalTests extends BaseTestClass {
    @BeforeTest
    public void setUp() {
        DriverFactory.openURL(URL);
    }

    @Test
    public void logIn() {
        logStringIntoConsole("test");
    }

}
