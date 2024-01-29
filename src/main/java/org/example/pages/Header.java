package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
public class Header extends BasePage{
    public Header(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[@data-name='cart-icon']")
    private WebElement linkBasket;


}
