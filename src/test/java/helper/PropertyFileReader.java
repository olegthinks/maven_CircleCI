package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static jdk.internal.net.http.common.Log.logError;

public class PropertyFileReader {
    /** Logger to log the Driver Factory log messages */
    public String returnPropVal(final String propertyFileName, final String key) {

        // get a new properties object:
        final Properties properties = new Properties();
        String value = null;
        {
            try {

                //properties.load(new FileInputStream(new File("../config/"+ propertyFileName + ".properties")));
                properties.load(new FileInputStream(new File(System.getProperty("user.dir")+ "/src/test/resources/automation.properties")));


                // get PROPERTY value based on key:
                value = properties.getProperty(key);

            } catch (final FileNotFoundException e) {
                logError("The file was not found at "+"/src/test/resouces"+ propertyFileName + ".properties", e);

            } catch (final IOException e) {
                logError("IOException was found in returnPropVal method", e);
            }
        }
        return value;
    }

    /**
     *
     * @param propertyFileName
     * @return A PROPERTY File containing automation key value pair
     *
     */
    public Properties returnProperties(final String propertyFileName) {

        // get a new properties object:
        Properties properties = new Properties();
        {
            try {
                //properties.load(new FileInputStream(new File("../config/"+ propertyFileName + ".properties")));
                properties.load(new FileInputStream(new File(System.getProperty("user.dir")+ "/src/test/resources/automation.properties")));

            } catch (final FileNotFoundException e) {
                //logError("The file was not found at " + "../config/"+ propertyFileName + ".properties", e);
                logError("The file was not found at " + "/src/test/resources"+ propertyFileName + ".properties", e);

            } catch (final IOException e) {
                logError("IOException was found in returnProperties method", e);
            }
        }
        return properties;
    }
}
