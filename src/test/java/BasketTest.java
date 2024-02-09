import org.apache.hc.core5.util.Asserts;
import org.example.pages.BasketPage;
import org.example.pages.MainPage;
import org.example.pages.ProductPage;

import org.testng.Assert;

import org.testng.annotations.Test;

public class BasketTest extends BaseTest {
    @Test(invocationCount = NUMBER_OF_STARTS)
    public void addProductToBasket() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(3);
        String expected = productPage.getTitleProduct();
        BasketPage basketPage = productPage.addProductToBasket().goToBasket();
        if (basketPage != null) {
            Assert.assertEquals(basketPage.getActualFromTitleAddedProduct(0), expected);
        } else {
            System.out.println("Failed to add product to basket after multiple attempts");
        }
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    public void deleteProductFromBasket() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(10);
        String firstExpected = productPage.getTitleProduct();
        BasketPage basketPage = productPage.addProductToBasket().returnMainPage()
                .chooseProductPage(5).addProductToBasket().goToBasket();
        if (basketPage != null) {
            String expected = basketPage.getActualFromTitleAddedProduct(1);
            Assert.assertEquals(firstExpected, expected);
            basketPage.deleteProductFromBasket(1);
            Assert.assertFalse(basketPage.isPresentProductInBasket(expected));

        } else {
            System.out.println("Failed to add product to basket after multiple attempts");
        }
    }
}
