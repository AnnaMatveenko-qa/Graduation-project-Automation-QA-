import org.example.pages.MainPage;
import org.example.pages.ProductPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Integer.parseInt;


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
                {"15000", "75000"}
               // {"3799", "82999"}

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
        String chooseCity = mainPage.putCheckboxCityName(1).getChooseFilter().getText().toLowerCase();
        ProductPage productPage = mainPage.chooseProductPage(1);
        String city = productPage.getValueInputCity().getAttribute("value").toLowerCase();
        Assert.assertTrue(chooseCity.contains(city));
    }

    @Test
    public void filterProductCondition() {
        String expected = "Уцінка";
        MainPage mainPage = new MainPage(driver);
        mainPage.putCheckProductCondition();
        Assert.assertTrue(mainPage.getTitleProduct(1).contains(expected.toLowerCase()));

    }

    @Test
    public void filterProducer() {
        MainPage mainPage = new MainPage(driver);
        mainPage.putCheckboxProducerName(0);
        Assert.assertTrue(mainPage.compareSelectFilterAndProductTitle(0));
    }

    @Test(dataProvider = "Price")
    public void filterPriceByNumber(String priceMin, String priceMax) {
        MainPage mainPage = new MainPage(driver);
        mainPage.putRangeValueOfPrice(priceMin, priceMax);
        int priceValueMin = parseInt(priceMin.replaceAll(" ", ""));
        int priceValueMax = parseInt(priceMax.replaceAll(" ", ""));
        Assert.assertTrue(mainPage.isPresentPriceInRange(priceValueMin, priceValueMax));
    }


@Test
public void filterPriceBySlider() {

}

@Test
public void filterTypeProduct() {

}

}

