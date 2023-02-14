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

public class Invite {
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
    public void InviteSuperAdmin() throws InterruptedException {
        //Click Admin Panel in the header
        locators.adminPanelNav.click();
        //Click Users in the submenu
        locators.usersNav.click();
        //Click Invite button
        locators.inviteButton.click();
        //Enter valid email
        Thread.sleep(500);
        String emailSuperAdmin = myMethods.emailValidScriptSuperAdmin();
        locators.emailField.sendKeys(emailSuperAdmin);
        //Click user type dropdown
        locators.userTypeDropdown.click();
        //Click superadmin option from dropdown
        locators.superadminOption.click();
        //Click to confirm
        locators.confirmButton.click();
        //Verify confirmation message is displayed
        locators.confirmationMessage.isDisplayed();
        //Click Ok button to close the modal
        locators.okButton.click();
    }

    @Test(priority = 2)
    public void InviteAdmin() throws InterruptedException {
        //Click Admin Panel in the header
        locators.adminPanelNav.click();
        //Click Users in the submenu
        locators.usersNav.click();
        //Click Invite button
        locators.inviteButton.click();
        //Enter valid email
        Thread.sleep(500);
        String emailAdmin = myMethods.emailValidScriptAdmin();
        locators.emailField.sendKeys(emailAdmin);
        //Click user type dropdown
        locators.userTypeDropdown.click();
        //Click admin option from dropdown
        locators.adminOption.click();
        //Click to confirm
        locators.confirmButton.click();
        //Verify confirmation message is displayed
        locators.confirmationMessage.isDisplayed();
        //Click Ok button to close the modal
        locators.okButton.click();
    }

    @Test(priority = 3)
    public void InvitePractitioner() throws InterruptedException {
        //Click Admin Panel in the header
        locators.adminPanelNav.click();
        //Click Users in the submenu
        locators.usersNav.click();
        //Click Invite button
        locators.inviteButton.click();
        //Enter valid email
        Thread.sleep(500);
        String emailDoc = myMethods.emailValidScriptPractitioner();
        locators.emailField.sendKeys(emailDoc);
        //Click user type dropdown
        locators.userTypeDropdown.click();
        //Click practitioner option from dropdown
        locators.docOption.click();
        //Select an option from department dropdown
        locators.departmentDropdownInvite.click();
        locators.cardiologyOptionInvite.click();
        //Click to confirm
        locators.confirmButton.click();
        //Verify confirmation message is displayed
        locators.confirmationMessage.isDisplayed();
        //Click Ok button to close the modal
        locators.okButton.click();
    }

    @Test(priority = 4)
    public void InviteNurse() throws InterruptedException {
        //Click Admin Panel in the header
        locators.adminPanelNav.click();
        //Click Users in the submenu
        locators.usersNav.click();
        //Click Invite button
        locators.inviteButton.click();
        //Enter valid email
        Thread.sleep(500);
        String emailNurse = myMethods.emailValidScriptNurse();
        locators.emailField.sendKeys(emailNurse);
        //Click user type dropdown
        locators.userTypeDropdown.click();
        //Click nurse option from dropdown
        locators.nurseOption.click();
        //Select an option from department dropdown
        locators.departmentDropdownInvite.click();
        locators.psychiatryOptionInvite.click();
        //Click to confirm
        locators.confirmButton.click();
        //Verify confirmation message is displayed
        locators.confirmationMessage.isDisplayed();
        //Click Ok button to close the modal
        locators.okButton.click();
    }

