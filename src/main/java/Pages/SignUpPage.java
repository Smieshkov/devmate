package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by illiasmieshkov on 10/3/14.
 */
public class SignUpPage {

    @FindBy (xpath = "//input[@type = 'checkbox' and @name ='selling_outside_app_store']")
    WebElement sellOutAppStoreCBox;

    @FindBy (xpath = "//input[@type = 'text' and @name = 'solution']")
    WebElement solutionField;

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sellOutAppStoreCheck () {
        if (!sellOutAppStoreCBox.isSelected()) {
            sellOutAppStoreCBox.click();
        }
    }

    public boolean isVisibleSolutionField () {
        boolean result;
        if (solutionField.isDisplayed()) {result = true;}
        else {result = false;}
        return result;
    }

}
