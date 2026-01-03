package com.qa.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.factory.DriverFactory;
import com.qa.openCartPages.AccountsPage;
import com.qa.openCartPages.LoginPage;
import com.qa.openCartPages.ProductInfoPage;
import com.qa.openCartPages.ProductResultsPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.NoOpLog;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.Properties;

@Listeners(ChainTestListener.class)
public class BaseTest {

    Log log = new NoOpLog();

    WebDriver driver;
    DriverFactory df;
    protected Properties prop;
    protected  LoginPage loginpage;
    protected AccountsPage accountPage;
    protected ProductResultsPage productResultspage;
    protected ProductInfoPage productInfoPage;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(@Optional("chrome") String browserName){
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
        if(result.isSuccess()){
          ChainTestListener.embed(DriverFactory.takeScreenshotAsBase64(),"image/png");

        }
//        ChainTestListener.embed(DriverFactory.takeScreenshotAsBase64(), "image/png");
    }
    @AfterTest
    public void teardown(){
        driver.quit();
    }



}
