import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.example.pages.MainPage;
import org.example.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




public class ProductTest extends BaseTest {
    @DataProvider(name = "Product status")
    public Object[] productStatus() {
        return new Object[]{
               0,1,2,3,22,15,38
        };
    }


    @Test( dataProvider = "Product status", invocationCount = NUMBER_OF_STARTS)
    @Description(value = "check for product status on the product page")
    @Owner("Anna Matveenko")
    public void checkProductStatus(Integer index) {
        String fileName = "inputText.txt";
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = mainPage.chooseProductPage(index);
        Assert.assertTrue(productPage.statusProduct());
        productPage.writeText(fileName, productPage.getTitleProduct() + "  " + productPage.getProductStatus().getText());

    }


}