package helper;

import java.util.Properties;

import static com.sun.tools.doclint.Entity.prop;

public class TestProperty {
    //Setup
    final static PropertyFileReader prop = new PropertyFileReader();
    final static Properties propObj = prop.returnProperties("automation");

    public static final int WAITING_TIME =  Integer.parseInt(propObj.getProperty("WAITING_TIME"));
    public static final String BROWSER =  propObj.getProperty("BROWSER");
    public static final String HEADLESS =  propObj.getProperty("HEADLESS");

    public static final String URL =  propObj.getProperty("URL");
    public static final String USERNAME =  propObj.getProperty("USERNAME");
    public static final String APIKEY =  propObj.getProperty("APIKEY");
}
