package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic_07_08_TextBox_TextArea_DropdownList {

	// Setting constant
	static final String WEBSITE_URL_TC01 = "http://demo.guru99.com/v4";
	static final String HOME_PAGE_TITLE = "Guru99 Bank Manager HomePage";

	// Setting some By object of elements xpath
	By userIdInput = By.xpath("//input[@name='uid']");
	By passWordInput = By.xpath("//input[@name='password']");
	By loginButton = By.xpath("//input[@name='btnLogin']");
	By newCustomerLink = By.xpath("//a[@href='addcustomerpage.php']");
	By editCustomerLink = By.xpath("//a[@href='EditCustomer.php']");
	By customerNameInput = By.xpath("//input[@name='name']");
	By maleRadioButton = By.xpath("//input[@name='rad1' and @value='m']");
	By birthDayInput = By.xpath("//input[@id='dob']");
	By addressTextArea = By.xpath("//textarea[@name='addr']");
	By cityInput = By.xpath("//input[@name='city']");
	By stateInput = By.xpath("//input[@name='state']");
	By pinInput = By.xpath("//input[@name='pinno']");
	By mobileNumberInput = By.xpath("//input[@name='telephoneno']");
	By emailInput = By.xpath("//input[@name='emailid']");
	By createCustomerSubmitButton = By.xpath("//input[@name='sub']");
	By editCustomerSubmitButton = By.xpath("//input[@name='AccSubmit']");
	By customerIdInput = By.xpath("//input[@name='cusid']");

	//Setting some By object for verify value
	By customerIdVerify = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By customerNameVerify = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By genderVerify = By.xpath("//td[text()='Gender']/following-sibling::td");
	By birthDayVerify = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By addressVerify = By.xpath("//td[text()='Address']/following-sibling::td");
	By cityVerify = By.xpath("//td[text()='City']/following-sibling::td");
	By stateVerify = By.xpath("//td[text()='State']/following-sibling::td");
	By pinVerify = By.xpath("//td[text()='Pin']/following-sibling::td");
	By mobileNumberVerify = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By emailVerify = By.xpath("//td[text()='Email']/following-sibling::td");

	// Setting varialbes
	WebDriver driver;
	String userName = "mngr232324";
	String passWord = "ErugEju";

	// Data for create new customer
	String customerName = "Nguyen Ngoc Thuong";
	String dateOfBirth = "02/16/1987";
	String addressNew = "K131 Ly Thai To";
	String cityNew = "Da Nang";
	String stateNew = "Thanh Khe";
	String pinNew = "123456";
	String mobileNumberNew = "0979161024";
	String emailNew = Utils.getRandomEmail();
	String passwordNew = "123456";

	// Data for edit customer
	String addressEdit = "245 Le Duan";
	String cityEdit= "Ho Chi Minh";
	String stateEdit = "Tan Binh";
	String pinEdit = "457832";
	String mobileNumberEdit = "0936123123";
	String emailEdit = Utils.getRandomEmail();


	@BeforeClass
	public void launchBrowser() {

		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void goToWebSite() {

	}

	@AfterMethod
	public void sleepBrowser() {
		// Sleep browser 3s
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_01_ProcessWithTextBoxAndTextArea() {

		// Go to page and login
		driver.get(WEBSITE_URL_TC01);
		driver.findElement(userIdInput).sendKeys(userName);
		driver.findElement(passWordInput).sendKeys(passWord);
		driver.findElement(loginButton).click();

		// Verify Homepage display
		Assert.assertTrue(driver.getTitle().equals(HOME_PAGE_TITLE));

		// Go to new customer page, create new customer and submit
		driver.findElement(newCustomerLink).click();
		driver.findElement(customerNameInput).sendKeys(customerName);
		driver.findElement(maleRadioButton).click();
		driver.findElement(birthDayInput).sendKeys(dateOfBirth);
		driver.findElement(addressTextArea).sendKeys(addressNew);
		driver.findElement(cityInput).sendKeys(cityNew);
		driver.findElement(stateInput).sendKeys(stateNew);
		driver.findElement(pinInput).sendKeys(pinNew);
		driver.findElement(mobileNumberInput).sendKeys(mobileNumberNew);
		driver.findElement(emailInput).sendKeys(emailNew);
		driver.findElement(passWordInput).sendKeys(passwordNew);
		driver.findElement(createCustomerSubmitButton).click();

		// Verify new informations are created
		String customerId = driver.findElement(customerIdVerify).getText();
		Assert.assertTrue(driver.findElement(customerNameVerify).getText().equals(customerName));
		Assert.assertTrue(driver.findElement(birthDayVerify).getText().equals(Utils.changeFormatDate(dateOfBirth)));
		Assert.assertTrue(driver.findElement(addressVerify).getText().equals(addressNew));
		Assert.assertTrue(driver.findElement(cityVerify).getText().equals(cityNew));
		Assert.assertTrue(driver.findElement(stateVerify).getText().equals(stateNew));
		Assert.assertTrue(driver.findElement(pinVerify).getText().equals(pinNew));
		Assert.assertTrue(driver.findElement(mobileNumberVerify).getText().equals(mobileNumberNew));
		Assert.assertTrue(driver.findElement(emailVerify).getText().equals(emailNew));

		// Go to edit customer
		driver.findElement(editCustomerLink).click();
		driver.findElement(customerIdInput).sendKeys(customerId);
		driver.findElement(editCustomerSubmitButton).click();

		// Verify customer name and address
		Assert.assertTrue(driver.findElement(customerNameInput).getAttribute("value").equals(customerName));
		Assert.assertTrue(driver.findElement(addressTextArea).getAttribute("value").equals(addressNew));

		// Edit information of some fields not disable
		driver.findElement(addressTextArea).clear();
		driver.findElement(addressTextArea).sendKeys(addressEdit);
		driver.findElement(cityInput).clear();
		driver.findElement(cityInput).sendKeys(cityEdit);
		driver.findElement(stateInput).clear();
		driver.findElement(stateInput).sendKeys(stateEdit);
		driver.findElement(pinInput).clear();
		driver.findElement(pinInput).sendKeys(pinEdit);
		driver.findElement(mobileNumberInput).clear();
		driver.findElement(mobileNumberInput).sendKeys(mobileNumberEdit);
		driver.findElement(emailInput).clear();
		driver.findElement(emailInput).sendKeys(emailEdit);
		driver.findElement(createCustomerSubmitButton).click();

		// Verify new informations after edit
		Assert.assertTrue(driver.findElement(addressVerify).getText().equals(addressEdit));
		Assert.assertTrue(driver.findElement(cityVerify).getText().equals(cityEdit));
		Assert.assertTrue(driver.findElement(stateVerify).getText().equals(stateEdit));
		Assert.assertTrue(driver.findElement(pinVerify).getText().equals(pinEdit));
		Assert.assertTrue(driver.findElement(mobileNumberVerify).getText().equals(mobileNumberEdit));
		Assert.assertTrue(driver.findElement(emailVerify).getText().equals(emailEdit));
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}


}
