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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.assertEquals;


public class ProlongPatientStay {
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
        //Login as practitioner
        myMethods.practitionerLogin();
    }

    @Test(priority = 1)
    public void ProlongStay() throws InterruptedException {
        //Click a patient to display patient details screen
        locators.firstPatient.click();

        //Click Prolong stay button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.prolongStayButton));
        locators.prolongStayButton.click();

        locators.dischargeDateField.sendKeys(futureDate());

        //Click Save
        locators.saveButtonProlong.click();

        //Verify confirmation message is displayed
        String dateDisplayed = locators.dischargeDatePatientDetails.getAttribute("value");
        assertEquals(dateDisplayed, futureDate());
    }

    @Test(priority = 2)
    public void ProlongStayUserRolesValidation() throws InterruptedException {
        //Click a patient to display patient details screen
        locators.firstPatient.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.prolongStayButton));
        locators.prolongStayButton.isDisplayed();

        //Logout
        locators.signOutNav.click();
        Thread.sleep(500);

        //Login as nurse
        myMethods.nurseLogin();
        //Click a patient to display patient details screen
        locators.firstPatient.click();

        wait.until(ExpectedConditions.visibilityOf(locators.prolongStayButton));
        locators.prolongStayButton.isDisplayed();

        //Logout
        locators.signOutNav.click();
        Thread.sleep(500);

        //Login as superadmin
        myMethods.superLogin();

        locators.patientListNav.click();
        Thread.sleep(1000);

        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.firstPatient)).apply(driver);
        Assert.assertNotEquals(notPresent, Boolean.TRUE);

        //Logout
        locators.signOutNav.click();
        Thread.sleep(500);

        //Login as receptionist
        myMethods.adminLogin();

        locators.patientListNav.click();
        Thread.sleep(1000);

        locators.firstPatient.click();
        Thread.sleep(500);
        Boolean notPresent2 = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.prolongStayButton)).apply(driver);
        Assert.assertNotEquals(notPresent2, Boolean.TRUE);
    }

    @Test(priority = 3)
    public void ProlongSNegativePast() throws InterruptedException {
        //Click a patient to display patient details screen
        locators.secondPatient.click();

        //Click Prolong stay button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.prolongStayButton));
        locators.prolongStayButton.click();

        //Enter a past date
        locators.datePickerProlong.click();
        locators.previousMonthButton.click();
        locators.previousMonthButton.click();
        locators.firstDate.click();

        //Verify there's an error for past date
        locators.pastDateError.isDisplayed();
    }

    @Test(priority = 4)
    public void ProlongSNegativeInvalid() throws InterruptedException {
        //Click a patient to display patient details screen
        locators.secondPatient.click();

        //Click Prolong stay button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.prolongStayButton));
        locators.prolongStayButton.click();

        //Enter an invalid date
        locators.dischargeDateField.sendKeys(BasicParams.invalidDate1);

        //Click Save
        locators.saveButtonProlong.click();

        //Verify there's an error for invalid date
        locators.invalidDateError.isDisplayed();

        locators.cancelButton.click();

        locators.prolongStayButton.click();

        //Enter an invalid date
        Thread.sleep(500);
        locators.dischargeDateField.sendKeys(BasicParams.invalidDate2);

        //Click Save
        locators.saveButtonProlong.click();

        //Verify there's an error for invalid date
        locators.invalidDateError.isDisplayed();

        locators.cancelButton.click();

        locators.prolongStayButton.click();

        //Enter an invalid date
        Thread.sleep(500);
        locators.dischargeDateField.sendKeys(BasicParams.invalidDate3);

        //Click Save
        locators.saveButtonProlong.click();

        //Verify there's an error for invalid date
        locators.invalidDateError.isDisplayed();

        locators.cancelButton.click();

    }

    @Test(priority = 5)
    public void ProlongSCloseCancel() throws InterruptedException {
        //Click a patient to display patient details screen
        locators.firstPatient.click();

        //Click Prolong stay button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.prolongStayButton));
        locators.prolongStayButton.click();
        Thread.sleep(500);
        locators.cancelButton.click();


        locators.prolongStayButton.click();
        Thread.sleep(500);
        locators.closeButton.click();

        wait.until(ExpectedConditions.invisibilityOf(locators.closeButton));
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.closeButton)).apply(driver);
        Assert.assertNotEquals(notPresent, Boolean.TRUE);

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

    public static String futureDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,2);
        cal.add(Calendar.MONTH,2);
        cal.add(Calendar.YEAR,1);

        Date myFutureDate = cal.getTime();

        SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
        return myFormat.format(myFutureDate);
    };
}
