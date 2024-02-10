import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;


public abstract class BaseTest {
    protected WebDriver driver;
    protected final int NUMBER_OF_STARTS = 5;
   @BeforeSuite
    public void setupClass() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void InitDriver() throws RuntimeException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        this.driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.get("https://eldorado.ua/uk/holodilniki/c1061560/");


    }

    @AfterMethod
    public void TearDown() throws RuntimeException {
        driver.manage().deleteAllCookies();
        this.driver.quit();

    }

    @AfterSuite
    public void tearDownClass() {
        WebDriverManager.chromedriver().quit();
    }
}
