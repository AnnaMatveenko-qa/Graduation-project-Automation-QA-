import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
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
               // {"Уцінка", 0},
                {"Уцінка", 1}

        };
    }

    @DataProvider(name = "Price")
    public Object[][] checkPrice() {
        return new Object[][]{
                {"15 000", "75 000"},
                {"25 000", "35 000"}
        };
    }


    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "Test check for the presence of filter headers in the  in subcategory Refrigerators")
    @Owner("Anna Matveenko")
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

    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "Check filtering by availability in the city by enter valid name")
    public void filterAvailabilityCity() {
        MainPage mainPage = new MainPage(driver);
        String selectedCity = mainPage.selectCityFilter(10).getSelectedCityName();
        ProductPage productPage = mainPage.chooseProductPage(0);
        String cityNameOnProductPage = productPage.getCityNameFromFieldInput();
        Assert.assertEquals(cityNameOnProductPage,selectedCity);
    }

    @Test(dataProvider = "Product condition",invocationCount = NUMBER_OF_STARTS)
    @Description(value = "Check filtering by Product condition, check that the product " +
            "title on the selected product page contains the product status or lack thereof ")
    public void filterProductCondition(String data, Integer index) {
        MainPage mainPage = new MainPage(driver);
        if (index == 0) {
            Assert.assertTrue(mainPage.putCheckProductCondition(index)
                    .compareListTitleProductsTextWithProductCondition(data));
        } else {
            if (index == 1) {
                mainPage=new MainPage(driver);
                 ProductPage productPage = mainPage.putCheckProductCondition(index).chooseProductPage(5);
                Assert.assertFalse(productPage.getTitleProduct().contains(data.toLowerCase()));
            } else {
                Assert.fail("This product condition isn't");
            }
        }
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "Check filtering by Producer, the name of the producer" +
            "  of this product is present in the visible list of product titles")
    public void filterProducer() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.putCheckboxProducerName(3).compareSelectFilterAndProductTitle(12));
    }

    @Test(dataProvider = "Price",invocationCount = NUMBER_OF_STARTS)
    @Description(value = "Check filtering by Price, check that the price of the products is within the normal price range")
    public void filterPriceByPutNumbers(String priceMin, String priceMax) {
        MainPage mainPage = new MainPage(driver);
        int priceValueMin = valueOf(priceMin.replaceAll(" ", ""));
        int priceValueMax = valueOf(priceMax.replaceAll(" ", ""));
        Assert.assertTrue(mainPage.putMinMaxValueOfPrice(priceMin, priceMax)
                .isPresentPriceInRange(priceValueMin, priceValueMax));
    }



}

