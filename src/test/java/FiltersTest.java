import org.example.pages.MainPage;
import org.example.pages.ProductPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class FiltersTest extends BaseTest {
  /*  @DataProvider(name = "Data")
    public Object[] checkTitle() {
        return new Object[]{
                // "Наявність в місті",
                "Стан товару"
                // "Виробник"

        };
    }*/

 /*   @Test(dataProvider = "Data")
    public void checkTitleFilters(String data) {
        RefrigeratorsPage refrigeratorsPage = new RefrigeratorsPage(driver);
        Assert.assertTrue(refrigeratorsPage.getProducer().isDisplayed());
        //  isPresentFilter(data,refrigeratorsPage.createListElements()));
        System.out.println(refrigeratorsPage.getProducer().getText());
    }*/

    @Test
    public void filterAvailabilityCity() {
        MainPage mainPage = new MainPage(driver);
        String chooseCity = mainPage.putCheckboxCityName(0).getChooseFilter().getText().toLowerCase();
        ProductPage productPage  =  mainPage.chooseProductPage(0);
        String city = productPage.getValueInputCity().getAttribute("value").toLowerCase();
       Assert.assertTrue(chooseCity.contains(city));
    }

    @Test
    public void filterProductCondition() {
        String expected = "Уцінка";
        MainPage mainPage = new MainPage(driver);
        mainPage.putCheckProductCondition();
        boolean result = mainPage
                .getTitleProduct(0).contains(expected.toLowerCase());
        Assert.assertTrue(result);
    }

    @Test
    public void filterProducer() {

    }

    @Test
    public void filterPriceByNumber() {

    }

    @Test
    public void filterPriceBySlider() {

    }

    @Test
    public void filterTypeProduct() {

    }

}

