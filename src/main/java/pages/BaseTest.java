package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.time.Duration;


public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void InitDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://eldorado.ua/uk/holodilniki/c1061560/");

    }

    @AfterMethod
    public void TearDown() {
        driver.quit();
    }

}
