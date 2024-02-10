package org.example.pages;


import lombok.Getter;
import org.openqa.selenium.*;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.*;

@Getter
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        this.sortPage = new SortPage(driver);
        this.popUpProduct = new PopUpProduct(driver);
        this.popUpProductInCart = new PopUpProductInCart(driver);
    }

    private SortPage sortPage;
    private PopUpProduct popUpProduct;
    private PopUpProductInCart popUpProductInCart;
    @FindBy(xpath = "//div[@id='city']/span")
    private WebElement availInCity;
    @FindBy(xpath = "//div[@id='discounted_item']/span")
    private WebElement productCond;
    @FindBy(xpath = "//div[@id='producer']")
    private WebElement producer;
    @FindBy(xpath = "//div[@id='price']/span")
    private WebElement price;
    @FindBy(xpath = "//div[contains(@class,'ui-library-labelContent')]" +
            "//button[contains(@class,'ui-library-buttonRadiusDefault')]")
    private List<WebElement> buttonsFilter;
    @FindBy(xpath = "//div[@id='city']/following-sibling::div/descendant::button")
    private WebElement buttonShowAllCityName;
    @FindBy(xpath = "//div[@id='city']/following-sibling::div/descendant::img")
    private List<WebElement> checkBoxCityNames;
    @FindBy(xpath = "//div[@id='city']/following-sibling::div/descendant::p")
    private List<WebElement> cityNames;
    @FindBy(xpath = "//div[@id='discounted_item']/following-sibling::div/descendant::span" +
            "[contains(@class,'ui-library-checkmark')]")
    private List<WebElement> listProductCond;
    @FindBy(xpath = "//div[@name='allPromoGoods']//h1")
    private WebElement productsHeader;
    @FindBy(xpath = "//div[@id='tileBlock']/div[contains(@class,'TileBlockstyled')]" +
            "//span[contains(@class,'GoodsDescriptionstyled')]")
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
    @FindBy(xpath = "//div[@id='price']//button[contains(@class,'ui-library-action')]")
    private WebElement buttonApplyPrice;
    @FindBy(xpath = " //div[@name='catalog-top']//div[@id='tileBlockFooter']//span[contains(@class,'ui-library-subtitle1Bold')]")
    private List<WebElement> productsPrices;
    @FindBy(xpath = "//div[contains(@class,'ui-library-typography')]/span")
    private WebElement choosedFilter;
    @FindBy(css = "iframe[src='https://www.googletagmanager.com/ns.html?id=GTM-KH7F6FQ']")
    private WebElement frameElement;

      private boolean isModalVisible(WebElement closePopUp) {
        try {
            return closePopUp.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }


    private void closeModal(WebElement closePopUp) {
        closePopUp.click();
    }

    public MainPage selectCityFilter(Integer index) {
        openCityFilter();
        selectCityCheckbox(index);

        return this;
    }


    private void selectCityCheckbox(Integer index) {
        checkBoxCityNames.get(index).click();
        buttonsFilter.get(0).click();
    }

    private void openCityFilter() {
        new Actions(driver).moveToElement(buttonsFilter.get(0)).click();
        buttonsFilter.get(0).click();
    }


    public String getSelectedCityName() {
        return cityNames.get(10).getText().toLowerCase();
    }

    public boolean compareListTitleFiltersTextWithExpected(List<String> expected) {
        List<String> actualFilters = new ArrayList<>();
        for (WebElement filter : titleFilters)
            actualFilters.add(filter.getText().toLowerCase());
        for (String expectedFilter : expected) {
            boolean found = false;
            for (String actualFilter : actualFilters) {
                if (actualFilter.contains(expectedFilter.toLowerCase())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public ProductPage chooseProductPage(Integer index) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.and(
                    ExpectedConditions.elementToBeClickable(linksProductPages.get(index)),
                    ExpectedConditions.visibilityOfAllElements(linksProductPages)));
            linksProductPages.get(index).click();
        } catch (TimeoutException | ElementClickInterceptedException e) {
            if (isModalVisible(popUpProductInCart.getClosePopUp())) {
                closeModal(popUpProductInCart.getClosePopUp());
                 }
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(linksProductPages.get(index)));
            linksProductPages.get(index).click();
        }
        return new ProductPage(driver);
    }
        public MainPage putCheckProductCondition (Integer index){
            productCond.click();
            new Actions(driver).moveToElement(producerMarks.get(1)).build().perform();
            listProductCond.get(index).isDisplayed();
            listProductCond.get(index).click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.and(
                            ExpectedConditions.visibilityOf(choosedFilter),
                            ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(listTitleProducts))));
            return this;
        }

        public boolean compareListTitleProductsTextWithProductCondition (String expected){
            boolean result = false;
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.and(
                            ExpectedConditions.visibilityOf(choosedFilter),
                            ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(listTitleProducts))));
            List<String> titleProducts = new ArrayList<>();
            for (WebElement titleProduct : listTitleProducts.subList(0, listTitleProducts.size() - 4)) {
                titleProducts.add(titleProduct.getText());
            }
            for (String title : titleProducts) {
                if (title.toLowerCase().contains(expected.toLowerCase())) {
                    result = true;
                } else {
                    return false;
                }
            }
            return result;
        }

        public MainPage putCheckboxProducerName (Integer index){
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By
                            .xpath("//div[@id='producer']/following-sibling::div//button")));
            new Actions(driver).moveToElement(buttonShowAllProducerName).click()
                    .click(producerMarks.get(index)).build().perform();
            return this;
        }

        public boolean compareSelectFilterAndProductTitle (Integer index){
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.and(
                            ExpectedConditions.visibilityOf(choosedFilter),
                            ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(listTitleProducts))));
            String[] selectedFilterOptions = choosedFilter.getText().toLowerCase().trim().split(": ");
            String[] productTitleWords = listTitleProducts.get(index).getText().toLowerCase().trim().split(" ");
            for (String selectedOption : selectedFilterOptions) {
                for (String productWord : productTitleWords) {
                    if (Objects.equals(selectedOption, productWord)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public MainPage putMinMaxValueOfPrice (String priceMin, String priceMax){
            priceInputNumberRangeMax.click();
            priceInputNumberRangeMax.clear();
            new Actions(driver).pause(Duration.ofSeconds(2)).sendKeys(priceInputNumberRangeMax, priceMax).build().perform();
            priceInputNumberRangeMin.click();
            priceInputNumberRangeMin.clear();
            new Actions(driver).sendKeys(priceInputNumberRangeMin, priceMin).scrollToElement(titleFilters.get(5)).build().perform();
            if (!isButtonApplyClickable()) {
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(buttonApplyPrice));
            }
            buttonApplyPrice.click();
            return this;
        }

        public boolean isButtonApplyClickable () {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(buttonApplyPrice));
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        }

        private List<Integer> conversionPriceList () {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                    .refreshed(ExpectedConditions.visibilityOfAllElements(productsPrices)));
            List<Integer> prices = new ArrayList<>();
            List<WebElement> visiblePrices = productsPrices.subList(0, productsPrices.size() - 4);
            for (WebElement productPrice : visiblePrices) {
                String priceValue = productPrice.getText().replaceAll(" ", "");
                prices.add(Integer.valueOf(priceValue));
            }
            return prices;
        }

        public boolean isPresentPriceInRange (Integer priceMin, Integer priceMax){
            boolean result = false;
            List<Integer> prices = conversionPriceList();
            for (Integer price : prices) {
                if (priceMin <= price && price <= priceMax) {
                    result = true;
                } else {
                    result = false;
                }
            }
            return result;
        }

        public boolean comparePricesOrderOfIncrease () {
            List<Integer> prices = conversionPriceList();
            for (int i = 0; i < prices.size() - 1; i++) {
                if (prices.get(i) > prices.get(i + 1)) {
                    return false;
                }
            }
            return true;
        }

        public boolean comparePricesDescendingOrder () {
            List<Integer> prices = conversionPriceList();
            for (int i = 0; i < prices.size() - 1; i++) {
                if (prices.get(i) < prices.get(i + 1)) {
                    return false;
                }
            }
            return true;
        }

        private List<String> getVisibleProductTitles () {
            List<String> titles = new ArrayList<>();
            for (int i = 0; i < listTitleProducts.size() - 4; i++) {
                String title = listTitleProducts.get(i).getText();
                String titleWithoutWord = title.replace("УЦІНКА!", "").trim();
                titles.add(titleWithoutWord);
            }
            return titles;
        }

        private List<String> sort () {
            List<String> strings = getVisibleProductTitles();
            Collections.sort(strings);
            return strings;
        }


        public boolean comparisonProductHeaderSorting () {
            List<String> sortedTitles = sort();
            List<String> visibleTitles = getVisibleProductTitles();
            return sortedTitles.equals(visibleTitles);
        }

        public void chooseSortAndRetrieveTitles (Integer index){
            getSortPage().chooseSortName(index);
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(listTitleProducts)));
            getVisibleProductTitles();
        }

        private List<String> sortReverse () {
            List<String> strings = getVisibleProductTitles();
            strings.sort(Collections.reverseOrder());
            return strings;
        }

        public boolean comparisonReverseSortingProductTitles () {
            List<String> strings = sortReverse();
            List<String> actualTitles = getVisibleProductTitles();
            return actualTitles.equals(strings);
        }

        public void chooseSortAndRetrieveTitlesInReverseOrder (Integer index){
            getSortPage().chooseSortName(index).chooseSortName(index);
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.refreshed(ExpectedConditions
                            .visibilityOfAllElements(listTitleProducts)));
            getVisibleProductTitles();
        }
    }