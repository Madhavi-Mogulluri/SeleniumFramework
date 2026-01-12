package com.qa.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.factory.DriverFactory;
import com.qa.openCartPages.AccountsPage;
import com.qa.openCartPages.LoginPage;
import com.qa.openCartPages.ProductInfoPage;
import com.qa.openCartPages.ProductResultsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.util.Properties;

@Listeners(ChainTestListener.class)
public class BaseTest {

    public static Logger log = LogManager.getLogger(DriverFactory.class);

    WebDriver driver;
    DriverFactory df;
    protected Properties prop;
    protected  LoginPage loginpage;
    protected AccountsPage accountPage;
    protected ProductResultsPage productResultspage;
    protected ProductInfoPage productInfoPage;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName){
        df = new DriverFactory();
        prop =df.initProperties();
        if(browserName!= null){
            prop.setProperty("browser",browserName);
        }
        driver = df.initializeDriver(prop);
        loginpage = new LoginPage(driver);
    }


    @AfterMethod
    public void attachScreeshot(ITestResult result){
        if(!result.isSuccess()){
          ChainTestListener.embed(DriverFactory.takeScreenshotAsBase64(),"image/png");
            log.info("screenshot is attached");
        }
//        else{
//            ChainTestListener.embed(DriverFactory.takeScreenshotAsBase64(), "image/png");
//            log.info("screenshot is attached");
//        }

    }
    @AfterTest
    public void teardown(){
        driver.quit();
        log.info("driver is closed");
    }



}
