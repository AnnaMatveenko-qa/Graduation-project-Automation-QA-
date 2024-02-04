import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;


public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void InitDriver() throws RuntimeException{
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-debugging-pipe");
      //  options.addArguments("--windows-size=1920,1080");
        options.addArguments("--disable-dev-shm-usage");
       options.addArguments("--headless");
        driver = new ChromeDriver(options);
      // driver.manage().window().setSize(new Dimension(1600,900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://eldorado.ua/uk/holodilniki/c1061560/");


    }

    @AfterMethod
    public void TearDown() throws RuntimeException {
        driver.quit();
       WebDriverManager.chromedriver().quit();
    }

}
