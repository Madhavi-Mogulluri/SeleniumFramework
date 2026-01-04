package com.qa.tests;

import com.qa.base.BaseTest;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Owner("Madhavi Mogulluri")
public class ProductResulstsPageTest extends BaseTest {

    @BeforeClass
    public void productPageSetUp(){
       accountPage=  loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test(priority = 1)
    public void searchProduct(){
        productResultspage = accountPage.searchProduct("macbook");
    }

    @Test(priority = 2)
    public void getProductCountTest(){
       int productCount = productResultspage.productResultsCount();
        Assert.assertEquals(productCount,3);

    }





}
