import org.example.pages.MainPage;
import org.example.pages.ProductPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.valueOf;


public class FiltersTest extends BaseTest {
    @DataProvider(name = "Product condition")
    public Object[][] checkProductCondition() {
        return new Object[][]{
                {"Уцінка", 0},
                {"Уцінка", 1}

        };
    }

    @DataProvider(name = "Price")
    public Object[][] checkPrice() {
        return new Object[][]{
                {"15 000", "75 000"}

        };
    }


    @Test
    public void checkTitleFilters() {
        List<String> data = new ArrayList<>();
        data.add("Наявність в місті");
        data.add("Стан товару");
        data.add("Ціна");
        data.add("Тип холодильника");
        data.add("Висота");
        data.add("Система охолодження холодильної камери");
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.compareListTitleFiltersTextWithExpected(data));

    }

    @Test
    public void filterAvailabilityCity() {
        MainPage mainPage = new MainPage(driver);
        String chooseCity = mainPage.putCheckboxCityName(10).getTextFromCityNames(10);
        ProductPage productPage = mainPage.chooseProductPage(0);
        String city = productPage.chooseNameCityFromFieldInput();
        Assert.assertEquals(chooseCity, city);
    }

    @Test(dataProvider = "Product condition")
    public void filterProductCondition(String data, Integer index) {
        MainPage mainPage = new MainPage(driver);
        if (index == 0) {
            Assert.assertTrue(mainPage.putCheckProductCondition(index)
                    .compareListTitleProductsTextWithProductCondition(data));
            ProductPage productPage = mainPage.chooseProductPage(0);
            Assert.assertTrue(productPage.getTitleProduct().contains(data.toLowerCase()));
        } else {
            if (index == 1) {
                Assert.assertFalse(mainPage.putCheckProductCondition(index)
                        .compareListTitleProductsTextWithProductCondition(data));
                ProductPage productPage = mainPage.chooseProductPage(5);
                Assert.assertFalse(productPage.getTitleProduct().contains(data.toLowerCase()));
            } else Assert.fail("This product condition isn`t");

        }
    }

    @Test
    public void filterProducer() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.putCheckboxProducerName(3).compareSelectFilterAndProductTitle(12));
    }

    @Test(dataProvider = "Price")
    public void filterPriceByNumber(String priceMin, String priceMax) {
        MainPage mainPage = new MainPage(driver);
        int priceValueMin = valueOf(priceMin.replaceAll(" ", ""));
        int priceValueMax = valueOf(priceMax.replaceAll(" ", ""));
        Assert.assertTrue(mainPage.putMinMaxValueOfPrice(priceMin, priceMax)
                .isPresentPriceInRange(priceValueMin, priceValueMax));
    }



}

