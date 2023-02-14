package TestData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Locators {
    WebDriver driver;

    //Element locators
    //Login screen
    @FindBy(xpath = "//div[1]/div/input")
    public WebElement usernameField;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordField;

    @FindBy(id = "mxui_widget_LoginButton_0")
    public WebElement loginButton;

    @FindBy(id = "mxui_widget_ValidationMessage_0")
    public WebElement loginError;

    @FindBy(xpath = "//img")
    //@FindBy(xpath="//img[role='button']")
    public WebElement unmaskButton;

    @FindBy(xpath = "//img[@src='https://hospitalmanagementapp105-sandbox.mxapps.io/img/User$Icons$visible.png']")
    public WebElement maskButton;

    @FindBy(xpath = "//input[@type='password' and @class='form-control mx-name-passwordTextBox1']")
    public WebElement maskedPassword;

    // * @FindBy(
    // * xpath="//input[@type='text' and @class='form-control mx-name-passwordTextBox1']"
    // * ) public WebElement unmaskedPassword;


    //Navigation in the header

    @FindBy(xpath="//span[@class='glyphicon glyphicon-log-out']")
    public WebElement signOutNav;

    @FindBy(xpath = "//button[. = ' Create Room']")
    public WebElement createRoomButton;

    @FindBy(xpath="//button[normalize-space()='Register Patient']")
    public WebElement registerPatientButton;

    @FindBy(xpath = "//a[. = ' Hospital Overview']")
    public WebElement hospitalOverviewNav;

    @FindBy(xpath = "//a[normalize-space()='Patients List']")
    public WebElement patientListNav;

    @FindBy(xpath = "//a[normalize-space()='Admin Panel']")
    public WebElement adminPanelNav;

    @FindBy(xpath = "//a[normalize-space()='Users']")
    public WebElement usersNav;

    @FindBy(xpath = "//a[normalize-space()='Departments']")
    public WebElement departmentsNav;

    @FindBy(xpath = "//a[normalize-space()='Disease Codes']")
    public WebElement diseaseCodesNav;

    @FindBy(xpath = "//a[normalize-space()='Settings']")
    public WebElement settingsNav;


    //Sections on home screen

    @FindBy(xpath = "//h4[. = 'Departments']")
    public WebElement departmentsSectionTitle;

    @FindBy(id = "mxui_widget_TabContainer_0_tab-0")
    public WebElement departmentsTab;

    @FindBy(id = "mxui_widget_TabContainer_0_tab-1")
    public WebElement roomsTab;

    @FindBy(id = "mxui_widget_TabContainer_0_tab-2")
    public WebElement bedsTab;

    @FindBy(id = "mxui_widget_TabContainer_0_tab-3")
    public WebElement patientsTab;

    @FindBy(id = "mxui_widget_TabContainer_0_tab-4")
    public WebElement usersTab;

    @FindBy(xpath = "//h2[. = 'Patient List']")
    public WebElement patientListSectionTitle;

    @FindBy(xpath = "//div[@class='mx-demouserswitcher-toggle']")
    public WebElement userIconRight;

    @FindBy(xpath = "//h3[. = 'demo_receptionist']")
    public WebElement demoReceptionist;

    @FindBy(xpath = "//h3[. = 'demo_doc']")
    public WebElement demoDoc;

    @FindBy(xpath = "//h3[. = 'demo_nurse']")
    public WebElement demoNurse;

    //Create room

    @FindBy(xpath = "//input[@placeholder = 'Enter number of the Room']")
    public WebElement roomNumberField;

    @FindBy(xpath = "//div[2]/div/div/div/div/div/div/div/div/div[1]/div[2]/div/select")
    public WebElement floorDropdown;

    @FindBy(xpath = "//option[@value='_13']")
    public WebElement floor13;

    @FindBy(xpath = "//div[3]/div/div/select")
    public WebElement departmentDropdown;

    @FindBy(xpath = "//option[@value='13510798882111685']")
    public WebElement cardiologyDepDropdown;

    @FindBy(xpath = "//div[4]/div/select")
    public WebElement bedsDropdown;

    @FindBy(xpath = "//div[4]/div/select//option[@value='_2']")
    public WebElement beds2value;

    @FindBy(xpath = "//button[. = ' Save']")
    public WebElement saveButton;

    @FindBy(xpath = "//p[. = '2 new avaliable beds are created!']")
    public WebElement roomConfirmation;

    @FindBy(xpath = "//button[. = 'OK']")
    public WebElement okButton;

    //+Edit a room, search
    @FindBy(xpath = "//div[2]/div/div/div[3]/table/tbody/tr[1]/td[1]")
    public WebElement firstRoom;

    @FindBy(id = "mxui_widget_ControlBarButton_6")
    public WebElement editRoom;

    @FindBy(xpath = "//div[2]/div/div/div/div/div/div/div/div/div[1]/div[2]/div/select")
    public WebElement selectFloorDropdown;

    @FindBy(xpath = "//option[@value='_1']")
    public WebElement floor1value;

    @FindBy(xpath = "//div[3]/div/div/select")
    public WebElement selectDepartmentDropdown;

    @FindBy(xpath = "//option[@value='13510798882112121']")
    public WebElement surgeryDepartment;

    @FindBy(xpath = "//div[4]/div/select")
    public WebElement selectBedsDropdown;

    @FindBy(xpath = "//div[4]/div/select//option[@value='_1']")
    public WebElement bed1value;

    @FindBy(xpath = "//p[. = '1 new avaliable beds are created!']")
    public WebElement editConfirmation;

    @FindBy(xpath = "//td/div[. = '321']")
    public WebElement room321;

    @FindBy(id = "mxui_widget_ControlBarButton_7")
    public WebElement deleteRoomButton;

    @FindBy(xpath = "//p[. = 'Are you sure you want to delete this?']")
    public WebElement areYouSure;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    public WebElement cancelButton;

    //@FindBy(xpath = "//button[@title=\"Cancel\"]")
    @FindBy(xpath = "//button[normalize-space()='×']")
    public WebElement closeButton;

    @FindBy(xpath = "//div[contains(text(),'Number')]")
    public WebElement sortRoomsByNumber;

    @FindBy(xpath = "//div[contains(text(),'123')]")
    public WebElement room123list;

    @FindBy(xpath = "//div[contains(text(),'777')]")
    public WebElement room777list;

    @FindBy(id = "mxui_widget_ControlBarButton_4")
    public WebElement searchRoomsButton;

    @FindBy(xpath = "//div[2]/input")
    public WebElement roomSearchField;

    @FindBy(xpath = "//div[2]/input")
    public WebElement floorSearchDropdown;

    @FindBy(xpath = "//option[@value='mxui_widget_SearchInput_2__2']")
    public WebElement floor2InSearch;

    @FindBy(id = "mxui_widget_Button_6")
    public WebElement searchConfirmationButton;

    @FindBy(id = "mxui_widget_Button_7")
    public WebElement searchResetButton;

    @FindBy(xpath = "//div[2]/div/div/div[3]/table/tbody/tr[1]/td[1]")
    public WebElement firstCellRooms;

    @FindBy(xpath = "//div[. = 'Must enter number of the Room']")
    public WebElement roomNumberError;

    @FindBy(xpath = "//div[. = 'Must select floor']")
    public WebElement floorNumberError;

    @FindBy(xpath = "//div[. = 'Must select department ...']")
    public WebElement departmentNumberError;

    @FindBy(xpath = "//div[. = 'Must select the number of the beds']")
    public WebElement bedsNumberError;

    @FindBy(xpath = "//div[. = 'Number should be max 4 digit']")
    public WebElement roomNumberInvalidError;

    @FindBy(xpath = "//div[. = 'Number already exists!']")
    public WebElement roomNumberExistingError;

    //Register patient
    @FindBy(xpath = "//div[4]/div[1]/div[2]/div/div/div/div/div/div/div/div/div[1]/div/input")
    public WebElement ssnField;

    @FindBy(xpath = "//button[. = ' Search']")
    //@FindBy(xpath = "//button[@class='btn mx-button mx-name-actionButton1 btn-primary']")
    public WebElement searchSsnButton;

    @FindBy(xpath = "//div[4]/div[1]/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[2]/div/input")
    public WebElement firstNameField;

    @FindBy(xpath = "//div[4]/div[1]/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[3]/div/input")
    public WebElement lastNameField;

    @FindBy(xpath = "//div[4]/div[1]/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[4]/div/input")
    public WebElement phoneField;

    @FindBy(xpath = "//div[4]/div[1]/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/div/input")
    public WebElement contactField;

    @FindBy(xpath = "/html/body/div[4]/div[1]/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/input")
    //@FindBy(xpath = "//div[4]/div/input")
    public WebElement reasonField;

    @FindBy(xpath = "/html/body/div[4]/div[1]/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[3]/div/input")
    //@FindBy(xpath = "//div[4]//input")
    public WebElement allergiesField;

    @FindBy(xpath = "/html/body/div[4]/div[1]/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[4]/div/div/select")
    //@FindBy(xpath = "//div[4]/div/div/select")
    public WebElement departmentRegister;

    @FindBy(xpath = "//button[@aria-label='Show date picker']")
    public WebElement calendarIconArea;

    @FindBy(xpath = "//tr[6]/td[7]")
    public WebElement lastDate;

    @FindBy(className = "select2-selection__placeholder")
    public WebElement diseaseCodeDropdown;

    @FindBy(xpath = "//span/input")
    public WebElement diseaseCodeSearchField;

    @FindBy(xpath = "//span//div")
    public WebElement diseaseCodeSearchResult;

    @FindBy(xpath = "//h4[. = 'Add Bed']")
    public WebElement addBedHeader;

    //@FindBy(xpath = "//span[@class='mx-text mx-name-text1'][normalize-space()='1']")
    @FindBy(xpath = "//div[@class='mx-name-container1 green-bed']")
    public WebElement bed1;

    @FindBy(xpath = "//p[. = 'Тhe patient is admitted']")
    public WebElement patientAdmittedMessage;

    @FindBy(xpath = "//p[. = 'This patient is already admitted']")
    public WebElement patientAdmittedError;

    @FindBy(xpath = "//div[. = 'This field is required']")
    public WebElement requiredFieldErrorSSN;

    @FindBy(xpath = "//div[2]/div/div[. = 'This field is mandatory']")
    public WebElement requiredFieldErrorFN;

    @FindBy(xpath = "//div[3]/div/div[. = 'This field is mandatory']")
    public WebElement requiredFieldErrorLN;

    @FindBy(xpath = "//div[4]/div/div[. = 'This field is mandatory']")
    public WebElement requiredFieldErrorPhone;

    @FindBy(xpath = "//div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/div/div[. = 'This field is mandatory']")
    public WebElement requiredFieldErrorContact;

    @FindBy(xpath = "//div[2]/div/div[. = 'This field is mandatory']")
    public WebElement requiredFieldErrorReason;

    @FindBy(xpath = "//div[3]/div/div[. = 'This field is mandatory']")
    public WebElement requiredFieldErrorAllergies;

    @FindBy(xpath = "//div[4]/div/div/div[. = 'This field is mandatory']")
    public WebElement requiredFieldErrorDepartment;

    @FindBy(xpath = "//div[2][. = concat('You can', \"'\", 't select past dates')]")
    public WebElement requiredFieldErrorDate;

    @FindBy(xpath = "//div[2][. = 'Date should not be in the past!']")
    public WebElement pastDateError;

    @FindBy(xpath = "//div[. = 'This field is mandatory']")
    public WebElement requiredFieldDiseaseCode;

    @FindBy(xpath = "//div[6]//button[1]")
    public WebElement previousMonthButton;

    @FindBy(xpath = "//span[@class='glyphicon glyphicon-calendar']")
    public WebElement calendarIcon;

    @FindBy(xpath = "//div[6]/table/tbody/tr[1]/td[1]")
    public WebElement firstDate;

    @FindBy(xpath = "//div[5]//input")
    public WebElement dateField;

    @FindBy(xpath = "//div[2][. = 'Invalid date']")
    public WebElement invalidDateError;

    //Invite patient
    @FindBy(xpath = "//button[. = ' Invite User']")
    public WebElement inviteButton;

    @FindBy(xpath = "//div[@class='col-sm-9']//input[@type='text']")
    public WebElement emailField;

    @FindBy(xpath = "//div[2]/div/select")
    public WebElement userTypeDropdown;

    @FindBy(xpath = "//option[. = 'Superadmin']")
    public WebElement superadminOption;

    @FindBy(xpath = "//option[. = 'Admin Staff']")
    public WebElement adminOption;

    @FindBy(xpath = "//option[. = 'Practitioner']")
    public WebElement docOption;

    @FindBy(xpath = "//option[. = 'Nurse']")
    public WebElement nurseOption;

    @FindBy(xpath = "//div[@class='mx-name-dropDown1 mx-dropdown form-group']//select[@class='form-control']")
    public WebElement departmentDropdownInvite;

    @FindBy(xpath = "//option[@value='D2']")
    public WebElement cardiologyOptionInvite;

    @FindBy(xpath = "//option[@value='D3']")
    public WebElement psychiatryOptionInvite;

    @FindBy(xpath = "//button[. = ' Confirm']")
    public WebElement confirmButton;

    @FindBy(xpath = "//p[contains(text(),'Invitation to')]")
    public WebElement confirmationMessage;

    @FindBy(xpath = "//div[2]/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div[. = 'Field is mandatory']")
    public WebElement emailRequiredError;

    @FindBy(xpath = "//div[2]/div/div[. = 'Field is mandatory']")
    public WebElement userTypeRequiredError;

    @FindBy(xpath = "//div[2]/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div[. = 'Invalid e-mail']")
    public WebElement invalidEmailError;

    @FindBy(xpath = "//div[2]/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div[. = 'Email already registered']")
    public WebElement existingEmailError;


    //Discharge a patient
    @FindBy(xpath = "//div[@class='mx-name-container6 beds yellow-bed']")
    public WebElement occupiedRoom;

    @FindBy(xpath = "//div[@class='mx-name-container5 red-bed']")
    public WebElement occupiedBed;

    @FindBy(xpath = "//button[. = ' Discharge']")
    public WebElement dischargeButton;

    @FindBy(xpath = "//div/textarea")
    public WebElement dischargeComment;

    @FindBy(xpath = "//div[. = 'Comment is mandatory']")
    public WebElement commentError;

    //Prolong Patient Stay
    @FindBy(xpath = "(//div[@role='button'])[5]")
    public WebElement firstPatient;

    @FindBy(xpath = "//div[@role='rowgroup']/div[3]/div[1]")
    public WebElement secondPatient;

    @FindBy(xpath = "//button[normalize-space()='Prolong Stay']")
    public WebElement prolongStayButton;

    @FindBy(xpath = "//input[@placeholder='Select data of discharge ...']")
    public WebElement dischargeDateField;

    @FindBy(xpath = "//div[@class='mx-name-datePicker1 mx-datepicker form-group']//button[@aria-label='Show date picker']")
    public WebElement datePickerProlong;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    public WebElement saveButtonProlong;

    @FindBy(xpath = "//div[@class='mx-compound-control']//input[@type='text']")
    public WebElement dischargeDatePatientDetails;



    public Locators(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
