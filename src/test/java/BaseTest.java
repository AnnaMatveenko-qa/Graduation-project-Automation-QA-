import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;


public class BaseTest {
    protected WebDriver driver;
    protected final int NUMBER_OF_STARTS = 3;


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
        try {
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1600, 900));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.get("https://eldorado.ua/uk/holodilniki/c1061560/");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage());
        }
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            deleteScreenshotIfExists(result);
            takeScreenshot(result);
        }
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
        ITestContext context = result.getTestContext();
        ITestNGMethod[] testMethods = context.getAllTestMethods();
        for (ITestNGMethod testMethod : testMethods) {
            if (Arrays.asList(testMethod.getGroups()).contains("product")) {
                attachFileToAllureReport();
            }
        }
    }


    private byte[] takeScreenshot(ITestResult result) {
        try {
            setBrowserZoom(driver, 80);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshot);
            saveScreenshot(screenshot, result.getName() + "_screenshot.png");
            return screenshotBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private void saveScreenshot(File screenshot, String fileName) {
        try {
            String directory = "src/main/resources/";
            File directoryPath = new File(directory);
            if (!directoryPath.exists()) {
                directoryPath.mkdirs();
            }
            FileUtils.copyFile(screenshot, new File(directory + fileName));
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(new File(directory + fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void attachFileToAllureReport() {
        String directoryPath = "src/main/resources";
        String fileName = "ProductWithStatusAndName.txt";
        String filePath = directoryPath + File.separator + fileName;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            Allure.addAttachment("Additional Data", "text/plain", inputStream, "txt");
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }

    private void setBrowserZoom(WebDriver driver, double zoomLevel) {
        String script = "document.body.style.zoom='" + zoomLevel + "%'";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    private void deleteScreenshotIfExists(ITestResult result) {
        String directory = "src/main/resources/";
        String fileName = result.getName() + "_screenshot.png";
        File screenshotFile = new File(directory + fileName);
        if (screenshotFile.exists()) {
            boolean deleted = screenshotFile.delete();
            if (deleted) {
                System.out.println("Screenshot file deleted successfully.");
            } else {
                System.out.println("Failed to delete the screenshot file.");
            }
        }
    }

    @AfterSuite
    public void tearDownClass() {
        WebDriverManager.chromedriver().quit();
    }


}