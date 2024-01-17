package pages;


import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class RefrigeratorsPage extends BasePage {

    @FindBy(xpath = "//div[@id='city']/span")
    private WebElement availInСity;
    @FindBy(xpath = "//div[@id='discounted_item']/span")
    private WebElement productCond;
    @FindBy(xpath = "//div[@id='producer']/span")
    private WebElement producer;
    @FindBy(xpath = "//div[@id='price']/span")
    private WebElement price;







    public RefrigeratorsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



    public String getFilterText(WebElement webElement) {
        return webElement.getText();

    }

    public void clickButton(WebDriver driver, WebElement webElement) {
       scrollIntoView(driver,webElement);
       webElement.click();
    }

    public void putCheckBox(WebElement webElement) {
        webElement.click();
    }
    private static void scrollIntoView( WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

}