package parallel;

import com.qa.openCartPages.AccountsPage;
import com.qa.openCartPages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageSteps {

    private LoginPage loginPage;
    private AccountsPage accPage;

    String actTitle;
    String actURL;
    boolean flag;

    public LoginPageSteps(Hooks hooks) {
        this.loginPage = hooks.getLoginPage();
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Assuming the driver is already on the login page
    }

    @When("the user fetches the page title")
    public void the_user_fetches_the_page_title() {
        actTitle = loginPage.getTitle();
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        Assert.assertEquals(actTitle, expectedTitle);
    }

    @Then("the page title should not be empty")
    public void the_page_title_should_not_be_empty() {
        Assert.assertFalse("Page title should not be empty", actTitle.isEmpty());
    }



    @When("the user checks the page URL")
    public void the_user_checks_the_page_url() {
       actURL = loginPage.getURL();

    }

    @Then("the URL should contain {string}")
    public void the_url_should_contain(String fractionURL) {
        Assert.assertTrue(actURL.contains(fractionURL));
    }

    @When("the user checks the forgot password link")
    public void the_user_checks_the_forgot_password_link() {
        flag = loginPage.forgotPaaswordLinkisDisplayed();
    }

    @Then("the forgot password link should be displayed")
    public void the_forgot_password_link_should_be_displayed() {
        Assert.assertTrue(flag);
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        accPage = loginPage.doLogin(username, password);
    }

    @Then("the user should be redirected to the accounts page with title {string}")
    public void the_user_should_be_redirected_to_the_accounts_page_with_title(String expectedTitle) {
        Assert.assertEquals(accPage.accountPageTitleTest(), expectedTitle);
    }





}
