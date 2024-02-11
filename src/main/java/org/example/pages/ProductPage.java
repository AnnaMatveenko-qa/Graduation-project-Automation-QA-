package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

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
    @FindBy(xpath = "//div[@class='product-status']//span")
    private WebElement productStatus;

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

    public ProductPage addProductToCart() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(buttonBuyInStore));
        buttonBuyInStore.isDisplayed();
        buttonBuyInStore.click();
        popUpProduct.closePopUp();
        return this;
    }

    public CartPage goToCart() {
        header.getLinkBasket().click();
        return new CartPage(driver);
    }

    public MainPage returnMainPage() {
        driver.navigate().back();
        return new MainPage(driver);
    }

    public boolean statusProduct() {
        return productStatus.getText().equalsIgnoreCase(IN_STOCK) || productStatus.getText().equalsIgnoreCase(IT_ENDS)
                || productStatus.getText().equalsIgnoreCase(SALES);
    }

    public void writeText(String fileName, String text) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName, true)) {
            String textWithNewLine = text + System.lineSeparator();
            fileOutputStream.write(textWithNewLine.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("File not find");
        } catch (IOException e) {
            System.out.println("error while reading to file ");
        }
    }
}
