import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public abstract class BaseTest {
    protected WebDriver driver;
    protected final int NUMBER_OF_STARTS = 1;

    @BeforeSuite
    public void setupClass() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeClass(groups = "product")
    public void deleteFileIfExists() {
        String projectDirectory = System.getProperty("src/main/resources");
        String fileName = "ProductWithStatusAndName.txt";
        File file = new File(projectDirectory + File.separator + fileName);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                System.out.println("Failed to delete the file: " + fileName);
            }
        }
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
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            takeScreenshot(result);
        }
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }

    private void takeScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                setBrowserZoom(driver, 80);
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotName = result.getName() + "_screenshot.png";
                FileUtils.copyFile(screenshot, new File("src/main/resources/" + screenshotName));
                System.out.println("Screenshot captured: " + screenshotName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void setBrowserZoom(WebDriver driver, double zoomLevel) {
        String script = "document.body.style.zoom='" + zoomLevel + "%'";
        ((JavascriptExecutor) driver).executeScript(script);
    }


    @AfterSuite
    public void tearDownClass() {
        WebDriverManager.chromedriver().quit();
    }
}