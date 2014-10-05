package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by illiasmieshkov on 10/3/14.
 */
public class HomePage {

    @FindBy (xpath = "//a[text() = 'Sign Up Now']")
    WebElement signUpNowBtn;

    @FindBy (xpath = "//a[text() = 'Features']")
    WebElement featuresBtn;

    private WebDriver driver;

    public static String URL = "http://devmate.com";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage signUpNow () {
        signUpNowBtn.click();
        return new SignUpPage(driver);
    }

    public FrameworksPage featuresClick () {
        featuresBtn.click();
        return new FrameworksPage(driver);
    }
}
