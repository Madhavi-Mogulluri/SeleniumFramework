package runners;

import com.qa.utils.BrowserContext;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;


@CucumberOptions(
        features = "src/test/resources/parallel",
        glue = "parallel",
        tags = "@smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        },
        monochrome = true,
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    // private static ThreadLocal<String> browserThreadLocal = new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser(@Optional  String browser) {
        System.setProperty("browser", browser);
       BrowserContext.setBrowser(browser); //pass it to Hooks
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
