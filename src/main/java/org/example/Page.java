package org.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page {
      @FindBy(xpath = "//div[@id='city']/span")
      private WebElement availInСity;
      @FindBy(xpath = "//div[@id='discounted_item']/span")
      private WebElement productCond;
      @FindBy(xpath = "//div[@id='producer']/span")
      private WebElement producer;
      @FindBy(xpath = "//div[@id='price']/localhost 8080span")
      private WebElement price;
      @FindBy(xpath = "(//div[@role='button']/span)[5]")
      private WebElement typeProduct;
      @FindBy(xpath = "(//div[@role='button']/span)[6]")
      private WebElement height;
      @FindBy(xpath = "(//div[@role='button']/span)[7]")
      private WebElement width;
      @FindBy(xpath = "(//div[@role='button']/span)[8]")
      private WebElement depth;

    @FindBy(xpath = "(//div[@role='button']/span)[9]")
    private WebElement coolingSystemRefrigChambert;
    @FindBy(xpath = "(//div[@role='button']/span)[10]")
    private WebElement freezcoolsyst;
    @FindBy(xpath = "(//div[@role='button']/span)[11]")
    private WebElement bodyColor;
    @FindBy(xpath = "//div[@id='11540']")
    private WebElement button;
    @FindBy(xpath = "(//div[@id='page-footer-content-id']//div[2]/label/span/img)[8]")
    private WebElement staticСoolingSystemRefrigChambert;

}
