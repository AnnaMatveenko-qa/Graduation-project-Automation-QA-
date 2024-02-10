package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class PopUpProductInCart extends BasePage{
    public PopUpProductInCart(WebDriver driver) {
        super(driver);
    }
@FindBy(xpath = "//div[@class='rodal']//span[@class='rodal-close']")
    private WebElement closePopUp;
}
