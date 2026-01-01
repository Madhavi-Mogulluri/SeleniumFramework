package com.qa.openCartPages;

import com.qa.appConstants.AppConstants;
import com.qa.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductResultsPage {

   private WebDriver driver;
    private ElementUtil elementUtil;
    private final By products = By.cssSelector("div.product-thumb");

    public ProductResultsPage(WebDriver driver){
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);

    }

    public int productResultsCount(){
        return elementUtil.getElementsVisibleWithWait(products, AppConstants.DEFAULT_TIMEOUT).size();
    }


    public ProductInfoPage selectProduct(String productKey){
        elementUtil.clickwithWait(By.partialLinkText(productKey), AppConstants.DEFAULT_TIMEOUT);
        return new ProductInfoPage(driver);
    }



}
