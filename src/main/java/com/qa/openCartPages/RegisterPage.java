package com.qa.openCartPages;

import com.qa.appConstants.AppConstants;
import com.qa.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
public class RegisterPage {

    private  WebDriver driver;
    private ElementUtil elementUtil;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        elementUtil = new ElementUtil(driver );
    }
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmpassword = By.id("input-confirm");

    private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
    private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

    private By agreeCheckBox = By.name("agree");
    private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

    private By successMessg = By.cssSelector("div#content h1");
    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");

    public boolean userRegister(String firstName, String lastName,
                                String email, String telephone, String password,
                                String subscribe) {

        elementUtil.sendKeys(this.firstName, firstName);
        elementUtil.sendKeys(this.lastName, lastName);
        elementUtil.sendKeys(this.email, email);
        elementUtil.sendKeys(this.telephone, telephone);
        elementUtil.sendKeys(this.password, password);
        elementUtil.sendKeys(this.confirmpassword, password);

        if (subscribe.equalsIgnoreCase("yes")) {
            elementUtil.click(subscribeYes);
        } else {
            elementUtil.click(subscribeNo);
        }

        elementUtil.click(agreeCheckBox);
        elementUtil.click(continueButton);

        String successMesg = elementUtil.waitforElementVisibility(successMessg, 3000).getText();

        System.out.println(successMesg);

        if (successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
            elementUtil.click(logoutLink);
            elementUtil.click(registerLink);
            return true;
        } else {
            return false;
        }

    }





    
}
