import Pages.AppManagementPage;
import Pages.FrameworksPage;
import Pages.HomePage;
import Pages.SignUpPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by illiasmieshkov on 10/3/14.
 */
public class DevMateTest {

    private WebDriver driver;

    @BeforeTest
    public void beforeTest () {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeMethod () {
        driver.get(HomePage.URL);
    }

    @AfterTest
    public void afterTest () {
        driver.close();
    }

    @Test
    public void scenario1 () {
        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        SignUpPage signUpPage = homePage.signUpNow();
        signUpPage.sellOutAppStoreCheck();

        softAssert.assertTrue(signUpPage.isVisibleSolutionField(), "Tested input field is not presented.");
    }

    @Test
    public void scenario2 () {
        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        FrameworksPage frameworksPage = homePage.featuresClick();

        //Checking if the current page has correct URL
        softAssert.assertEquals(driver.getCurrentUrl(), frameworksPage.URL, "Wrong URL on Frameworks page");
        //Checking if tested image is shown in browser
        softAssert.assertTrue(frameworksPage.isImagePresent(), "Tested image on Frameworks page is not shown.");

        //Making screenshot of Frameworks page, attaching it to report
        screenShotLog(driver, "FrameworksPage");

        driver.get("http://devmate.com/features/app-management");
        AppManagementPage appManagementPage = new AppManagementPage(driver);

        //Checking if the page has asked URL
        softAssert.assertEquals(driver.getCurrentUrl(), appManagementPage.URL, "Wrong URL on AppManagement page");
        //Checking if tested image is shown in browser
        softAssert.assertTrue(appManagementPage.isImagePresent(), "Tested image on AppManagement page is not shown.");

        softAssert.assertAll();

        //Making screenshot of AppManagement page, attaching it to report
        screenShotLog(driver, "AppManagementPage");

    }

    //The method captures a screenshot of web page and attaches it to report on Reporter output page
    public void screenShotLog (WebDriver driver, String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("target/surefire-reports/screenshots/" + fileName +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reporter.log("<br>" + fileName + " screenshot: <br>");
        Reporter.log("<br> <img src=screenshots/" + fileName + ".png style=max-width:500px;>  <br>");
    }

}
