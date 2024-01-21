package org.example.pages;


import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);

    }


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
    private List<WebElement> linksProductPages;
    @FindBy(xpath = "//div[@name='catalog-top']//div[contains(@class,'ui-library-typographyContainer')]/span[contains(@class,'ui-library-body2Medium-fa40')]")
    private WebElement chooseFilter;
    @FindBy(xpath = "//div[contains(@class,'ui-library-gridAlignItems-d14c ui-library-gridCustomColum')]")
    private List<WebElement> titleFilters;
    @FindBy(xpath = "//div[@id='producer']/following-sibling::div//button")
    private WebElement buttonShowAllProducerName;
    @FindBy(xpath = "//div[@id='producer']/following-sibling::div//img")
    private List<WebElement> producerMarks;
    @FindBy(xpath = "//input[@id='price-input-number-range-min']")
    private WebElement priceInputNumberRangeMin;
    @FindBy(xpath = "//input[@id='price-input-number-range-max']")
    private WebElement priceInputNumberRangeMax;
    @FindBy(xpath = "//div[@id='price']//button")
    private WebElement buttonApplyPrice;
    @FindBy(xpath = "//div[@id='tileBlockFooter']//span[contains(@class,'ui-library-subtitle1Bold')]")
    private List<WebElement> productsPrices;

    public void putCheckProductCondition() {
        productCond.click();
        markDown.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
    }

    public MainPage putCheckboxCityName(Integer index) {
        buttonShowAllCityName.isDisplayed();
        buttonShowAllCityName.click();
        cityNames.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        return this;
    }

    public String getTitleProduct(Integer index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        return productsTitles.get(index).getText().toLowerCase();
    }

    public ProductPage chooseProductPage(Integer index) {
        linksProductPages.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class='eldo-input'])[1]")));
        return new ProductPage(driver);
    }

    public String getTitleFiltersText(int index) {
        return titleFilters.get(index).getText();

    }

    public void putCheckboxProducerName(Integer index) {
        buttonShowAllProducerName.isDisplayed();
        buttonShowAllProducerName.click();
        producerMarks.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));

    }

    public boolean compareSelectFilterAndProductTitle(Integer index) {
        String[] strs = chooseFilter.getText().toLowerCase().trim().split(": ");
        String[] strings = productsTitles.get(index).getText().toLowerCase().trim().split(" ");
        for (String a : strs) {
            for (String string : strings) {
                if (Objects.equals(a, string)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void putRangeValueOfPrice(String priceMin, String priceMax) {
        priceInputNumberRangeMin.clear();
        priceInputNumberRangeMin.sendKeys(priceMin);
        priceInputNumberRangeMax.clear();
        priceInputNumberRangeMax.sendKeys(priceMax);
        buttonApplyPrice.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
    }

    private List<String> hasValuePriceOfChooseRage() {
        List<String> strings = new ArrayList<>();
        for (WebElement productsPrice : productsPrices) {
            String priceValue = productsPrice.getText().replaceAll(" ", "");
            strings.add(priceValue);
        }
        return strings;
    }

    private List<Integer> convertToStringList() {
        List<Integer> prices = new ArrayList<>();
        List<String> strings = hasValuePriceOfChooseRage();
        for (int i = 0; i < strings.size(); i++) {
            prices.add(Integer.valueOf(strings.get(i)));
        }
        return prices;
    }

    public boolean isPresentPriceInRange(int priceMin, int priceMax) {
        boolean result = false;
        List<Integer> prices = convertToStringList();
        for (Integer integer : prices) {
            if (priceMin <= integer && integer <= priceMax) {
                result = true;
            } else {
                return false;

            }
        }
        return result;
    }


}

