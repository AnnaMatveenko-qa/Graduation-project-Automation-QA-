package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Getter
public class BasketPage extends BasePage {

    @FindBy(xpath = "//div[@class='title']/a")
    private List<WebElement> titleAddedProduct;
    @FindBy(xpath = "//div[@class='icon-trash']")
    private List<WebElement> buttonDeleteProduct;


    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public BasketPage deleteProductFromBasket(Integer index) {
        int attempts = 0;
        int maxAttempts = 3;
        while (attempts < maxAttempts) {
            try {
                buttonDeleteProduct.get(index).isDisplayed();
                new Actions(driver).moveToElement(buttonDeleteProduct.get(index)).click().build().perform();
                return new BasketPage(driver);
            } catch (NoSuchElementException | StaleElementReferenceException exception) {
                driver.navigate().refresh();
                deleteProductFromBasket(index);
                attempts++;
            }
        }
        return null;
    }


    public String getActualFromTitleAddedProduct(Integer index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElements(titleAddedProduct));
        titleAddedProduct.get(index).isDisplayed();
        return titleAddedProduct.get(index).getText().toLowerCase();
    }

    public boolean isPresentProductInBasket(String expected) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.stalenessOf(titleAddedProduct.get(1)));
        } catch (TimeoutException e) {
            driver.navigate().refresh();
        }
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.and(
                ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(titleAddedProduct)),
                ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='title']/a")))));
        for (WebElement product : titleAddedProduct) {
            String actual = product.getText().toLowerCase();
            if (actual.equals(expected.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
