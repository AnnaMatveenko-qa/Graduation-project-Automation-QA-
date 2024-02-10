import io.qameta.allure.Description;
import org.apache.hc.core5.util.Asserts;
import org.example.pages.BasketPage;
import org.example.pages.MainPage;
import org.example.pages.ProductPage;

import org.testng.Assert;

import org.testng.annotations.Test;

public class BasketTest extends BaseTest {
    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "check that the name of the selected product is equivalent to the product in the cart")
    public void addProductToCart() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(3);
        String expected = productPage.getTitleProduct();
        BasketPage basketPage = productPage.addProductToBasket().goToBasket();
        Assert.assertEquals(basketPage.getActualFromTitleAddedProduct(0), expected);
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "check that the name of the selected product has been removed from the list of products in the cart")
    public void deleteProductFromCart() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(10);
       String firstExpected = productPage.getTitleProduct();
        BasketPage basketPage = productPage.addProductToBasket().returnMainPage()
                .chooseProductPage(5).addProductToBasket().goToBasket();
        String expected = basketPage.getActualFromTitleAddedProduct(1);
        Assert.assertEquals(firstExpected, expected);
        basketPage.deleteProductFromBasket(1);
        Assert.assertTrue(basketPage.isAbsentProductInBasket(expected));
    }
}
