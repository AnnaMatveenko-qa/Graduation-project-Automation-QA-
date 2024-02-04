package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class BasketPage extends BasePage {
    private Header header;
    @FindBy(xpath = "//div[@class='title']/a")
    private WebElement titleAddedProduct;
    @FindBy(xpath = "//div[@class='icon-trash']")
    private List<WebElement> deleteProduct;


    public BasketPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);

    }

    public BasketPage deleteProductFromBasket(Integer index) {
        new Actions(driver).moveToElement(deleteProduct.get(index)).click();
        return new BasketPage(driver);
    }

    public boolean isLocationOnBasketPage() {
         new WebDriverWait(driver, Duration.ofSeconds(30))
               .until(ExpectedConditions.visibilityOf(titleAddedProduct));
        titleAddedProduct.isDisplayed();
        return true;
    }
}