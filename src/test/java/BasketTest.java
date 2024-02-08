import org.example.pages.BasketPage;
import org.example.pages.MainPage;
import org.example.pages.ProductPage;

import org.testng.Assert;

import org.testng.annotations.Test;

public class BasketTest extends BaseTest{
    @Test(invocationCount = NUMBER_OF_STARTS)
    public void addProductToBasket() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(3);
        String expected = productPage.getTitleProduct();
        BasketPage basketPage = productPage.addProductToBasket().goToBasket();
        Assert.assertEquals(basketPage.getActualFromTitleAddedProduct(), expected);

    }
    @Test(invocationCount = NUMBER_OF_STARTS)
    public void deleteProductFromBasket() {
        MainPage mainPage = new MainPage(driver);
            ProductPage productPage = mainPage.chooseProductPage(3);
            BasketPage basketPage = productPage.addProductToBasket().returnMainPage()
                    .chooseProductPage(5).addProductToBasket().goToBasket();
            basketPage.deleteProductFromBasket(0);
            Assert.assertTrue(basketPage.isLocationOnBasketPage());
          }
}
