import io.qameta.allure.Description;
import org.example.pages.MainPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SortingTest extends BaseTest {


    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "Сheck that the products are arranged in ascending order of price")
    public void sortByIncreasePrice() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(1);
        Assert.assertTrue(mainPage.comparePricesOrderOfIncrease());
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "Сheck that the products are arranged in descending order of price")
    public void sortByDecreasePrice() {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSortPage().chooseSortName(1).chooseSortName(1);
        Assert.assertTrue(mainPage.comparePricesDescendingOrder());
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "Сheck that the products are arranged by name in direct order")
    public void sortBeNameUp() {
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseSortAndRetrieveTitles(2);
        Assert.assertTrue(mainPage.comparisonProductHeaderSorting());
    }

    @Test(invocationCount = NUMBER_OF_STARTS)
    @Description(value = "check that the products are arranged by name in reverse order")
        public void sortBeNameDown() {
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseSortAndRetrieveTitlesInReverseOrder(2);
        Assert.assertTrue(mainPage.comparisonReverseSortingProductTitles());
    }
}