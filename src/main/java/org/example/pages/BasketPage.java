package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class BasketPage extends BasePage{
    private Header header;
    @FindBy(xpath = "//div[@class='title']/a")
    private WebElement titleAddedProduct;
    public BasketPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
    }

}
