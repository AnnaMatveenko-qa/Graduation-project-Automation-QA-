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

    @DataProvider(name = "Price")
    public Object[][] checkPrice() {
        return new Object[][]{
              {"15 000", "75 000"},
                {"4 000", "80 000"},
               {"25 000", "72 000"}
        };
    }


    @Test(dataProvider = "Data")
    public void checkTitleFilters(String data, int index) {
        MainPage mainPage = new MainPage(driver);
        String actual = mainPage.getTitleFiltersText(index);
        Assert.assertEquals(actual.toLowerCase(), data.toLowerCase());

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

    @Test
    public void filterProductCondition() {
        String expected = "Уцінка";
        MainPage mainPage = new MainPage(driver);
        mainPage.putCheckProductCondition();
        ProductPage productPage = mainPage.chooseProductPage(2);
        Assert.assertTrue(productPage.getTitleProduct().contains(expected.toLowerCase()));


    }

    @Test
    public void filterProducer() {
        MainPage mainPage = new MainPage(driver);
        mainPage.putCheckboxProducerName(5);
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
      public void filterPriceBySlider() {
    //  MainPage mainPage = new MainPage(driver);
          }
  */
    @Test
    public void filterTypeProduct() {

    }

}

