package com.qa.tests;

import com.qa.base.BaseTest;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qa.appConstants.AppConstants.LOGINPAGE_FRACTION_URL;
import static com.qa.appConstants.AppConstants.LOGIN_PAGE_TITLE;

@Owner("Madhavi Mogulluri")
public class LoginTest extends BaseTest {

    @Test(description = "this test check the title of login page")
    @Severity(SeverityLevel.MINOR)
    @Step("checking login page Title")
    public void checkLoginpageTitle() {
        String loginpageTitle = loginpage.getTitle();
        Assert.assertEquals(loginpageTitle, LOGIN_PAGE_TITLE);
    }

    @Test(enabled = false)
    @Step("checking login page Url")
    public void checkLoginPagepageUrl() {
        String loginpageURL = loginpage.getURL();
        Assert.assertTrue(loginpageURL.contains(LOGINPAGE_FRACTION_URL));
    }

    @Test(priority = Short.MAX_VALUE)
    @Severity(SeverityLevel.BLOCKER)
    public void LogintoApp() {
        loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }


}
