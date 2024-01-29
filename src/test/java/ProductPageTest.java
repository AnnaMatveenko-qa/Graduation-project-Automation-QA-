import org.example.pages.BasketPage;
import org.example.pages.MainPage;
import org.example.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {
    @Test
    public void addProductToCart() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(3);
        String expected = productPage.getTitleProduct();
        BasketPage basketPage = productPage.addProductToBasket().goToBasket();
        Assert.assertEquals(basketPage.getTitleAddedProduct().getText().toLowerCase(), expected);

    }


}
