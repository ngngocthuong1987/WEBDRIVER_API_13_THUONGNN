package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic_07_08_TextBox_TextArea_DropdownList {

	// Setting constant
	static final String WEBSITE_URL_TC01 = "http://demo.guru99.com/v4";
	static final String WEBSITE_URL_TC02 = "https://automationfc.github.io/basic-form/index.html";
	static final String WEBSITE_URL_JQUERY_TC03 = "http://jqueryui.com/resources/demos/selectmenu/default.html";
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
	By jobRole01Select = By.xpath("//select[@id='job1']");

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

	By jQueryDropdown = By.xpath("//span[@id='number-button']");
	By allItemsJQueryDropdown = By.xpath("//ul[@id='number-menu']/li/div");
	By valueItemExpected = By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']");

	// Setting varialbes
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor jsExecutor;
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
		waitExplicit = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
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
		String customerId = Utils.getTextElement(driver, customerIdVerify);
		Assert.assertTrue(Utils.getTextElement(driver, customerNameVerify).equals(customerName));
		Assert.assertTrue(Utils.getTextElement(driver, birthDayVerify).equals(Utils.changeFormatDate(dateOfBirth)));
		Assert.assertTrue(Utils.getTextElement(driver, addressVerify).equals(addressNew));
		Assert.assertTrue(Utils.getTextElement(driver, cityVerify).equals(cityNew));
		Assert.assertTrue(Utils.getTextElement(driver, stateVerify).equals(stateNew));
		Assert.assertTrue(Utils.getTextElement(driver, pinVerify).equals(pinNew));
		Assert.assertTrue(Utils.getTextElement(driver, mobileNumberVerify).equals(mobileNumberNew));
		Assert.assertTrue(Utils.getTextElement(driver, emailVerify).equals(emailNew));

		// Go to edit customer
		driver.findElement(editCustomerLink).click();
		driver.findElement(customerIdInput).sendKeys(customerId);
		driver.findElement(editCustomerSubmitButton).click();

		// Verify customer name and address
		Assert.assertTrue(driver.findElement(customerNameInput).getAttribute("value").equals(customerName));
		Assert.assertTrue(driver.findElement(addressTextArea).getAttribute("value").equals(addressNew));

		// Edit information of some fields not disable
		Utils.sendKeyElement(driver, addressTextArea, addressEdit);
		Utils.sendKeyElement(driver, cityInput, cityEdit);
		Utils.sendKeyElement(driver, stateInput, stateEdit);
		Utils.sendKeyElement(driver, pinInput, pinEdit);
		Utils.sendKeyElement(driver, mobileNumberInput, mobileNumberEdit);
		Utils.sendKeyElement(driver, emailInput, emailEdit);
		driver.findElement(createCustomerSubmitButton).click();

		// Verify new informations after edit
		Assert.assertTrue(Utils.getTextElement(driver, addressVerify).equals(addressEdit));
		Assert.assertTrue(Utils.getTextElement(driver, cityVerify).equals(cityEdit));
		Assert.assertTrue(Utils.getTextElement(driver, stateVerify).equals(stateEdit));
		Assert.assertTrue(Utils.getTextElement(driver, pinVerify).equals(pinEdit));
		Assert.assertTrue(Utils.getTextElement(driver, mobileNumberVerify).equals(mobileNumberEdit));
		Assert.assertTrue(Utils.getTextElement(driver, emailVerify).equals(emailEdit));
	}

	@Test
	public void TC_02_ProcessWithDropdownAndList() {
		driver.get(WEBSITE_URL_TC02);
		Select jobRole01 = new Select(driver.findElement(jobRole01Select));

		// Check dropdown is multi select
		Assert.assertFalse(jobRole01.isMultiple());

		// Select by method selectByVisibleText() and check
		jobRole01.selectByVisibleText("Automation Tester");
		Assert.assertEquals(jobRole01.getFirstSelectedOption().getText(), "Automation Tester");

		// Select by method selectByValue() and check
		jobRole01.selectByValue("manual");
		Assert.assertEquals(jobRole01.getFirstSelectedOption().getText(), "Manual Tester");

		// Select by method selectByIndex() and check
		jobRole01.selectByIndex(3);
		Assert.assertEquals(jobRole01.getFirstSelectedOption().getText(), "Mobile Tester");

		// Check size of dropdown is 5
		Assert.assertEquals(5, jobRole01.getOptions().size());
	}

	@Test
	public void TC_03_JQueryDropdown() {
		try {
			driver.get(WEBSITE_URL_JQUERY_TC03);
			selectItemInDropdown(jQueryDropdown, allItemsJQueryDropdown, "19");
			Assert.assertEquals(Utils.getTextElement(driver, valueItemExpected), "19");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * The method get expected item in dropdown
	 */
	public void selectItemInDropdown(By dropdownBy, By allItemInDropdownBy, String expectedItemValue) throws InterruptedException {
		// Click in dropdown for display all items
		WebElement paretnDropdown = driver.findElement(dropdownBy);
		paretnDropdown.click();
		Thread.sleep(3000);

		// Wait all items loaded
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemInDropdownBy));

		List<WebElement> allItems = driver.findElements(allItemInDropdownBy);
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItemValue)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				Thread.sleep(1000);
				item.click();
				break;
			}
		}
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}


}
