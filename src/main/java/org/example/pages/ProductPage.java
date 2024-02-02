package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class ProductPage extends BasePage {
    private Header header;
    @FindBy(xpath = "(//input[@class='eldo-input'])[1]")
    private WebElement valueInputCity;
    @FindBy(xpath = "//div[@class='product-name']")
    private WebElement mainTitle;
    @FindBy(xpath = "(//div[@class='buy-button sp valign-wrapper']/span)[2]")
    private WebElement buttonBuyInStore;
    @FindBy(xpath = "//div[@class='product-head-text']")
    private WebElement container;


    public ProductPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
    }

    public String chooseNameCityFromFieldInput() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOf(valueInputCity));
        return valueInputCity.getAttribute("value").toLowerCase();
    }

    public String getTitleProduct() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(mainTitle));
        return mainTitle.getText().toLowerCase();
    }

    public ProductPage addProductToBasket() {
        buttonBuyInStore.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(buttonBuyInStore));
        container.click();
        return this;
    }

    public BasketPage goToBasket() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(header.getLinkBasket()));
        header.getLinkBasket().click();
        return new BasketPage(driver);
    }

    public MainPage returnMainPage() {
        driver.navigate().back();
        return new MainPage(driver);
    }


}