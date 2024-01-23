import org.example.pages.MainPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SortingTest extends BaseTest {


    @Test
    public void SortByIncreasePrice() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(1);
        Assert.assertTrue(mainPage.comparePricesOrderOfIncrease());
    }

    @Test
    public void SortByDecreasePrice() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(1).chooseSortName(1);
        Assert.assertTrue(mainPage.comparePricesDescendingOrder(1));
    }
}
