package org.example.pages;


import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

@Getter
public class SortPage extends BasePage {
    @FindBy(xpath = "//div[@name='catalog-top']//div[contains(@class,'StyledSortingDesktopstyled__StyledFilterItem')]")
    private List<WebElement> sortsNames;


    public SortPage(WebDriver driver) {
        super(driver);
    }

    public SortPage chooseSortName(Integer index) {
        sortsNames.get(index).click();
        sortsNames.get(index).isDisplayed();
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(sortsNames.get(index)));
        return this;
    }
}
