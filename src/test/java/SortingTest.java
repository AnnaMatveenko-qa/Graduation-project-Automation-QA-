import org.example.pages.MainPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SortingTest extends BaseTest {


    @Test
    public void sortByIncreasePrice() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(1);
        Assert.assertTrue(mainPage.comparePricesOrderOfIncrease());
    }

    @Test
    public void sortByDecreasePrice() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(1).chooseSortName(1);
        Assert.assertTrue(mainPage.comparePricesDescendingOrder());
    }

    @Test
    public void sortBeNameUp() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(2);
        Assert.assertTrue(mainPage.comparisonProductHeaderSorting());
    }

    @Test
    public void sortBeNameDown() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(2).chooseSortName(2);
        Assert.assertTrue(mainPage.comparisonReverseSortingProductTitles());
    }
}