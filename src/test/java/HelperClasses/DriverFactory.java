package HelperClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static HelperClasses.SeleniumUtil.*;
import static HelperClasses.TestProperty.*;
import static HelperClasses.UtilityMethods.doesStringContainSomeText;

public class DriverFactory {
    /**
     * This String contains the value of the browser needed to create the
     * required driver instance
     */
    /** Webdriver instance to create a webdriver */
    public static WebDriver driver;



    public DriverFactory(WebDriver driver){
        DriverFactory.driver = driver;
    }
    /**
     * This method is called by the test classes to create a driver
     public static void getDriverInstance() {
     createDriver();
     logStringIntoConsole("Initialized the WebDriver");
     }*/


    /**
     * This is the Factory Method used for creating appropriate web driver
     *

     */
    public static void createDriver() {
        logStringIntoConsole("**********************************************");
        logStringIntoConsole("***********************");
        logStringIntoConsole("**********************************************");
        logStringIntoConsole("***********************");
        logStringIntoConsole("|***| Browser: " + BROWSER);


        if (doesStringContainSomeText(BROWSER, "firefox")) {
            logStringIntoConsole("Creating the instance of FIREFOX DRIVER...");
            driver = new FirefoxDriver();

        } else if(doesStringContainSomeText(BROWSER, "chrome")) {
            logStringIntoConsole("Creating the instance of CHROME...");

            final File file = new File("C://Users//oandr//Documents//Source//webdrivers//chromedriver//chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.toString());

            DesiredCapabilities capability = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            logStringIntoConsole("***CHECKING WHETHER TO RUN IN HEADLESS MODE");
            if(HEADLESS.equalsIgnoreCase("true")) {
                logStringIntoConsole(HEADLESS);
                options.addArguments("headless");
                logStringIntoConsole("Argument 'headless' ADDED to ChromeOptions.");
            } else {
                logStringIntoConsole("Argument 'headless' SKIPPED.");
            }
            options.addArguments("test-type=browser");
            options.addArguments("--disable-web-security");
            options.addArguments("--no-proxy-server");

            //Window Size
            //options.addArguments("--window-size=904,768");
            options.addArguments("--start-maximized");

            options.addArguments("--ignore-certificate-errors");
            //options.addArguments("--incognito");
            options.addArguments("--enable-precise-memory-info");
            options.addArguments("--disable-geolocation");
            capability.setCapability("chrome.binary", file.getAbsolutePath());
            capability.setCapability(ChromeOptions.CAPABILITY, options);
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            capability.setCapability("chrome.binary", file.getAbsolutePath());
            capability.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new ChromeDriver();

        } else {
            try {
                throw new Exception(TestProperty.BROWSER +" NOT supported.  Choose either Firefox or Chrome.");
            } catch (Exception e) {
                logError("The requested browser is NOT supported! Choose either Firefox or Chrome.");
            }
        }
    }

    /**
     * This method opens the test URL and sets the driver properties
     */
    public static void openURL(String url) {
        createDriver();
        logStringIntoConsole("URL: " + url);

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(WAITING_TIME, TimeUnit.SECONDS);

        logStringIntoConsole("...completed OpenURL | " + getCurrentMethodName());
    }



    /**
     * This method appends current data parameter to the current test name for
     * naming the screenshot
     *
     * @param currTestName
     * @return String with the testname and date-time appended
     */
    public static String addDate(final String currTestName) {
        Date myDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yy-HH.mm.ss");
        return currTestName + "-" + sdf.format(myDate);
    }

    public static String addDate() {
        Date myDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yy-HH.mm.ss");
        return sdf.format(myDate);
    }



    /**
     * This innerclass is used for exception handling within the Driver Factory
     *
     * @author ravi.middha
     *
     */
    public static class UnsupportedBrowserError extends RuntimeException {
        private static final long serialVersionUID = 1L;

        /**
         * This method handles the Unsupported browser request
         *
         * @param message
         */
        public UnsupportedBrowserError(final String message) {
            super(message);
        }
    }

	/*@AfterClass(alwaysRun = true)
	public void cleanUpClass() {
		driver.close();
	}*/
    /**
     * This method is run at the end of the test suite
     */
	/*@AfterSuite (alwaysRun = true)
	public void suiteCleanup() {
		*//*logStringIntoConsole("Scheduling the Report to be sent via email");
		SendMailTLS.sendMail();
		ZipResultsFolders.zipResults();
		SendMailTLS.sendMail();
		logStringIntoConsole("Scheduled the Report to be sent via email and exiting the suite...");*//*
		logStringIntoConsole("*** Finished executing Suite *** Clean Up ***" + getCurrentMethodName());
	}*/

    public static void closeBrowser() {
        driver.close();
    }

    public static String encodeScreenshotBase64(String imgPath) {
        String base64Img = "";

        File inpFile = new File(imgPath);

        try(FileInputStream imgInFile = new FileInputStream(inpFile)) {
            byte[] imgData = new byte[(int) inpFile.length()];
            imgInFile.read(imgData);
            base64Img = Base64.getEncoder().encodeToString(imgData);

        } catch (FileNotFoundException e) {
            logError("The File is not found, in the embedScreenShotBase64 method of the driverFactory...");
        } catch (IOException ioe) {
            logError("The file is not readable, screenshot will not be available" + ioe );
        }

        logStringIntoConsole("Encode Screenshot: " + imgPath);
        return base64Img;
    }

    public static void setDriverToConfigWaitingTime() {
        driver.manage().timeouts().implicitlyWait(WAITING_TIME, TimeUnit.SECONDS);
    }

    public static void setDriverToConfigWaitingTime(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static void closeAllBrowsers() {
        logStringIntoConsole("......calling 'helper.SeleniumUtil.closeAllBrowsers' as test class completed.");

        try {
            if (!(driver == null)) {
                Set<String> handles = driver.getWindowHandles();
                Iterator<String> handleIt = handles.iterator();

                while (handleIt.hasNext()) {
                    String handle = handleIt.next();
                    driver.switchTo().window(handle);
                    DriverFactory.closeBrowser();
                }
            }

            //killChromeDriver();
        } catch (Exception e) {
        }
    }

    /**
     * Windows
     */
    public static void killChromeDriver() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows
        System.out.println();
        processBuilder.command("cmd.exe", "/c", "taskkill /F /IM chromedriver.exe /T");

        try {
            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
