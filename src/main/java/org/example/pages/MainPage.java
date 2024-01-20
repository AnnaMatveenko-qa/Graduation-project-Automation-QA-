package org.example.pages;


import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
@Getter
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = "//input[@type='search']")
    private WebElement inputSearch;
    @FindBy(xpath = "//div[@id='city']/span")
    private WebElement availInCity;
    @FindBy(xpath = "//div[@id='discounted_item']")
    private WebElement productCond;
    @FindBy(xpath = "//div[@id='producer']")
    private WebElement producer;
    @FindBy(xpath = "//div[@id='price']/span")
    private WebElement price;
    @FindBy(xpath = "//div[@id='city']/following-sibling::div/descendant::button")
    private WebElement buttonShowAllCityName;
    @FindBy(xpath = "//div[@id='city']/following-sibling::div/descendant::img")
    private List<WebElement> cityNames;
    @FindBy(xpath = "//div[@id='discounted_item']/following-sibling::div/descendant::span[1]")
    private WebElement markDown;
    @FindBy(xpath = "//div[@name='allPromoGoods']//h1")
    private WebElement productsHeader;
    @FindBy(xpath = "//div[@id='tileBlock']/child::div[3]//span")
    private List<WebElement> productsTitles;
    @FindBy(xpath = "//div[@id='tileBlock']/div[2]/a")
    List<WebElement> linksProductPages;
     @FindBy(xpath = "//div[@name='catalog-top']//descendant::div/span[contains(@class,'ui-library-body2Medium')]")
    private WebElement chooseFilter;
    public void putCheckProductCondition() {
        productCond.click();
        markDown.click();
    }

    public MainPage putCheckboxCityName(Integer index) {
        buttonShowAllCityName.isDisplayed();
        buttonShowAllCityName.click();
        cityNames.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        return  this;
    }

    public String getTitleProduct(Integer index){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
     return  productsTitles.get(index).getText().toLowerCase();

    }

public ProductPage chooseProductPage(Integer index){
       linksProductPages.get(index).click();
       return new ProductPage(driver);
}



}

