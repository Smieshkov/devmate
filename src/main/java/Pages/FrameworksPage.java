package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by illiasmieshkov on 10/5/14.
 */
public class FrameworksPage {

    @FindBy (xpath = "//img[@alt = 'Sparkle-based updates framework']")
    WebElement image;

    private WebDriver driver;

    public static String URL = "http://devmate.com/features/frameworks";

    public FrameworksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isImagePresent () {
        String js = "return (typeof arguments[0].naturalWidth!=\"undefined\" && arguments[0].naturalWidth>0)";
        Boolean isImageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript(js, image);
        return isImageDisplayed;
    }
}
