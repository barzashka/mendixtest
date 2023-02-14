package TestCases;

import TestData.BasicParams;
import TestData.Locators;
import TestData.StandardMethods;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.assertNotEquals;

public class Discharge {
    private WebDriver driver;
    BasicParams parameters;
    Locators locators;
    StandardMethods myMethods;

    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        parameters = new BasicParams(driver);
        locators = new Locators(driver);
        myMethods = new StandardMethods(driver);
        String baseUrl = BasicParams.testingApp;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseUrl);
    }

    @BeforeMethod
    public void beforeMethod() {
        //Login as Super Admin
        myMethods.superLogin();
    }

    @Test(priority = 1)
    public void DischargePositive() {
        //Click on a room that is occupied
        locators.occupiedRoom.click();
        //Click on an occupied bed
        locators.occupiedBed.click();
        //Click Discharge button
        locators.dischargeButton.click();
        //Enter a comment about the discharge
        locators.dischargeComment.sendKeys(BasicParams.dischargeReason);
        //Click Confirm button
        locators.confirmButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(locators.dischargeComment));
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.dischargeComment)).apply(driver);
        assertNotEquals(notPresent, Boolean.TRUE);

    }

    @Test(priority = 2)
    public void DischargeNegative() {
        //Open Discharge modal
        locators.occupiedRoom.click();
        //locators.occupiedBed.click();
        locators.occupiedBed.click();
        //Click Discharge button
        locators.dischargeButton.click();

        //Click Confirm button
        locators.confirmButton.click();
        //Verify there's an error for missing comment
        locators.commentError.isDisplayed();

    }

    @Test(priority = 3)
    public void DischargeCancelClose() {
        //Click on a room that is occupied
        locators.occupiedRoom.click();
        //Click on an occupied bed
        locators.occupiedBed.click();
        //Click Discharge button
        locators.dischargeButton.click();
        //Click Cancel
        locators.cancelButton.click();

        //Open hospital overview
        locators.hospitalOverviewNav.click();

        //Click on a room that is occupied
        locators.occupiedRoom.click();
        //Click on an occupied bed
        locators.occupiedBed.click();
        //Click Discharge button
        locators.dischargeButton.click();
        //Click Close
        locators.closeButton.click();


    }


    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed: " + testResult.getMethod().getMethodName());
            String filename = testResult.getMethod().getMethodName() + getRandomString(10) + ".png";
            String directory = System.getProperty("user.dir") + "//screenshots//";
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(directory + filename));
        }

        driver.navigate().refresh();
        locators.signOutNav.click();
    }

    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

}
