import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.example.pages.CartPage;
import org.example.pages.MainPage;
import org.example.pages.ProductPage;

import org.testng.Assert;

import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "check that the name of the selected product is equivalent to the product in the cart")
    @Owner("Anna Matveenko")
    public void addProductToCart() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(3);
        String expected = productPage.getTitleProduct();
        CartPage basketPage = productPage.addProductToCart().goToCart();
        Assert.assertEquals(basketPage.getActualFromTitleAddedProduct(0), expected);
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "check that the name of the selected product has been removed " +
            "from the list of products in the cart")
    @Owner("Anna Matveenko")
    public void deleteProductFromCart() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(10);
       String firstExpected = productPage.getTitleProduct();
        CartPage basketPage = productPage.addProductToCart().returnMainPage()
                .chooseProductPage(5).addProductToCart().goToCart();
        String expected = basketPage.getActualFromTitleAddedProduct(1);
        Assert.assertEquals(firstExpected, expected);
        basketPage.deleteProductFromCart(1);
        Assert.assertTrue(basketPage.isAbsentProductInCart(expected));
    }
}
