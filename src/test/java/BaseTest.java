import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;


public abstract class BaseTest {
    protected WebDriver driver;

   @BeforeSuite
    public void setupClass() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void InitDriver() throws RuntimeException {


        this.driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.get("https://eldorado.ua/uk/holodilniki/c1061560/");


    }

    @AfterMethod
    public void TearDown() throws RuntimeException {
        driver.quit();

    }

    @AfterSuite
    public void tearDownClass() {
        WebDriverManager.chromedriver().quit();
    }
}
