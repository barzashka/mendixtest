package TestData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class StandardMethods {
    WebDriver driver;
    BasicParams parameters;
    Locators locators;

    public StandardMethods(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        parameters = new BasicParams(driver);
        locators = new Locators(driver);
    }


    public void superLogin() {

        //Enter valid username
        locators.usernameField.sendKeys(BasicParams.superAdminUsername);
        //Enter valid password
        locators.passwordField.sendKeys(BasicParams.superAdminPassword);
        //Click Sign in button
        locators.loginButton.click();
    }

    public void adminLogin() {

        //Enter valid username
        locators.usernameField.sendKeys(BasicParams.receptionistUsername);
        //Enter valid password
        locators.passwordField.sendKeys(BasicParams.receptionistPassword);
        //Click Sign in button
        locators.loginButton.click();
    }

    public void practitionerLogin() {

        //Enter valid username
        locators.usernameField.sendKeys(BasicParams.docUsername);
        //Enter valid password
        locators.passwordField.sendKeys(BasicParams.docPassword);
        //Click Sign in button
        locators.loginButton.click();
    }

    public void nurseLogin() {

        //Enter valid username
        locators.usernameField.sendKeys(BasicParams.nurseUsername);
        //Enter valid password
        locators.passwordField.sendKeys(BasicParams.nursePassword);
        //Click Sign in button
        locators.loginButton.click();
    }

    public String emailValidScriptAdmin() {
        //Create valid email
        return "barzashka+admin" + Math.floor(Math.random() * 9999) + "@gmail.com";
    }

    public String emailValidScriptNurse() {
        //Create valid email
        return "barzashka+nurse" + Math.floor(Math.random() * 9999) + "@gmail.com";
    }

    public String emailValidScriptPractitioner() {
        //Create valid email
        return "barzashka+doc" + Math.floor(Math.random() * 9999) + "@gmail.com";
    }

    public String emailValidScriptSuperAdmin() {
        //Create valid email
        return "barzashka+super" + Math.floor(Math.random() * 9999) + "@gmail.com";
    }

    public String firstNameValidScript() {
        //Create valid first name
        return "Viki" + Math.floor(Math.random() * 9999);
    }

    public String lastNameValidScript() {
        //Create valid last name
        return "Doe" + Math.floor(Math.random() * 9999);
    }

    public String phoneValidScript() {
        //Create valid phone number
        return String.valueOf(Math.floor(Math.random() * 100000000));
    }

    public String roomNumberValidScript() {
        //Create valid phone number
        return String.valueOf(Math.floor(Math.random() * 9999));
    }

    public String ssnValidScript() {
        //Create valid SSN number
        return "MySSN" + Math.floor(Math.random() * 9999) + "number";
    }
}


