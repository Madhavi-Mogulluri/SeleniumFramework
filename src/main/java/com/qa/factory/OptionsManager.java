package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

   private  Properties prop;
    public OptionsManager(Properties prop){
        this.prop = prop;
    }


    public ChromeOptions chromeOptions(){
        ChromeOptions co = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            System.out.println("Running the test cases in headless Chrome");
            co.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            System.out.println("Running the test cases in incoginto Mode");
            co.addArguments("--incognito");
        }
      return co;
    }

    public FirefoxOptions firefoxOptions(){
        FirefoxOptions fo = new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            System.out.println("Running the test cases in headless firefox");
            fo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            System.out.println("Running the test cases in incoginto Mode");
            fo.addArguments("--incognito");
        }
    return fo;
    }

    public EdgeOptions edgeOptions(){
        EdgeOptions eo = new EdgeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            System.out.println("Running the test cases in headless edge");
            eo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            System.out.println("Running the test cases in incoginto Mode");
            eo.addArguments("--inprivate");
        }
        return eo;
    }


}
