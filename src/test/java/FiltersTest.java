import org.example.pages.MainPage;
import org.example.pages.ProductPage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static java.lang.Integer.valueOf;


public class FiltersTest extends BaseTest {
    @DataProvider(name = "Data")
    public Object[][] checkTitle() {
        return new Object[][]{
                {"Наявність в місті", 0},
                {"Стан товару", 1},
                {"Виробник", 2}

        };
    }

    @DataProvider(name = "Product condition")
    public Object[][]checkProductCondition() {
        return new Object[][]{
                {"Уцінка", 0 },
                {"Уцінка", 1 }

        };
    }

    @DataProvider(name = "Price")
    public Object[][]checkPrice() {
        return new Object[][]{
                {"15 000", "75 000"}

        };
    }


    @Test(dataProvider = "Data")
    public void checkTitleFilters(String data, Integer index) {
        MainPage mainPage = new MainPage(driver);
        String actual = mainPage.getTitleFiltersText(index);
        Assert.assertEquals(actual, data.toLowerCase());

    }

    @Test
    public void filterAvailabilityCity() {
        MainPage mainPage = new MainPage(driver);
        mainPage.putCheckboxCityName(10);
        String chooseCity = mainPage.getChooseFilter().getText().toLowerCase();
        ProductPage productPage = mainPage.chooseProductPage(0);
        String city = productPage.chooseNameCityFromFieldInput();
        Assert.assertTrue(chooseCity.contains(city));
    }

    @Test(dataProvider ="Product condition")
    public void filterProductCondition(String data, Integer index) {
        MainPage mainPage = new MainPage(driver);
        if (index==0){
        Assert.assertTrue(mainPage.putCheckProductCondition(index)
                .compareListTitleProductsTextWithProductCondition(data));
        ProductPage productPage = mainPage.chooseProductPage(5);
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
        mainPage.putCheckboxProducerName(3);
        Assert.assertTrue(mainPage.compareSelectFilterAndProductTitle(12));
    }

    @Test(dataProvider = "Price")
    public void filterPriceByNumber(String priceMin, String priceMax) {
        MainPage mainPage = new MainPage(driver);
        int priceValueMin = valueOf(priceMin.replaceAll(" ", ""));
        int priceValueMax = valueOf(priceMax.replaceAll(" ", ""));
        Assert.assertTrue(mainPage.putMinMaxValueOfPrice(priceMin, priceMax)
                .isPresentPriceInRange(priceValueMin, priceValueMax));
    }

 /*  @Test(dataProvider = "Price")
    public void filterPriceBySlider(String priceMin, String priceMax) {
        MainPage mainPage = new MainPage(driver);
        int priceValueMin = valueOf(priceMin.replaceAll(" ", ""));
        int priceValueMax = valueOf(priceMax.replaceAll(" ", ""));
        Assert.assertTrue(mainPage.moveSlider(priceValueMin, priceValueMax).isPresentPriceInRange(priceValueMin, priceValueMax ));

    }*/



}

