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
public class ProductPage extends BasePage {
    private Header header;
    private PopUpProduct popUpProduct;
    private PopUpProductInCart popUpProductInCart;
    @FindBy(className = "product-view-specifications")
    private WebElement buttonProductViewSpecifications;
    @FindBy(xpath = "//div[@class='product-delivery-title']//input[@class='eldo-input']")
    private WebElement valueInputCity;
    @FindBy(xpath = "//div[@class='product-name']/h1")
    private WebElement mainTitle;
    @FindBy(xpath = "//div[@class='left-part price-information']//div[@class='buy-button sp valign-wrapper']")
    private WebElement buttonBuyInStore;
    @FindBy(xpath = "//div[@class='product-head-text']")
    private WebElement container;


    public ProductPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
        this.popUpProduct = new PopUpProduct(driver);
        this.popUpProductInCart = new PopUpProductInCart(driver);
    }

    public String getCityNameFromFieldInput() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(valueInputCity));
        new Actions(driver).moveToElement(valueInputCity).build().perform();
        return valueInputCity.getAttribute("value").toLowerCase();
    }

    public String getTitleProduct() {
        mainTitle.isDisplayed();
        return mainTitle.getText().toLowerCase();

    }

    public ProductPage addProductToBasket() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(buttonBuyInStore));
        buttonBuyInStore.isDisplayed();
        buttonBuyInStore.click();
        popUpProduct.closePopUp();
        return this;
    }

    public BasketPage goToBasket() {
        header.getLinkBasket().click();
        return new BasketPage(driver);
    }

    public MainPage returnMainPage() {
        driver.navigate().back();
        return new MainPage(driver);
    }


}