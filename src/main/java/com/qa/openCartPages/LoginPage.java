package com.qa.openCartPages;

import com.qa.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import  static com.qa.appConstants.AppConstants.*;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private  By userName= By.name("email");
    private  By password= By.name("password");
    private  By loginBtn= By.cssSelector("input[value='Login']");
    private  By forgotPasswordLink= By.linkText("Forgotten Password");




    public LoginPage(WebDriver driver){
        this.driver = driver;
        elementUtil = new ElementUtil(driver );

    }

    public String getTitle(){
        String title = elementUtil.getTitle();
        return title;
    }

    public String getURL(){
        String url = elementUtil.getCurrentUrl();
        System.out.println(url);
        return url;
    }

    public boolean forgotPaaswordLinkisDisplayed(){
        boolean b = elementUtil.elementIsDisplayed(forgotPasswordLink);
        return b;
    }

    public AccountsPage doLogin(String user, String pwd){
        elementUtil.sendkeysWithWait(userName,DEFAULT_TIMEOUT,user);
        elementUtil.sendKeys(password,pwd);
        elementUtil.click(loginBtn);
       return new AccountsPage(driver);
    }


}
