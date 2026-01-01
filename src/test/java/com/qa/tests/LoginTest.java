package com.qa.tests;

import com.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import static  com.qa.appConstants.AppConstants.*;
public class LoginTest extends BaseTest {


    @Test(description = "this test check the title of login page")
    public void checkLoginpageTitle(){
        String loginpageTitle = loginpage.getTitle();
        Assert.assertEquals(loginpageTitle,LOGIN_PAGE_TITLE);
    }
    @Test(enabled = false)
    public void checkLoginPagepageUrl(){
        String loginpageURL = loginpage.getURL();
        Assert.assertTrue(loginpageURL.contains(LOGINPAGE_FRACTION_URL));
    }

    @Test(priority =  Short.MAX_VALUE)
    public void LogintoApp(){
        loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }



}
