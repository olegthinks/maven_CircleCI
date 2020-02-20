package tests;

import helperClasses.DriverFactory;
import helperClasses.BaseTestClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static helperClasses.SeleniumUtil.logStringIntoConsole;
import static helperClasses.TestProperty.URL;

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
