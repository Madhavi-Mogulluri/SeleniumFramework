package com.qa.factory;

import com.qa.utils.ElementUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

   private  Properties prop;
    public static Logger log = LogManager.getLogger(OptionsManager.class);
    public OptionsManager(Properties prop){
        this.prop = prop;
    }


    public ChromeOptions chromeOptions(){
        ChromeOptions co = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            log.info("Running the test cases in headless Chrome");
            co.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
           log.info("Running the test cases in incoginto Mode");
            co.addArguments("--incognito");
        }
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            log.info("Running test cases remotely");
            co.setCapability("browserName","chrome");
        }
      return co;
    }

    public FirefoxOptions firefoxOptions(){
        FirefoxOptions fo = new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            log.info("Running the test cases in headless firefox");
            fo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            log.info("Running the test cases in incoginto Mode");
            fo.addArguments("--incognito");
        }
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            log.info("Running test cases remotely");
            fo.setCapability("browserName","firefox");
        }
    return fo;
    }

    public EdgeOptions edgeOptions(){
        EdgeOptions eo = new EdgeOptions();
        eo.setBrowserVersion("stable");
        eo.setPlatformName("LINUX");
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            log.info("Running the test cases in headless edge");
            eo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            log.info("Running the test cases in incoginto Mode");
            eo.addArguments("--inprivate");
        }
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            log.info("Running test cases remotely");
            eo.setCapability("browserName","edge");
        }
        return eo;
    }


}
