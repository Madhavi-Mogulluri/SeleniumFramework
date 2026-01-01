package com.qa.tests;

import com.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static  com.qa.appConstants.AppConstants.*;


public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void login(){
        accountPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test
    public void checkHomepageTitle(){
       String homepageTitle = accountPage.accountPageTitleTest();
        Assert.assertEquals(homepageTitle,HOMEPAGE_PAGE_TITLE);
    }
    @Test
    public void checkHomepageUrl(){
        String homepageurl = accountPage.accountPageURL();
        Assert.assertTrue(homepageurl.contains(HOMEPAGE_FRACTION_URL));
    }

    @Test
    public void getHeadersListTest(){
        accountPage.getAllHeaders();

    }

    @Test(priority =  Short.MAX_VALUE)
    public void SearchProductInHomepageTest(){
        accountPage.searchProduct("MacBook");
    }



}