    @Test(priority = 5)
    public void InviteVerifyRoles() throws InterruptedException {
        //Click Admin Panel in the header
        locators.adminPanelNav.click();
        //Click Users in the submenu
        locators.usersNav.click();

        //Verify invite button is enabled and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locators.inviteButton));
        Thread.sleep(500);
        locators.inviteButton.isDisplayed();

        //Sign out and log in as receptionist
        locators.signOutNav.click();
        Thread.sleep(500);
        myMethods.adminLogin();
        Thread.sleep(500);
        wait.until(ExpectedConditions.invisibilityOf(locators.adminPanelNav));
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.adminPanelNav)).apply(driver);
        assertNotEquals(notPresent, Boolean.TRUE);

        //Sign out and log in as doctor
        locators.signOutNav.click();
        Thread.sleep(500);
        myMethods.practitionerLogin();
        Thread.sleep(500);
        wait.until(ExpectedConditions.invisibilityOf(locators.adminPanelNav));
        Boolean notPresent2 = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.adminPanelNav)).apply(driver);
        assertNotEquals(notPresent2, Boolean.TRUE);

        //Sign out and log in as nurse
        Thread.sleep(500);
        locators.signOutNav.click();
        Thread.sleep(500);
        myMethods.nurseLogin();
        wait.until(ExpectedConditions.invisibilityOf(locators.adminPanelNav));
        Boolean notPresent3 = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.adminPanelNav)).apply(driver);
        assertNotEquals(notPresent3, Boolean.TRUE);
    }

    @Test(priority = 6)
    public void InviteNegative() throws Exception {
        //Click Admin Panel in the header
        locators.adminPanelNav.click();
        //Click Users in the submenu
        locators.usersNav.click();
        //Click Invite button
        locators.inviteButton.click();
        //Click confirm button
        locators.confirmButton.click();
        //Verify there's an error that email field is required
        locators.emailRequiredError.isDisplayed();
        //Verify there's an error that user type field is required
        locators.userTypeRequiredError.isDisplayed();

        //Enter invalid email, select user type and click Confirm
        locators.emailField.clear();
        locators.emailField.sendKeys(BasicParams.invalidEmail1);
        locators.userTypeDropdown.click();
        locators.superadminOption.click();
        locators.confirmButton.click();
        //Verify there's an error for invalid email
        locators.invalidEmailError.isDisplayed();
        //Click Cancel
        locators.cancelButton.click();

        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        //Enter invalid email, select user type and click Confirm
        locators.emailField.clear();
        locators.emailField.sendKeys(BasicParams.invalidEmail2);
        locators.userTypeDropdown.click();
        locators.superadminOption.click();
        locators.confirmButton.click();
        //Verify there's an error for invalid email
        locators.invalidEmailError.isDisplayed();
        //Click Cancel
        locators.cancelButton.click();

        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        //Enter invalid email, select user type and click Confirm
        locators.emailField.clear();
        locators.emailField.sendKeys(BasicParams.invalidEmail3);
        locators.userTypeDropdown.click();
        locators.superadminOption.click();
        locators.confirmButton.click();
        //Verify there's an error for invalid email
        locators.invalidEmailError.isDisplayed();
        //Click Cancel
        locators.cancelButton.click();

        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        //Enter invalid email, select user type and click Confirm
        locators.emailField.clear();
        locators.emailField.sendKeys(BasicParams.invalidEmail4);
        locators.userTypeDropdown.click();
        locators.superadminOption.click();
        locators.confirmButton.click();
        //Verify there's an error for invalid email
        locators.invalidEmailError.isDisplayed();
        //Click Cancel
        locators.cancelButton.click();

        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        //Enter invalid email, select user type and click Confirm
        locators.emailField.clear();
        locators.emailField.sendKeys(BasicParams.invalidEmail5);
        locators.userTypeDropdown.click();
        locators.superadminOption.click();
        locators.confirmButton.click();
        //Verify there's an error for invalid email
        locators.invalidEmailError.isDisplayed();
        //Click Cancel
        locators.cancelButton.click();

        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        //Enter invalid email, select user type and click Confirm
        locators.emailField.clear();
        locators.emailField.sendKeys(BasicParams.invalidEmail6);
        locators.userTypeDropdown.click();
        locators.superadminOption.click();
        locators.confirmButton.click();
        //Verify there's an error for invalid email
        locators.invalidEmailError.isDisplayed();
        //Click Cancel
        locators.cancelButton.click();

        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        //Enter invalid email, select user type and click Confirm
        locators.emailField.clear();
        locators.emailField.sendKeys(BasicParams.invalidEmail7);
        locators.userTypeDropdown.click();
        locators.superadminOption.click();
        locators.confirmButton.click();
        //Verify there's an error for invalid email
        locators.invalidEmailError.isDisplayed();
        //Click Cancel
        locators.cancelButton.click();

        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        //Enter invalid email, select user type and click Confirm
        locators.emailField.clear();
        locators.emailField.sendKeys(BasicParams.invalidEmail8);
        locators.userTypeDropdown.click();
        locators.superadminOption.click();
        locators.confirmButton.click();
        //Verify there's an error for invalid email
        locators.invalidEmailError.isDisplayed();
        //Click Cancel
        locators.cancelButton.click();

        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        //Enter invalid email, select user type and click Confirm
        locators.emailField.clear();
        locators.emailField.sendKeys(BasicParams.existingEmail);
        locators.userTypeDropdown.click();
        locators.superadminOption.click();
        locators.confirmButton.click();
        //Verify there's an error for existing email
        locators.existingEmailError.isDisplayed();
        //Click Cancel
        locators.cancelButton.click();
    }

    @Test(priority = 7)
    public void InviteCloseCancel() throws InterruptedException {
        //Click Admin Panel in the header
        locators.adminPanelNav.click();
        //Click Users in the submenu
        locators.usersNav.click();
        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        locators.cancelButton.click();

        //Click Invite button
        locators.inviteButton.click();
        Thread.sleep(500);

        locators.closeButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(locators.closeButton));
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.closeButton)).apply(driver);
        assertNotEquals(notPresent, Boolean.TRUE);
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
