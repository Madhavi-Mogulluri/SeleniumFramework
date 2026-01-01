package com.qa.openCartPages;

import com.qa.appConstants.AppConstants;
import com.qa.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {

    private WebDriver driver;
    private ElementUtil elementUtil;
    //Locators
    By noOfProductImages = By.cssSelector("div#content ul.thumbnails img");
    By productHeader = By.tagName("h1");
    By productMetadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    By productPrice = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

  //Constructor
    public ProductInfoPage(WebDriver driver){
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }
    Map<String,String> productMap;




//Methods
    public int getProductImagesCount(){
       int noofImages = elementUtil.getElementsVisibleWithWait(noOfProductImages, AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
        System.out.println(noofImages);
        return noofImages;
    }

    public String getProductHeader(){
        String header =elementUtil.getElementwithWait(productHeader,AppConstants.MEDIUM_DEFAULT_TIMEOUT).getText();
        System.out.println(header);
        return header;
    }

    public Map<String, String> getProductInfo(){
        productMap = new LinkedHashMap<>();   //maintains insertion order
        productMap.put("header",getProductHeader());
        productMap.put("imagesCount",String.valueOf(getProductImagesCount()));
        getProductData();
        getProductPrice();
        System.out.println(productMap);
        return productMap;
    }



    private void getProductData(){
        List<WebElement> elements = elementUtil.getElementsVisibleWithWait(productMetadata, AppConstants.DEFAULT_TIMEOUT);
        for(WebElement e : elements){
            String[] meta = e.getText().split(":");
            String productKey =meta[0].trim();
            String productValue = meta[1].trim();
            productMap.put(productKey,productValue);
        }

    }

//    $122.00
//    Ex Tax: $100.00

    private void getProductPrice(){
        List<WebElement> priceList =elementUtil.getElementsVisibleWithWait(productPrice,AppConstants.DEFAULT_TIMEOUT);
      String productprice = priceList.get(0).getText().trim();
       String exTaxPrice = priceList.get(1).getText().substring(8);
       productMap.put("price",productprice);
       productMap.put("ExTaxPrice",exTaxPrice);

    }



}
