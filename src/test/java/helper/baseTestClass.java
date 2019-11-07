package helper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static helper.DriverFactory.closeAllBrowsers;
import static helper.SeleniumUtil.logStringIntoConsole;

/**
 * TESTS
 *
 1. BA - Program configuration - create/update
 2. BA - Sponsor Configuration - create/update
 3. CSR, Health Coach - IHA Assessment for single condition -  smoking cessation
 4. Health Coach - Baseline Assessment(Care plan as well)
 5. Health Coach - Follow up Assessment
 6. CSR, Health Coach - Fulfillment request
 7. Clinical Assistant - Research task
 8. Member 360 - Demographic update.
 9. Member Enrollment, Disenrollment & Reinstatement - includes batch process etc...
 10. Unable to Locate
 */

public class baseTestClass {
    long classStartTime;
    long classEndTime;
    long classDurationInSeconds;
    long testStartTime;
    long testEndTime;
    long testDurationInSeconds;
    long methodStartTime;
    long methodEndTime;
    long methodDurationInSeconds;

    private static Logger logger = LogManager.getLogger(baseTestClass.class);

    @BeforeGroups("smoke")
    public void _startSmokeTests() {
        logger.info("Starting the SMOKE TEST run...");
    }


    @BeforeTest()
    public void _ensureNoPreviousSessionsOfWebDriver() {
        //closeAllBrowsers();

        testStartTime = System.nanoTime();
    }


    @BeforeMethod()
    public void _getTestDetails(ITestContext ctx, Method method) {
        logStringIntoConsole("****************************");
        logStringIntoConsole("******* TEST DETAILS *******");

        String testName = ctx.getCurrentXmlTest().getName();
        logStringIntoConsole("Test Name: " + testName);

        String methodName = method.getName();
        logStringIntoConsole("Method: " + methodName);

        logStringIntoConsole("****************************");
        logStringIntoConsole("****************************");


        classStartTime = System.nanoTime();
        methodStartTime = System.nanoTime();
    }


    @AfterMethod
    public void _afterMethod(ITestResult result, Method method, ITestContext ctx) {
        methodEndTime = System.nanoTime();
        long durationInNano = (methodEndTime - methodStartTime);
        methodDurationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNano);
        String durationInSecondsString = methodDurationInSeconds + "s";
        logStringIntoConsole("Method Duration: " + durationInSecondsString);

        //Get method name for mapping.
        String methodName = method.getName();



        logStringIntoConsole("### Test Method Complete: " + DateUtil.getFormattedCurrentDateTime());
    }

    @AfterClass
    public void _afterClass(ITestContext ctx) {
        classEndTime = System.nanoTime();
        long durationInNano = (classEndTime - classStartTime);
        classDurationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNano);
        String durationInSecondsString = classDurationInSeconds + "s";
        logStringIntoConsole("Class Duration: " + durationInSecondsString);

        //Get class path for mapping.
        String classPath = this.getClass().getName();



        closeAllBrowsers();
    }

    protected boolean hasClassFailedTests(ITestContext context) {
        Class clazz = this.getClass();
        return context.getFailedTests().getAllMethods().stream().anyMatch(it ->
                it.getRealClass().equals(clazz));
    }

    @AfterTest
    public void _testLogging(ITestContext ctx) {
        testEndTime = System.nanoTime();
        long durationInNano = (testEndTime - testStartTime);
        testDurationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNano);
        String durationInSecondsString = testDurationInSeconds + "s";
        logStringIntoConsole("Test Duration: " + durationInSecondsString);


        String testName = ctx.getCurrentXmlTest().getName();
    }


    @AfterGroups("smoke")
    public void _teardownSmokeTests() {
        logger.info("Completed the SMOKE TEST run...");
    }
}

