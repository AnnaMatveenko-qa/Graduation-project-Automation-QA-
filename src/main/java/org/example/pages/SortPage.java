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
    @FindBy(xpath = "//div[@name='catalog-top']//div[contains(@class,'StyledSortingDesktopstyled__StyledFilterItem')]" +
            "/div[contains(@class,'ui-library')]")
    private List<WebElement> sortsNames;


    public SortPage(WebDriver driver) {
        super(driver);
    }

    public SortPage chooseSortName(Integer index) {
        try {
            sortsNames.get(index).click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            sortsNames.get(index).click();
        }
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.elementToBeClickable(sortsNames.get(index)));


        return this;

    }


}
