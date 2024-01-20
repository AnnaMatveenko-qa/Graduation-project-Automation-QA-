package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
public class ProductPage extends BasePage{

    @FindBy(xpath = "(//input[@class='eldo-input'])[1]")
    private WebElement valueInputCity;


    public ProductPage(WebDriver driver) {
        super(driver);
    }




}
