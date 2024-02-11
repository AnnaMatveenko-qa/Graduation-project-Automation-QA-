package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@class='title']/a")
    private List<WebElement> titleAddedProduct;
    @FindBy(xpath = "//div[@class='icon-trash']")
    private List<WebElement> buttonDeleteProduct;


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage deleteProductFromCart(Integer index) {
        new Actions(driver).moveToElement(buttonDeleteProduct.get(index)).click().build().perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
               .refreshed(ExpectedConditions.invisibilityOf(titleAddedProduct.get(index))));
        return new CartPage(driver);

    }


    public String getActualFromTitleAddedProduct(Integer index) {
        titleAddedProduct.get(index).isDisplayed();
        return titleAddedProduct.get(index).getText().toLowerCase();

    }

    public boolean isAbsentProductInCart(String expected) {
        if (titleAddedProduct.isEmpty()) {
            return true;
        }
        for (WebElement product : titleAddedProduct) {
            String actual = product.getText().toLowerCase();
            if (actual.equals(expected.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}



