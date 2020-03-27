package helperClasses;

import com.sun.xml.xsom.impl.util.Uri;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static helperClasses.BaseTestClass.createDriver;
import static helperClasses.SeleniumUtil.*;
import static helperClasses.TestProperty.*;
import static helperClasses.UtilityMethods.*;

public class DriverFactory {
    /**
     * This String contains the value of the browser needed to create the
     * required driver instance
     */
    /** Webdriver instance to create a webdriver */







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


    /**
     * This method opens the test URL and sets the driver properties
     */




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



    public static String encodeScreenshotBase64(String imgPath) {
        String base64Img = "";

        File inpFile = new File(imgPath);

        try(FileInputStream imgInFile = new FileInputStream(inpFile)) {
            byte[] imgData = new byte[(int) inpFile.length()];
            imgInFile.read(imgData);
            base64Img = Base64.getEncoder().encodeToString(imgData);

        } catch (FileNotFoundException e) {
            logError("The File is not found, in the embedScreenShotBase64 method of the ..");
        } catch (IOException ioe) {
            logError("The file is not readable, screenshot will not be available" + ioe );
        }

        logStringIntoConsole("Encode Screenshot: " + imgPath);
        return base64Img;
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
