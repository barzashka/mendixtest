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
import java.time.Duration;

import static org.testng.Assert.assertNotEquals;

public class PatientRegister {
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
        //Login as receptionist
        myMethods.adminLogin();
    }

    @Test(priority = 1)
    public void PatientRegisterPositive() throws InterruptedException {
        //Click Register patient button
        locators.patientListNav.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        //Create and enter a valid and unique SSN
        String validSSN = myMethods.ssnValidScript();
        locators.ssnField.sendKeys(validSSN);
        //Click Search button
        locators.searchSsnButton.click();
        //Create and enter first name
        Thread.sleep(500);
        String firstName = myMethods.firstNameValidScript();
        locators.firstNameField.sendKeys(firstName);
        //Create and enter last name
        String lastName = myMethods.lastNameValidScript();
        locators.lastNameField.sendKeys(lastName);
        //Create and enter phone
        String phone = myMethods.phoneValidScript();
        locators.phoneField.sendKeys(phone);
        //Click Save button to register the patient
        locators.saveButton.click();
        //Enter valid contact person
        Thread.sleep(500);
        locators.contactField.sendKeys(BasicParams.contactPerson);
        //Enter a reason
        locators.reasonField.sendKeys(BasicParams.reasonValid);
        //Enter allergies
        locators.allergiesField.sendKeys(BasicParams.allergies);
        //Select a department
        locators.departmentRegister.click();
        locators.cardiologyDepDropdown.click();
        //Click Calendar icon
        locators.calendarIconArea.click();
        //Click on a date
        locators.lastDate.click();
        //Click disease code field
        locators.diseaseCodeDropdown.click();
        //Enter valid disease code in the search field
        Thread.sleep(500);
        locators.diseaseCodeSearchField.sendKeys(BasicParams.diseaseCodeValid);
        //Click to select a disease code from search results
        locators.diseaseCodeSearchResult.click();
        //Click Save button to register the patient
        locators.saveButton.click();
        //Verify add bed modal is displayed
        locators.addBedHeader.isDisplayed();
        //Click on a bed to select
        locators.bed1.click();
        //Verify confirmation message that the patient is admitted is displayed
        locators.patientAdmittedMessage.isDisplayed();
        //Click on Ok button to close the confirmation modal
        locators.okButton.click();
    }

    @Test(priority = 2)
    public void PatientRegisterUserRolesValidation() throws InterruptedException {
        locators.patientListNav.click();

        //Verify Register patient button is displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.isDisplayed();

        //Click to sign out
        locators.signOutNav.click();

        //Click to log in as a superadmin
        myMethods.superLogin();

        locators.patientListNav.click();

        //Verify Register patient button is not displayed
        wait.until(ExpectedConditions.invisibilityOf(locators.registerPatientButton));
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.registerPatientButton)).apply(driver);
        assertNotEquals(notPresent, Boolean.TRUE);

        //Click to sign out
        Thread.sleep(1000);
        locators.signOutNav.click();

        //Login as a doctor
        Thread.sleep(500);
        myMethods.practitionerLogin();

        //Verify Register patient button is not displayed
        wait.until(ExpectedConditions.invisibilityOf(locators.registerPatientButton));
        assertNotEquals(notPresent, Boolean.TRUE);
        //Click to sign out
        Thread.sleep(1000);
        locators.signOutNav.click();

        //Login as a nurse
        Thread.sleep(500);
        myMethods.nurseLogin();
        //Verify Register patient button is not displayed
        wait.until(ExpectedConditions.invisibilityOf(locators.registerPatientButton));
        assertNotEquals(notPresent, Boolean.TRUE);

    }

    @Test(priority = 3)
    public void PatientAlreadyAdmitted() throws InterruptedException {
        locators.patientListNav.click();

        //Click on Register patient button in the navigation menu
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        //Enter valid SSN of a patient that is already admitted
        locators.ssnField.sendKeys(BasicParams.registeredPatient);
        //Click on Search button in Register patient modal
        locators.searchSsnButton.click();
        //Verify there's an error that the patient is admitted
        locators.patientAdmittedError.isDisplayed();
    }

    @Test(priority = 4)
    public void PatientRegisterNegative() throws InterruptedException {
        locators.patientListNav.click();

        //Click on Register patient button in the navigation menu
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        Thread.sleep(1000);
        //Click on Search button in Register patient modal without entering input
        locators.searchSsnButton.click();
        //Verify there's an error displayed for no input
        locators.requiredFieldErrorSSN.isDisplayed();

        //Create and enter a valid and unique SSN
        String validSSN = myMethods.ssnValidScript();
        locators.ssnField.sendKeys(validSSN);
        //Click Search button
        wait.until(ExpectedConditions.elementToBeClickable(locators.searchSsnButton));
        locators.searchSsnButton.click();
        locators.searchSsnButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        //Click Save button
        locators.saveButton.click();
        //Verify there's error for first, last name and phone number field
        locators.requiredFieldErrorFN.isDisplayed();
        locators.requiredFieldErrorLN.isDisplayed();
        locators.requiredFieldErrorPhone.isDisplayed();

        //Enter valid first, last name and phone number and click Save
        String firstName = myMethods.firstNameValidScript();
        locators.firstNameField.sendKeys(firstName);
        String lastName = myMethods.lastNameValidScript();
        locators.lastNameField.sendKeys(lastName);
        String phone = myMethods.phoneValidScript();
        locators.phoneField.sendKeys(phone);
        locators.saveButton.click();
        locators.saveButton.click();

        //Click Save
        Thread.sleep(3000);
        locators.saveButton.click();

        //Verify there's an error displayed for required fields w/o disease code
        wait.until(ExpectedConditions.visibilityOf(locators.requiredFieldErrorContact));
        locators.requiredFieldErrorContact.isDisplayed();
        locators.requiredFieldErrorReason.isDisplayed();
        locators.requiredFieldErrorAllergies.isDisplayed();
        locators.requiredFieldErrorDepartment.isDisplayed();
        locators.requiredFieldErrorDate.isDisplayed();
        //Enter valid input in fields
        locators.contactField.sendKeys(BasicParams.contactPerson);
        locators.reasonField.sendKeys(BasicParams.reasonValid);
        locators.allergiesField.sendKeys(BasicParams.allergies);
        locators.departmentRegister.click();
        locators.cardiologyDepDropdown.click();
        locators.calendarIconArea.click();
        locators.lastDate.click();
        //Click Save button to register the patient
        locators.saveButton.click();
        //Verify there's an error for disease code field
        locators.requiredFieldDiseaseCode.isDisplayed();
    }

    @Test(priority = 5)
    public void RegisterExistingPatient() throws InterruptedException {
        locators.patientListNav.click();
        //Click on Register patient button in the navigation menu
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        //Enter valid SSN of a patient that is already admitted
        locators.ssnField.sendKeys(BasicParams.oldPatient);
        //Click on Search button in Register patient modal
        locators.searchSsnButton.click();
        //Verify correct screen is displayed
        locators.contactField.isDisplayed();
        locators.reasonField.isDisplayed();
        locators.allergiesField.isDisplayed();
        locators.departmentRegister.isDisplayed();
    }

    @Test(priority = 6)
    public void PatientRegisterNegativeDates() throws InterruptedException {
        locators.patientListNav.click();
        //Click Register patient button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        //Create and enter a valid and unique SSN
        String validSSN = myMethods.ssnValidScript();
        locators.ssnField.sendKeys(validSSN);
        //Click Search button
        locators.searchSsnButton.click();
        //Create and enter first name
        Thread.sleep(500);
        String firstName = myMethods.firstNameValidScript();
        locators.firstNameField.sendKeys(firstName);
        //Create and enter last name
        String lastName = myMethods.lastNameValidScript();
        locators.lastNameField.sendKeys(lastName);
        //Create and enter phone
        String phone = myMethods.phoneValidScript();
        locators.phoneField.sendKeys(phone);
        //Click Save button to register the patient
        locators.saveButton.click();

        //Click Calendar icon
        locators.calendarIcon.click();
        //Click the button for the previous month
        locators.previousMonthButton.click();
        //Click on first date
        locators.firstDate.click();
        //Verify there's an error for past dates
        locators.requiredFieldErrorDate.isDisplayed();
        locators.cancelButton.click();

        //Click Register patient button
        locators.registerPatientButton.click();
        //Create and enter a valid and unique SSN
        Thread.sleep(500);
        locators.ssnField.sendKeys(validSSN);
        //Click Search button
        locators.searchSsnButton.click();

        //Enter invalid date
        Thread.sleep(500);
        locators.dateField.clear();
        locators.dateField.clear();
        locators.dateField.sendKeys(BasicParams.invalidDate1);
        locators.allergiesField.click();
        //Verify there's an error for invalid date
        wait.until(ExpectedConditions.visibilityOf(locators.invalidDateError));
        locators.invalidDateError.isDisplayed();
        locators.cancelButton.click();

        //Click Register patient button
        locators.registerPatientButton.click();
        //Create and enter a valid and unique SSN
        Thread.sleep(500);
        locators.ssnField.sendKeys(validSSN);
        //Click Search button
        locators.searchSsnButton.click();

        //Enter invalid date
        Thread.sleep(500);
        locators.dateField.clear();
        locators.dateField.clear();
        locators.dateField.sendKeys(BasicParams.invalidDate2);
        locators.allergiesField.click();
        //Verify there's an error for invalid date
        locators.invalidDateError.isDisplayed();
        locators.cancelButton.click();

        //Click Register patient button
        locators.registerPatientButton.click();
        //Create and enter a valid and unique SSN
        Thread.sleep(500);
        locators.ssnField.sendKeys(validSSN);
        //Click Search button
        locators.searchSsnButton.click();

        //Enter invalid date
        Thread.sleep(500);
        locators.dateField.clear();
        locators.dateField.clear();
        locators.dateField.sendKeys(BasicParams.invalidDate3);
        locators.allergiesField.click();
        //Verify there's an error for invalid date
        locators.invalidDateError.isDisplayed();
    }

    @Test(priority = 7)
    public void PatientRegisterCloseCancel() throws InterruptedException {
        locators.patientListNav.click();

        //SSN screen
        //Click Register patient button and close button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        locators.closeButton.click();

        //Click Register patient button and cancel button

        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        locators.cancelButton.click();

        //Edit patient screen
        //Click Register patient button, enter valid SSN and click Search
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        String validSSN = myMethods.ssnValidScript();
        locators.ssnField.sendKeys(validSSN);
        locators.searchSsnButton.click();

        //Click close button
        wait.until(ExpectedConditions.elementToBeClickable(locators.closeButton));
        locators.closeButton.click();

        //Click Register patient button, enter valid SSN and click Search
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        locators.ssnField.sendKeys(validSSN);
        locators.searchSsnButton.click();

        //Click cancel button
        Thread.sleep(1000);
        locators.cancelButton.click();

        //Admit patient screen
        //Click Register patient button, enter valid SSN and click Search
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        validSSN = myMethods.ssnValidScript();
        locators.ssnField.sendKeys(validSSN);
        locators.searchSsnButton.click();
        Thread.sleep(500);
        String firstName = myMethods.firstNameValidScript();
        locators.firstNameField.sendKeys(firstName);
        String lastName = myMethods.lastNameValidScript();
        locators.lastNameField.sendKeys(lastName);
        String phone = myMethods.phoneValidScript();
        locators.phoneField.sendKeys(phone);
        locators.saveButton.click();

        //Click close button
        Thread.sleep(500);
        locators.closeButton.click();

        //Click Register patient button, enter valid SSN and click Search
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        validSSN = myMethods.ssnValidScript();
        locators.ssnField.sendKeys(validSSN);
        locators.searchSsnButton.click();
        Thread.sleep(500);
        locators.firstNameField.sendKeys(firstName);
        locators.lastNameField.sendKeys(lastName);
        locators.phoneField.sendKeys(phone);
        locators.saveButton.click();

        //Click cancel button
        Thread.sleep(500);
        locators.cancelButton.click();

        //Add bed screen
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        validSSN = myMethods.ssnValidScript();
        locators.ssnField.sendKeys(validSSN);
        locators.searchSsnButton.click();
        Thread.sleep(500);
        locators.firstNameField.sendKeys(firstName);
        locators.lastNameField.sendKeys(lastName);
        locators.phoneField.sendKeys(phone);
        locators.saveButton.click();
        Thread.sleep(500);
        locators.contactField.sendKeys(BasicParams.contactPerson);
        locators.reasonField.sendKeys(BasicParams.reasonValid);
        locators.allergiesField.sendKeys(BasicParams.allergies);
        locators.departmentRegister.click();
        locators.cardiologyDepDropdown.click();
        locators.calendarIconArea.click();
        locators.lastDate.click();
        locators.diseaseCodeDropdown.click();
        Thread.sleep(500);
        locators.diseaseCodeSearchField.sendKeys(BasicParams.diseaseCodeValid);
        locators.diseaseCodeSearchResult.click();
        locators.saveButton.click();

        //Click close button
        Thread.sleep(500);
        locators.closeButton.click();

        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(locators.registerPatientButton));
        locators.registerPatientButton.click();
        validSSN = myMethods.ssnValidScript();
        locators.ssnField.sendKeys(validSSN);
        locators.searchSsnButton.click();
        Thread.sleep(500);
        locators.firstNameField.sendKeys(firstName);
        locators.lastNameField.sendKeys(lastName);
        locators.phoneField.sendKeys(phone);
        locators.saveButton.click();
        Thread.sleep(500);
        locators.contactField.sendKeys(BasicParams.contactPerson);
        locators.reasonField.sendKeys(BasicParams.reasonValid);
        locators.allergiesField.sendKeys(BasicParams.allergies);
        locators.departmentRegister.click();
        locators.cardiologyDepDropdown.click();
        locators.calendarIconArea.click();
        locators.lastDate.click();
        locators.diseaseCodeDropdown.click();
        Thread.sleep(500);
        locators.diseaseCodeSearchField.sendKeys(BasicParams.diseaseCodeValid);
        locators.diseaseCodeSearchResult.click();
        locators.saveButton.click();

        //Click cancel button
        Thread.sleep(500);
        locators.cancelButton.click();

        wait.until(ExpectedConditions.invisibilityOf(locators.cancelButton));
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.cancelButton)).apply(driver);
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
}
