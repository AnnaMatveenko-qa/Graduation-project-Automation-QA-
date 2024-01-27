package org.example.pages;


import lombok.Getter;
import org.openqa.selenium.*;

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
    @FindBy(xpath = "//div[@id='discounted_item']/following-sibling::div/descendant::span" +
            "[contains(@class,'ui-library-checkmark')]")
    private List<WebElement> listProductCond;
    @FindBy(xpath = "//div[@name='allPromoGoods']//h1")
    private WebElement productsHeader;
    @FindBy(xpath = "//div[contains(@class,'TileBlockstyled__StyledTileBlockB')]//span")
    private List<WebElement> listTitleProducts;
    @FindBy(xpath = "//div[@id='tileBlock']/div[contains(@class,'TileBlockstyled__StyledTileBlockHeader')]/a")
    private List<WebElement> linksProductPages;
    @FindBy(xpath = "//div[contains(@class,'StyledAppliedFiltersstyled__StyledAppliedFiltersContainer')]")
    private WebElement containerWithChoosedFilters;
    @FindBy(xpath = "//div[contains(@class,'ui-library-gridAlignItems-d14c ui-library-gridCustomColum')]/span")
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
    @FindBy(xpath = "//div[@id='price']//button[contains(@class,'ui-library-action')]")
    private WebElement buttonApplyPrice;
    @FindBy(xpath = " //div[@name='catalog-top']//div[@id='tileBlockFooter']//span[contains(@class,'ui-library-subtitle1Bold')]")
    private List<WebElement> productsPrices;
    @FindBy(xpath = "//div[contains(@class,'ui-library-typography')]/span")
    private WebElement chooseFilter;

    public MainPage putCheckProductCondition(Integer index) {
        productCond.click();
        listProductCond.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        return this;
    }

    public MainPage putCheckboxCityName(Integer index) {
        buttonShowAllCityName.isDisplayed();
        buttonShowAllCityName.click();
        cityNames.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        return this;
    }


    public ProductPage chooseProductPage(Integer index) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(linksProductPages.get(index)));
        linksProductPages.get(index).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@class='product-name']")));
        return new ProductPage(driver);
    }



    public boolean compareListTitleFiltersTextWithExpected(List<String> expected) {
        List<String> listTitleFiltersText = new ArrayList<>();
        for (int i = 0; i < titleFilters.size(); i++)
            listTitleFiltersText.add(titleFilters.get(i).getText());
        for (int i = 0; i < listTitleFiltersText.size(); i++) {
            for (int j = 0; j < expected.size(); j++) {
                if (listTitleFiltersText.get(i).toLowerCase().contains(expected.get(j).toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean compareListTitleProductsTextWithProductCondition(String expected) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        List<String> titleProducts = new ArrayList<>();
        for (int i = 0; i < listTitleProducts.size() - 4; i++)
            titleProducts.add(listTitleProducts.get(i).getText());
        for (int i = 0; i < titleProducts.size(); i++) {
            if (titleProducts.get(i).toLowerCase().contains(expected.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public MainPage putCheckboxProducerName(Integer index) {
        new Actions(driver).moveToElement(buttonShowAllProducerName).click()
                .pause(Duration.ofSeconds(5)).click(producerMarks.get(index)).perform();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        return this;
    }

    public boolean compareSelectFilterAndProductTitle(Integer index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
        chooseFilter.isDisplayed();
        String[] strs = chooseFilter.getText().toLowerCase().trim().split(": ");
        String[] strings = listTitleProducts.get(index).getText().toLowerCase().trim().split(" ");
        for (String a : strs) {
            for (String string : strings) {
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
        new Actions(driver).pause(Duration.ofSeconds(2)).sendKeys(priceInputNumberRangeMax, priceMax).build().perform();
        priceInputNumberRangeMin.click();
        priceInputNumberRangeMin.clear();
        new Actions(driver).sendKeys(priceInputNumberRangeMin, priceMin).scrollToElement(titleFilters.get(5)).build().perform();
        new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions
                .elementToBeClickable(buttonApplyPrice));
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
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(sortPage.getSortsNames().get(1)));
        return prices;
    }


    public boolean isPresentPriceInRange(Integer priceMin, Integer priceMax) {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOf(chooseFilter));
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


    public boolean comparePricesOrderOfIncrease() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(sortPage.getSortsNames().get(1)));
        boolean result = false;
        List<Integer> prices = conversionPriceList();
        for (int i = 0; i < prices.size() - 5; i++) {
            if (prices.get(i) <= prices.get(i + 1)) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    public boolean comparePricesDescendingOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(sortPage.getSortsNames().get(1)));
        boolean result = false;
        List<Integer> prices = conversionPriceList();
        for (int i = 0; i < prices.size() - 5; i++) {
            if (prices.get(i) >= prices.get(i + 1)) {
                result = true;
            } else {
                return false;
            }
        }
        return result;
    }

    public MainPage moveSlider(Integer priceMin, Integer priceMax) {
        //   System.out.println(rangeMaxBySlider.getLocation());


     /*   System.out.println(rangeMinBySlider.getLocation());
        Dimension sizeMin = rangeMinBySlider.getSize();
        Dimension sizeMax = rangeMinBySlider.getSize();
        System.out.println(sizeMax);
        System.out.println(sizeMin);
        int offsetXMin = (sizeMin.getWidth() / 2) + 1;
        int offsetYMin = (sizeMin.getHeight() / 2) + 1;
        int offsetXMax = (sizeMax.getWidth() / 2) + 1;
        int offsetYMax = (sizeMax.getHeight() / 2) + 1;
        System.out.println(offsetXMin);
        System.out.println(offsetYMin);
        System.out.println(offsetXMax);
        System.out.println(offsetYMax);*/


        new Actions(driver).moveToElement(rangeMinBySlider).dragAndDropBy(rangeMinBySlider, 138, 0)
                .pause(Duration.ofSeconds(10)).perform();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonApplyPrice));
        new Actions(driver).scrollToElement(titleFilters.get(5)).pause(Duration.ofSeconds(5)).moveToElement(rangeMaxBySlider)
                .dragAndDropBy(rangeMaxBySlider, -50, 0).pause(Duration.ofSeconds(10)).perform();
        buttonApplyPrice.click();
        return this;
    }


}

