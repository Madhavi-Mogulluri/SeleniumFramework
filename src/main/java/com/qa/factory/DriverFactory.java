package com.qa.factory;

import com.qa.exceptions.BrowserException;
import com.qa.exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class DriverFactory {

    WebDriver driver;
    Properties prop;
    OptionsManager optionsManager;
    public static String highlight;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

  public static Logger log = LogManager.getLogger(DriverFactory.class);

    public WebDriver initializeDriver(Properties prop){

        String browserName = prop.getProperty("browser");
        System.out.println("Browser name " + browserName);
        optionsManager = new OptionsManager(prop);
        highlight = prop.getProperty("highlight");
        switch (browserName.trim().toLowerCase()){

            case "chrome" :
                tlDriver.set(new ChromeDriver(optionsManager.chromeOptions()));
                break;
            case "edge" :
                tlDriver.set(new EdgeDriver(optionsManager.edgeOptions()));
                break;
            case "firefox" :
                tlDriver.set(new FirefoxDriver(optionsManager.firefoxOptions()));
                break;
            case "safari" :
                tlDriver.set(new SafariDriver());
                break;
            default :
            log.warn("Please pass the right browser name " + browserName);
              throw new BrowserException("invalid browser" + browserName);

        }
        getDriver().get(prop.getProperty("url"));
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        return getDriver();

    }


    public static WebDriver getDriver(){
        return tlDriver.get();
    }

    /**
     * This method will initialize the properties
     * @return properties loaded in it
     */
    public Properties initProperties()  {
        FileInputStream fis = null;
        prop = new Properties();
       String envName= System.getProperty("env");

        try {
            if (envName == null) {
                System.out.println("evn is null hence running testcases on qa");
                fis = new FileInputStream("./src/test/resources/Config/qa.config.properties");
            } else {

                switch (envName.toLowerCase().trim()) {

                    case "qa":
                        log.info("running testcases on environment "+envName);
                        fis = new FileInputStream("./src/test/resources/Config/qa.config.properties");
                        break;
                    case "dev":
                        log.info("running testcases on environment "+envName);
                        fis = new FileInputStream("./src/test/resources/Config/dev.config.properties");
                        break;
                    case "stage":
                        log.info("running testcases on environment "+envName);
                        fis = new FileInputStream("./src/test/resources/Config/stage.config.properties");
                        break;
                    case "uat":
                        log.info("running testcases on environment "+envName);
                        fis = new FileInputStream("./src/test/resources/Config/uat.config.properties");
                        break;
                    case "prod":
                        log.info("running testcases on environment "+envName);
                        fis = new FileInputStream("./src/test/resources/Config/Config.properties");
                        break;
                    default:
                        log.warn("INVALID ENV NAME");
                        throw new FrameworkException("====INVALID ENV NAME=====" + envName);

                }
            }
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }

        try{
            prop.load(fis);
            log.info(prop);
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    return prop;
    }
    
    
    
    
    
    public static File takeScreenshotAsFile(){
      File srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        return srcfile;
    }

    public static byte[] takeScreenshotAsByte(){
        TakesScreenshot ts = (TakesScreenshot) tlDriver.get();
        byte[] f = ts.getScreenshotAs(OutputType.BYTES);
        return f;
    }

    public static String takeScreenshotAsBase64(){
        TakesScreenshot ts = (TakesScreenshot) tlDriver.get();
        String image  = ts.getScreenshotAs(OutputType.BASE64);
        return image;
    }

}
