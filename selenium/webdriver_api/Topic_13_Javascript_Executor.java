package webdriver_api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	/*Setting some By object of elements xpath */
	//Test case 01
	By mobileLink = By.xpath("//a[text()='Mobile']");
	By buttonAddToCart = By.xpath("//h2/a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
	By successMessage = By.xpath("//li[@class='success-msg']//span");
	By customerServiceLink = By.xpath("//a[text()='Customer Service']");
	By inputNewsLetter = By.xpath("//input[@id='newsletter']");
	By lastText = By.xpath("//dd[last()]");

	//Test case 02
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

	//Test case 03
	By myAccountLink = By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]");
	By accountLink = By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']");
	By createAccountButton = By.xpath("//a[@title='Create an Account']");
	By firstNameInput = By.xpath("//input[@id='firstname']");
	By lastNameInput = By.xpath("//input[@id='lastname']");
	By mailInput = By.xpath("//input[@id='email_address']");
	By passInput = By.xpath("//input[@id='password']");
	By passWordConfirmInput = By.xpath("//input[@id='confirmation']");
	By registerButton = By.xpath("//button[@title='Register']");
	By logoutLink = By.xpath("//a[@title='Log Out']");
	By requiredEmailMessage = By.xpath("//div[@id='advice-required-entry-email']");
	By requiredPasswordMessage = By.xpath("//div[@id='advice-required-entry-pass']");
	By invalidEmailMessage = By.xpath("//div[@id='advice-validate-email-email']");
	By shortPasswordMessage = By.xpath("//div[@id='advice-validate-password-pass']");
	By invalidLoginMessage = By.xpath("//li[@class='error-msg']");
	By registerSuccessMessage = By.xpath("//li[@class='success-msg']");

	//Data for create new customer
	String userName = "mngr236337";
	String passWord = "retUqEn";
	String customerName = "Nguyen Ngoc Thuong";
	String dateOfBirth = "02/16/1987";
	String addressNew = "K131 Ly Thai To";
	String cityNew = "Da Nang";
	String stateNew = "Thanh Khe";
	String pinNew = "123456";
	String mobileNumberNew = "0979161024";
	String emailNew = Utils.getRandomEmail();
	String passwordNew = "123456";

	//Data for test case 03
	String firstName = "THUONG";
	String lastName = "NGUYEN";
	String email = Utils.getRandomEmail();
	String validPassword = "123456789";

	//Contants
	static final String REGISTER_SUCCESS_MESSAGE = "Thank you for registering with Main Website Store.";
	static final String HOME_PAGE_TITLE = "Home page";

	@BeforeClass
	public void launchBrowser() {
		// Setting disable notification in Chrome
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);

		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver(options);
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Javascript_Executor() {
		jsExecutor.executeScript("window.location='http://live.demoguru99.com/'");

		//Get domain
		String domain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(domain, "live.demoguru99.com");

		//Get Url
		String url = (String) jsExecutor.executeScript("return document.URL");
		Assert.assertEquals(url, "http://live.demoguru99.com/");

		//Open MOBILE page
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(mobileLink));

		//Click button add to cart
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(buttonAddToCart));

		//Get success message
		String message = (String) jsExecutor.executeScript("return arguments[0].innerText", driver.findElement(successMessage));
		Assert.assertEquals(message, "Samsung Galaxy was added to your shopping cart.");

		//Open customer service page
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(customerServiceLink));
		String title = (String) jsExecutor.executeScript("return document.title");
		Assert.assertEquals(title, "Customer Service");

		//Scroll to newsletter input
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(inputNewsLetter));

		//Get text
		String text = (String) jsExecutor.executeScript("return arguments[0].innerText", driver.findElement(lastText));
		Assert.assertTrue(text.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));

		//Negative to URL:http://demo.guru99.com/v4/
		jsExecutor.executeScript("window.location='http://demo.guru99.com/v4/'");
		String negativeDomain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(negativeDomain, "demo.guru99.com");
	}

	@Test
	public void TC_02_RemoveAttribute() throws InterruptedException {
		// Go to page and login
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(userIdInput).sendKeys(userName);
		driver.findElement(passWordInput).sendKeys(passWord);
		driver.findElement(loginButton).click();

		// Go to new customer page, create new customer and submit
		driver.findElement(newCustomerLink).click();
		driver.findElement(customerNameInput).sendKeys(customerName);
		driver.findElement(maleRadioButton).click();

		//Remove attribute
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(birthDayInput));
		Thread.sleep(5000);

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
		Assert.assertTrue(Utils.getTextElement(driver.findElement(customerNameVerify)).equals(customerName));
		Assert.assertTrue(Utils.getTextElement(driver.findElement(birthDayVerify)).equals(Utils.changeFormatDate(dateOfBirth)));
		Assert.assertTrue(Utils.getTextElement(driver.findElement(addressVerify)).equals(addressNew));
		Assert.assertTrue(Utils.getTextElement(driver.findElement(cityVerify)).equals(cityNew));
		Assert.assertTrue(Utils.getTextElement(driver.findElement(stateVerify)).equals(stateNew));
		Assert.assertTrue(Utils.getTextElement(driver.findElement(pinVerify)).equals(pinNew));
		Assert.assertTrue(Utils.getTextElement(driver.findElement(mobileNumberVerify)).equals(mobileNumberNew));
		Assert.assertTrue(Utils.getTextElement(driver.findElement(emailVerify)).equals(emailNew));
	}

	@Test
	public void TC_03_CreateAnAccount() {
		jsExecutor.executeScript("window.location='http://live.demoguru99.com/'");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(myAccountLink));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(createAccountButton));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + firstName + "')", driver.findElement(firstNameInput));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + lastName + "')", driver.findElement(lastNameInput));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + email + "')", driver.findElement(mailInput));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + validPassword + "')", driver.findElement(passInput));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + validPassword + "')", driver.findElement(passWordConfirmInput));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(registerButton));

		//Get text
		String successText = (String) jsExecutor.executeScript("return arguments[0].innerText", driver.findElement(registerSuccessMessage));
		Assert.assertEquals(successText, REGISTER_SUCCESS_MESSAGE);

		jsExecutor.executeScript("arguments[0].click()", driver.findElement(accountLink));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(logoutLink));

		// Wait browser redirect 20 seconds
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleIs(HOME_PAGE_TITLE));

		String pageTitle = (String) jsExecutor.executeScript("return document.title");
		Assert.assertEquals(pageTitle, HOME_PAGE_TITLE);
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
