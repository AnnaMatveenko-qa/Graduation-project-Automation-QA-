package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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

    @FindBy(xpath = "//div[@class='title']/a")
    private WebElement titleAddedProduct;
    @FindBy(xpath = "//div[@class='icon-trash']")
    private List<WebElement> deleteProduct;


    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public BasketPage deleteProductFromBasket(Integer index) {
        int attempts = 0;
        int maxAttempts = 3;
        while (attempts < maxAttempts) {
            try {
                deleteProduct.get(index).isDisplayed();
                new Actions(driver).moveToElement(deleteProduct.get(index)).click().build().perform();
                return new BasketPage(driver);
            } catch (NoSuchElementException | StaleElementReferenceException exception) {
                driver.navigate().refresh();
                deleteProductFromBasket(index);
                attempts++;
            }
        }
        return null;
    }

    public boolean isLocationOnBasketPage() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(titleAddedProduct));
        titleAddedProduct.isDisplayed();
        return true;
    }

    public String getActualFromTitleAddedProduct() {
        return titleAddedProduct.getText().toLowerCase();
    }
}