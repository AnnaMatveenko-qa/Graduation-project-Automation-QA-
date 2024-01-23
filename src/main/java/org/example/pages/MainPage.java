package org.example.pages;


import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
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
        this.sortPage = new SortPage(driver);
    }

    private SortPage sortPage;
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
    @FindBy(xpath = "//div[contains(@class,'TileBlockstyled__StyledTileBlockB')]//span")
    private List<WebElement> listTitleProducts;
    @FindBy(xpath = "//div[@id='tileBlock']/div[2]/a")
    private List<WebElement> linksProductPages;
    @FindBy(xpath = "//div[contains(@class,'StyledAppliedFiltersstyled__StyledAppliedFiltersContainer')]")
    private WebElement containerWithChoosedFilters;
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
    @FindBy(xpath = "//input[@id='input-range-min']")
    private WebElement rangeMinBySlider;
    @FindBy(xpath = "//input[@id='input-range-max']")
    private WebElement rangeMaxBySlider;
    @FindBy(xpath = "//div[@id='price']//button")
    private WebElement buttonApplyPrice;
    @FindBy(xpath = "//div[@id='tileBlockFooter']//span[contains(@class,'ui-library-subtitle1Bold')]")
    private List<WebElement> productsPrices;
    @FindBy(xpath = "//div[@class='ui-library-typographyContainer-5489']/span")
    private WebElement chooseFilter;

    public void putCheckProductCondition() {
        productCond.click();
        markDown.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
    }

    public void putCheckboxCityName(Integer index) {
        buttonShowAllCityName.isDisplayed();
        buttonShowAllCityName.click();
        cityNames.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
    }


    public ProductPage chooseProductPage(Integer index) {
        linksProductPages.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@class='product-name']")));
        return new ProductPage(driver);
    }

    public String getTitleFiltersText(Integer index) {
        return titleFilters.get(index).getText();

    }

    public MainPage putCheckboxProducerName(Integer index) {
        buttonShowAllProducerName.isDisplayed();
        buttonShowAllProducerName.click();
        producerMarks.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        return this;
    }

    public boolean compareSelectFilterAndProductTitle(Integer index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        String[] strs = chooseFilter.getText().toLowerCase().trim().split(": ");
        String[] strings = listTitleProducts.get(index).getText().toLowerCase().trim().split(" ");
        for (String a : strs) {
            System.out.println(a);
            for (String string : strings) {
                System.out.println(string);
                if (Objects.equals(a, string)) {
                    return true;
                }
            }
        }
        return false;
    }


    public MainPage putMinMaxValueOfPrice(String priceMin, String priceMax) {
        priceInputNumberRangeMax.click();
        priceInputNumberRangeMax.clear();
        new Actions(driver).pause(Duration.ofSeconds(3)).sendKeys(priceInputNumberRangeMax, priceMax).build().perform();
        priceInputNumberRangeMin.click();
        priceInputNumberRangeMin.clear();
        new Actions(driver).sendKeys(priceInputNumberRangeMin, priceMin).scrollToElement(titleFilters.get(5)).build().perform();
        buttonApplyPrice.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(chooseFilter));
        return this;
    }


    private List<Integer> conversionPriceList() {
        List<Integer> prices = new ArrayList<>();
        for (WebElement productsPrice : productsPrices) {
            String priceValue = productsPrice.getText().replaceAll(" ", "");
            prices.add(Integer.valueOf(priceValue));
        }
        return prices;
    }


    public boolean isPresentPriceInRange(Integer priceMin, Integer priceMax) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(containerWithChoosedFilters));
        boolean result = false;
        List<Integer> prices = conversionPriceList();
        for (int i = 0; i < prices.size() - 4; i++) {
            if (priceMin <= prices.get(i) && prices.get(i) <= priceMax) {

                result = true;
            } else {
                return false;
            }
        }
        return result;
    }

    //  public void moveSlider(String priceMin, String priceMax) {
    //    Actions actions = new Actions(driver);
    //    actions.clickAndHold(rangeMinBySlider)/
    // }
    public boolean comparePricesOrderOfIncrease() {
        boolean result = false;
        List<Integer> prices = conversionPriceList();
        for (int i = 0; i < prices.size() - 5; i++) {
            if (prices.get(i) <= prices.get(i + 1)) {
                System.out.println(prices.get(i));
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    public boolean comparePricesDescendingOrder(Integer index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(sortPage.getSortsNames().get(index)));
        boolean result = false;
        List<Integer> prices = conversionPriceList();
        for (int i = 0; i < prices.size() - 5; i++) {
            if (prices.get(i) >= prices.get(i + 1)) {
                System.out.println(prices.get(i));
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

}

