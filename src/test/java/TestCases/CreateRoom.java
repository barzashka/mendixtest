package TestCases;

import TestData.BasicParams;
import TestData.Locators;
import TestData.StandardMethods;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
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
import static org.testng.Assert.*;

public class CreateRoom {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseUrl);
        //Login as Super Admin
        myMethods.superLogin();
    }


    @Test(priority = 1)
    public void CreateRoomPositive() throws InterruptedException {
        //Click create room button
        Thread.sleep(500);
        locators.createRoomButton.click();
        //Create a new room number and enter in room number field
        String roomNumber = myMethods.roomNumberValidScript();
        locators.roomNumberField.sendKeys(roomNumber);
        //Select a floor
        locators.floorDropdown.click();
        locators.floor13.click();
        //Select a department
        locators.departmentDropdown.click();
        locators.cardiologyDepDropdown.click();
        //Select beds from dropdown
        locators.bedsDropdown.click();
        locators.beds2value.click();
        //Click Save button
        locators.saveButton.click();
        //Verify confirmation message
        locators.roomConfirmation.isDisplayed();
        //Click Ok button
        locators.okButton.click();

    }

    @Test(priority = 2)
    public void EditRoom() throws InterruptedException {
        //Click to open Rooms tab on dashboard
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locators.roomsTab);
        Thread.sleep(500);
        locators.roomsTab.click();
        //Click to select first room
        locators.firstRoom.click();
        //Click on Edit room button
        locators.editRoom.click();
        //Select 1st floor
        locators.selectFloorDropdown.click();
        locators.floor1value.click();
        //Select Surgery department
        locators.selectDepartmentDropdown.click();
        locators.surgeryDepartment.click();
        //Select 1 bed
        locators.selectBedsDropdown.click();
        locators.bed1value.click();
        //Click Save button
        locators.saveButton.click();
        //Verify confirmation message is displayed
        locators.editConfirmation.isDisplayed();
        locators.okButton.click();
    }

    @Test(priority = 3)
    public void DeleteRoom() throws InterruptedException {
        //Click Create room button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(locators.createRoomButton));
        locators.createRoomButton.click();
        //Create room 321
        locators.roomNumberField.sendKeys(BasicParams.roomToBeDeleted);
        //Select a floor
        locators.floorDropdown.click();
        locators.floor13.click();
        //Select a department
        locators.departmentDropdown.click();
        locators.cardiologyDepDropdown.click();
        //Select beds from dropdown
        locators.bedsDropdown.click();
        locators.beds2value.click();
        //Click Save button
        locators.saveButton.click();
        //Click Ok button
        locators.okButton.click();


        //Click Rooms tab and sort them
        locators.roomsTab.click();
        locators.sortRoomsByNumber.click();
        //Click to select the room
        locators.room321.click();
        //Click delete room button
        locators.deleteRoomButton.click();
        //Verify confirmation dialog is displayed
        locators.areYouSure.isDisplayed();
        //Click to Cancel deletion
        locators.cancelButton.click();
        driver.navigate().refresh();

        //Click Rooms tab and sort them
        locators.roomsTab.click();
        wait.until(ExpectedConditions.visibilityOf(locators.sortRoomsByNumber));
        locators.sortRoomsByNumber.click();
        //Click to select the room
        locators.room321.click();
        //Click delete room button
        locators.deleteRoomButton.click();
        //Click to close the dialog cancelling deletion flow
        locators.closeButton.click();
        driver.navigate().refresh();

        //Click Rooms tab and sort them
        locators.roomsTab.click();
        Thread.sleep(2000);
        locators.sortRoomsByNumber.click();
        //Click to select the room
        locators.room321.click();
        //Click delete room button
        locators.deleteRoomButton.click();
        //Click to confirm deletion of the room
        locators.okButton.click();
        //locators.room321.isDisplayed();
        wait.until(ExpectedConditions.invisibilityOf(locators.room321));
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.room321)).apply(driver);
        assertNotEquals(notPresent, Boolean.TRUE);
        //Sign out
        //locators.signOutNav.click();
    }

    @Test(priority = 4)
    public void SearchRooms() throws InterruptedException {
        //Click to open Rooms tab on dashboard
        Thread.sleep(2000);
        locators.roomsTab.click();
        //Verify room 123 is displayed
        locators.room123list.isDisplayed();
        //Verify room 777 is displayed
        locators.room777list.isDisplayed();
        //Click Search rooms button
        locators.searchRoomsButton.click();
        //Enter valid room number
        locators.roomSearchField.sendKeys("123");
        //Select a valid floor with available room /s
        locators.floorSearchDropdown.click();
        locators.floor2InSearch.click();
        //Click to Search == confirm search criteria and execute search
        locators.searchConfirmationButton.click();
        //Verify room 123 is displayed
        locators.room123list.isDisplayed();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(locators.room777list));
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.room777list)).apply(driver);
        assertNotEquals(notPresent, Boolean.TRUE);
        //Click to reset the search
        locators.searchResetButton.click();
        //Enter input that will return no results
        locators.roomSearchField.sendKeys("abc");
        //Click to Search == confirm search criteria and execute search
        locators.searchConfirmationButton.click();
        //Verify there are no results displayed
        wait.until(ExpectedConditions.invisibilityOf(locators.firstCellRooms));
        notPresent = ExpectedConditions.not(ExpectedConditions.invisibilityOf(locators.firstCellRooms)).apply(driver);
        assertNotEquals(notPresent, Boolean.TRUE);
        //Click to reset the search
        locators.searchResetButton.click();
        //Select a valid floor with available room /s
        locators.floorSearchDropdown.click();
        locators.floor2InSearch.click();
        //Click to Search == confirm search criteria and execute search
        locators.searchConfirmationButton.click();
        //Verify there are results displayed
        locators.firstCellRooms.isDisplayed();
    }

    @Test(priority = 5)
    public void CreateRoomNoInput() throws InterruptedException {
        //Click create room button
        Thread.sleep(2000);
        locators.createRoomButton.click();
        //Clear room number field
        locators.roomNumberField.clear();
        //Click Save button
        locators.saveButton.click();
        //Verify there's an error for room number
        locators.roomNumberError.isDisplayed();
        //Verify there's an error for floor
        locators.floorNumberError.isDisplayed();
        //Verify there's an error for department
        locators.departmentNumberError.isDisplayed();
        //Verify there's an error for number of beds
        locators.bedsNumberError.isDisplayed();

    }

    @Test(priority = 6)
    public void CreateRoomInvalidInput() throws InterruptedException {
        //Click create room button
        Thread.sleep(2000);
        locators.createRoomButton.click();
        //Enter invalid room number
        Thread.sleep(2000);
        locators.roomNumberField.sendKeys(BasicParams.letters);
        //Select a floor
        locators.floorDropdown.click();
        locators.floor1value.click();
        //Select a department
        locators.departmentDropdown.click();
        locators.cardiologyDepDropdown.click();
        //Select beds from dropdown
        locators.bedsDropdown.click();
        locators.beds2value.click();
        //Click Save button
        locators.saveButton.click();
        //Verify there's an error for number of room input
        locators.roomNumberInvalidError.isDisplayed();
        //Click Cancel button
        locators.cancelButton.click();

        //Click create room button
        locators.createRoomButton.click();
        //Enter invalid room number
        Thread.sleep(2000);
        locators.roomNumberField.sendKeys(BasicParams.symbols);
        //Select a floor
        locators.floorDropdown.click();
        locators.floor13.click();
        //Select a department
        locators.departmentDropdown.click();
        locators.cardiologyDepDropdown.click();
        //Select beds from dropdown
        locators.bedsDropdown.click();
        locators.beds2value.click();
        //Click Save button
        locators.saveButton.click();
        //Verify there's an error for number of room input
        locators.roomNumberInvalidError.isDisplayed();
        //Click close button
        locators.closeButton.click();

        //Click create room button
        locators.createRoomButton.click();
        //Enter invalid room number
        Thread.sleep(2000);
        locators.roomNumberField.sendKeys(BasicParams.lettersSymbols);
        //Select a floor
        locators.floorDropdown.click();
        locators.floor1value.click();
        //Select a department
        locators.departmentDropdown.click();
        locators.cardiologyDepDropdown.click();
        //Select beds from dropdown
        locators.bedsDropdown.click();
        locators.beds2value.click();
        //Click Save button
        locators.saveButton.click();
        //Verify there's an error for number of room input
        locators.roomNumberInvalidError.isDisplayed();
        //Click Cancel button
        locators.cancelButton.click();

        //Click create room button
        locators.createRoomButton.click();
        //Enter long room number
        Thread.sleep(2000);
        locators.roomNumberField.sendKeys(BasicParams.longRoomNumber);
        //Click Save button
        locators.saveButton.click();
        //Verify the field doesn't accept more than 4 digits
        String roomNumber = locators.roomNumberField.getAttribute("value");
        assertEquals(roomNumber, "1111");
        //Click Cancel button
        locators.cancelButton.click();

        //Click create room button
        locators.createRoomButton.click();
        //Enter invalid room number
        Thread.sleep(2000);
        locators.roomNumberField.sendKeys(BasicParams.existingRoomNumber);
        //Select a floor
        locators.floorDropdown.click();
        locators.floor1value.click();
        //Select a department
        locators.departmentDropdown.click();
        locators.cardiologyDepDropdown.click();
        //Select beds from dropdown
        locators.bedsDropdown.click();
        locators.beds2value.click();
        //Click Save button
        locators.saveButton.click();
        //Verify there's an error for number of room input
        locators.roomNumberExistingError.isDisplayed();
        //Click Cancel button
        locators.cancelButton.click();


    }

    @AfterTest
    public void afterTest() {
        //Sign out
        locators.signOutNav.click();
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
