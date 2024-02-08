import org.example.pages.MainPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SortingTest extends BaseTest {


    @Test(invocationCount = NUMBER_OF_STARTS)
    public void sortByIncreasePrice() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(1);
        Assert.assertTrue(mainPage.comparePricesOrderOfIncrease());
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    public void sortByDecreasePrice() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(1).chooseSortName(1);
        Assert.assertTrue(mainPage.comparePricesDescendingOrder());
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    public void sortBeNameUp() {
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseSortAndRetrieveTitles(2);
        Assert.assertTrue(mainPage.comparisonProductHeaderSorting());
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    public void sortBeNameDown() {
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseSortAndRetrieveTitlesInReverseOrder(2);
        Assert.assertTrue(mainPage.comparisonReverseSortingProductTitles());
    }
}