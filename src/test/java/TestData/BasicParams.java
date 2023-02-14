//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package TestData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasicParams {
    WebDriver driver;
    public static String localApp = "http://localhost:8080/index.html";
    public static String testingApp = "https://hospitalmanagementapp105-sandbox.mxapps.io/";
    public static String superAdminUsername = "demo_administrator";
    public static String superAdminPassword = "o6fX6TqOhi";
    public static String receptionistUsername = "demo_receptionist";
    public static String receptionistPassword = "6hx2Stq7zI";
    public static String docUsername = "demo_doc";
    public static String docPassword = "uNRGw3Vj1J";
    public static String nurseUsername = "demo_nurse";
    public static String nursePassword = "2fQwcMyVoS";
    public static String incorrectPassword = "abc123!?";
    public static String allergies = "none";
    public static String contactPerson = "Armenskia Pop";
    public static String dateValid = "12/31/2023";
    public static String departmentAnesthesiology = "Anesthesiology";
    public static String departmentCardiology = "Cardiology";
    public static String departmentPsychiatry = "Psychiatry";
    public static String departmentSurgery = "Surgery";
    public static String dischargeReason = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum congue, ligula a scelerisque ultrices, dolor enim mollis enim, ut pellentesque ex felis eget eros. Curabitur sed lacus vel justo euismod finibus dictum ut nulla. Vivamus suscipit libero nulla, ut volutpat metus volutpat eget. Suspendisse ullamcorper ullamcorper lacus, eu accumsan ex aliquam non. Nunc venenatis tempor eros quis faucibus. ";
    public static String diseaseCodeValid = "Hypochondria";
    public static String emailValid = "";
    public static String existingEmail = "barzashka+nurse2@gmail.com";
    public static String existingRoomNumber = "666";
    public static String firstName = "";
    public static String invalidDate1 = "33/33/3333";
    public static String invalidDate2 = "2.22.2222";
    public static String invalidDate3 = "2 22 2222";
    public static String invalidEmail1 = "John..Doe@example.com";
    public static String invalidEmail2 = ".John.Doe@example.com";
    public static String invalidEmail3 = "Abc.example.com";
    public static String invalidEmail4 = "A@b@c@example.com";
    public static String invalidEmail5 = "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com";
    public static String invalidEmail6 = "just\"not\"right@example.com";
    public static String invalidEmail7 = "this is\"not\\allowed@example.com";
    public static String invalidEmail8 = "underscore@is_not_allowed_here.com";
    public static String lastName = "";
    public static String letters = "abCD";
    public static String lettersSymbols = "aB*+";
    public static String longEmail = "1234567890123456789012345678901234567890123456789012345678901234+x@example.com";
    public static String longRoomNumber = "11111";
    public static String oldPatient = "3349";
    public static String phoneValid = "";
    public static String reasonValid = "Hypochondria is the only disease I haven't got";
    public static String registeredPatient = "MySSN7278.0number";
    public static String roomNumber = "";
    public static String roomToBeDeleted = "321";
    public static String symbols = "!@#$";
    public static String validSSN = "";

    public BasicParams(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
