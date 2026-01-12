package com.qa.openCartPages;

import com.qa.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.qa.appConstants.AppConstants.*;

public class AccountsPage {
   private WebDriver driver;
  private  ElementUtil elementUtil;


//Locators
    private final By search = By.name("search");
    private  final By searchIcon = By.cssSelector("i.fa-search");
    private final By headers = By.cssSelector("div#content>h2");



//Constructor
    public AccountsPage(WebDriver driver){
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);

    }
    //methods

    public String accountPageTitleTest(){
        elementUtil.waitForTitle(HOMEPAGE_PAGE_TITLE,DEFAULT_TIMEOUT);
        return elementUtil.getTitle();
    }

    public String accountPageURL(){
        elementUtil.waitForURLcontains(HOMEPAGE_FRACTION_URL,DEFAULT_TIMEOUT);
        return elementUtil.getCurrentUrl();
    }

    public void getAllHeaders() {
        List<WebElement> textofWebelements = elementUtil.getListOfWebElements(headers);
        for (WebElement e : textofWebelements) {
            String text = e.getText();
            System.out.println(text);
        }
    }

    public ProductResultsPage searchProduct(String productName){
        System.out.println("search for the Product is " + productName);
        elementUtil.sendkeysWithWait(search,LONGER_DEFAULT_TIMEOUT,productName);
        elementUtil.click(searchIcon);
        elementUtil.waitForShortTime();
        return new ProductResultsPage(driver);
    }










}
