import org.example.pages.BasketPage;
import org.example.pages.MainPage;
import org.example.pages.ProductPage;

import org.testng.Assert;

import org.testng.annotations.Test;

public class BasketTest extends BaseTest{
    @Test
    public void addProductToBasket() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(3);
        String expected = productPage.getTitleProduct();
        BasketPage basketPage = productPage.addProductToBasket().goToBasket();
        Assert.assertEquals(basketPage.getTitleAddedProduct().getText().toLowerCase(), expected);

    }
    @Test
    public void deleteProductFromBasket() {
        MainPage mainPage = new MainPage(driver);
            ProductPage productPage = mainPage.chooseProductPage(3);
            BasketPage basketPage = productPage.addProductToBasket().returnMainPage()
                    .chooseProductPage(5).addProductToBasket().goToBasket();
            basketPage.deleteProductFromBasket(0);
            Assert.assertTrue(basketPage.isLocationOnBasketPage());
          }
}
