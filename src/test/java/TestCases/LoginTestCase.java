package TestCases;

import TestData.BasicParams;
import TestData.Locators;
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
import java.time.Duration;

public class LoginTestCase {
    private WebDriver driver;
    BasicParams parameters;
    Locators locators;

    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        parameters = new BasicParams(driver);
        locators = new Locators(driver);
        String baseUrl = BasicParams.testingApp;

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(baseUrl);
    }

    @BeforeMethod
    public void beforeMethod() {

    }

    @Test(priority = 1)
    public void LoginSuperAdmin() {
        //Enter valid username
        locators.usernameField.sendKeys(BasicParams.superAdminUsername);
        //Enter valid password
        locators.passwordField.sendKeys(BasicParams.superAdminPassword);
        //Click Sign in button
        locators.loginButton.click();
        //Verify Create room button is visible
        locators.createRoomButton.isDisplayed();
        //Verify departments tab is displayed on dashboard
        locators.departmentsTab.isDisplayed();
        //Verify rooms tab is displayed on dashboard
        locators.roomsTab.isDisplayed();
        //Verify beds tab is displayed on dashboard
        locators.bedsTab.isDisplayed();
        //Verify patients tab is displayed on dashboard
        locators.patientsTab.isDisplayed();
        //Verify users tab is displayed on dashboard
        locators.usersTab.isDisplayed();

        //Verify hospital overview is displayed in the navigation menu
        locators.hospitalOverviewNav.isDisplayed();
        //Sign out
        locators.signOutNav.click();
    }

    @Test(priority = 2)
    public void LoginReceptionist() {
        //Enter valid username
        locators.usernameField.sendKeys(BasicParams.receptionistUsername);
        //Enter valid password
        locators.passwordField.sendKeys(BasicParams.receptionistPassword);
        //Click Sign in button
        locators.loginButton.click();
        //Verify hospital overview is displayed in the navigation menu
        locators.hospitalOverviewNav.isDisplayed();

        //Verify hospital overview is displayed in the navigation menu
        locators.hospitalOverviewNav.isDisplayed();
        //Verify Create room button is not visible
        //boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(parameters.createRoomButton)).equals(false);
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.createRoomButton)).apply(driver);
        Assert.assertNotEquals(notPresent, Boolean.TRUE);
        //Sign out
        locators.signOutNav.click();
    }

    @Test(priority = 3)
    public void LoginDoc() {
        //Enter valid username
        locators.usernameField.sendKeys(BasicParams.docUsername);
        //Enter valid password
        locators.passwordField.sendKeys(BasicParams.docPassword);
        //Click Sign in button
        locators.loginButton.click();
        //Verify Create room button is not visible
        //boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(parameters.createRoomButton)).equals(false);
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.createRoomButton)).apply(driver);
        Assert.assertNotEquals(notPresent, Boolean.TRUE);
        //Verify patient list is displayed on dashboard
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.patientListSectionTitle));
        locators.patientListSectionTitle.isDisplayed();
        //Verify Patient list is displayed in the navigation menu
        locators.patientListNav.isDisplayed();
        //Sign out
        locators.signOutNav.click();
    }

    @Test(priority = 4)
    public void LoginNurse() {
        //Enter valid username
        locators.usernameField.sendKeys(BasicParams.nurseUsername);
        //Enter valid password
        locators.passwordField.sendKeys(BasicParams.nursePassword);
        //Click Sign in button
        locators.loginButton.click();
        //Verify Create room button is not visible
        //boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(parameters.createRoomButton)).equals(false);
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.createRoomButton)).apply(driver);
        Assert.assertNotEquals(notPresent, Boolean.TRUE);
        //Verify patient list is displayed on dashboard
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.patientListSectionTitle));
        locators.patientListSectionTitle.isDisplayed();
        //Verify Patient list is displayed in the navigation menu
        locators.patientListNav.isDisplayed();
        //Sign out
        locators.signOutNav.click();
    }

    @Test(priority = 5)
    public void LoginMaskUnmaskPassword() {
        //Enter some password
        locators.passwordField.sendKeys(BasicParams.incorrectPassword);
        //Verify the password is masked
        locators.maskedPassword.isDisplayed();
        //Click to unmask / mask password button
        locators.unmaskButton.click();
        //Verify the password is unmasked
        locators.maskButton.isDisplayed();
        //Click to unmask / mask password button
        locators.unmaskButton.click();
        //Verify the password is masked
        locators.maskedPassword.isDisplayed();
        locators.unmaskButton.isDisplayed();
    }

    @Test(priority = 6)
    public void LoginNoInput() {
        //Enter valid username
        locators.usernameField.clear();
        locators.passwordField.clear();
        //Click Sign in button
        locators.loginButton.click();
        //Verify error message is displayed
        locators.loginError.isDisplayed();

    }

    @Test(priority = 7)
    public void LoginIncorrectPassword() {
        //Enter valid username
        locators.usernameField.sendKeys(BasicParams.superAdminUsername);
        //Enter incorrect password
        locators.passwordField.sendKeys(BasicParams.incorrectPassword);
        //Click Sign in button
        locators.loginButton.click();
        //Verify error message is displayed
        locators.loginError.isDisplayed();

    }

    @AfterTest
    public void afterTest() {
        driver.manage().deleteAllCookies();
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
