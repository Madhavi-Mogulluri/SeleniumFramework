package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.utils.CsvUtil;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;
@Owner("Madhavi Mogulluri")
public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void loginToApp(){
       accountPage= loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }



    @DataProvider
    public Object[][] getProductTestData(){

        return new Object[][]{
                {"macbook","MacBook Air"},
                {"macbook","MacBook Pro"},
                {"iMac","iMac"},
                {"samsung", "Samsung SyncMaster 941BW"},
                {"samsung","Samsung Galaxy Tab 10.1"}
        };
    }

    @DataProvider
    public Object[][] getProductTestDatawithImages(){

        return new Object[][]{
                {"macbook","MacBook Air",4},
                {"macbook","MacBook Pro",4},
                {"iMac","iMac",3},
                {"samsung", "Samsung SyncMaster 941BW",1},
                {"samsung","Samsung Galaxy Tab 10.1",7}
        };
    }


    @DataProvider
    public Object [] [] getCsvData(){
       return CsvUtil.readCsv("product");
    }


    @Test(dataProvider = "getProductTestData")
    public void getProductHeaderTest(String productName, String productKey){
        productResultspage = accountPage.searchProduct(productName);
        productInfoPage = productResultspage.selectProduct(productKey);
        String productHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(productHeader,productKey);
    }

    @Test(dataProvider = "getProductTestDatawithImages")
    public void verifyImagesofProductTest(String productName, String productKey,int imagesCount){
        productResultspage = accountPage.searchProduct(productName);
        productInfoPage =productResultspage.selectProduct(productKey);
        int productImagesCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(productImagesCount,imagesCount);
    }

    @Test(dataProvider = "getCsvData")
    public void verifyImagesofProductwithCsvTest(String productName, String productKey,String imagesCount){
        productResultspage = accountPage.searchProduct(productName);
        productInfoPage =productResultspage.selectProduct(productKey);
        int productImagesCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(String.valueOf(productImagesCount),imagesCount);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void getallTheProductInfoTest(){
       productResultspage=  accountPage.searchProduct("iMac");
        productInfoPage = productResultspage.selectProduct("iMac");
        Map<String,String> actualProductDetails = productInfoPage.getProductInfo();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProductDetails.get("Brand"),"Apple");
        softAssert.assertEquals(actualProductDetails.get("Product Code"),"Product 14");
        softAssert.assertEquals(actualProductDetails.get("price"),"$122.00");
        softAssert.assertEquals(actualProductDetails.get("ExTaxPrice"),"$100.00");
        softAssert.assertAll();

    }



}
