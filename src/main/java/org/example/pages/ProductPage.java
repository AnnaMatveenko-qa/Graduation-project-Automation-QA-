package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class ProductPage extends BasePage {

    @FindBy(xpath = "(//input[@class='eldo-input'])[1]")
    private WebElement valueInputCity;
    @FindBy(xpath = "//div[@class='product-name']")
    private WebElement mainTitle;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String chooseNameCityFromFieldInput() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.
                        xpath("//input[@class='eldo-input']")));
        return valueInputCity.getAttribute("value").toLowerCase();
    }

    public String getTitleProduct() {
        return mainTitle.getText().toLowerCase();
    }
}