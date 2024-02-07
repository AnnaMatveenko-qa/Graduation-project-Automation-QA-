package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class PopUpProduct extends BasePage{
    public PopUpProduct(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='order-cart']//div[@class='Modalstyled__ModalCloseButton-sc-1o9ej3w-0 dGwrDB']")
    private WebElement buttonClosedPopUp;


public void closePopUp(){
    buttonClosedPopUp.click();
}


}
