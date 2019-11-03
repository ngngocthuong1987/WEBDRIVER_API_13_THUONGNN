package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_05_XpathAndCss {

	// CONSTANT: Message expected
	static final String REQUIRED_ERROR_MESSAGE = "This is a required field.";
	static final String EMAIL_ERROR_MESSAGE = "Please enter a valid email address. For example johndoe@domain.com.";
	static final String SHORT_PASSWORD_ERROR_MESSAGE = "Please enter 6 or more characters without leading or trailing spaces.";
	static final String LOGIN_ERROR_MESSAGE = "Invalid login or password.";
	static final String REGISTER_SUCCESS_MESSAGE = "Thank you for registering with Main Website Store.";

	// CONSTANT: Website elements
	static final String WEBSITE_URL = "http://live.demoguru99.com/";
	static final String HOME_PAGE_TITLE = "Home page";

	// CONSTANT: XPath of link, button
	static final String XPATH_ACCOUNT_LINK = "//div[@class='account-cart-wrapper']//span[text()='Account']";
	static final String XPATH_MY_ACCOUNT_LINK = "//div[@class='footer']//a[@title='My Account']";
	static final String XPATH_LOGIN_BUTTON = "//button[@id='send2']";
	static final String XPATH_INPUT_EMAIL_LOGIN = "//input[@id='email']";
	static final String XPATH_INPUT_PASSWORD_LOGIN = "//input[@id='pass']";
	static final String XPATH_CREATE_ACCOUNT_BUTTON = "//a[@title='Create an Account']";
	static final String XPATH_INPUT_FIRST_NAME = "//input[@id='firstname']";
	static final String XPATH_INPUT_LAST_NAME = "//input[@id='lastname']";
	static final String XPATH_INPUT_EMAIL = "//input[@id='email_address']";
	static final String XPATH_INPUT_PASSWORD = "//input[@id='password']";
	static final String XPATH_INPUT_CONFIRM_PASSWORD = "//input[@id='confirmation']";
	static final String XPATH_REGISTER_BUTTON = "//button[@title='Register']";
	static final String XPATH_LOGOUT_LINK = "//a[@title='Log Out']";

	// CONSTANT: XPath of message validate
	static final String XPATH_REQUIRED_EMAIL_MESSAGE = "//div[@id='advice-required-entry-email']";
	static final String XPATH_REQUIRED_PASS_MESSAGE = "//div[@id='advice-required-entry-pass']";
	static final String XPATH_EMAIL_INVALID_MESSAGE = "//div[@id='advice-validate-email-email']";
	static final String XPATH_SHORT_PASSWORD_MESSAGE = "//div[@id='advice-validate-password-pass']";
	static final String XPATH_INVALID_LOGIN_MESSAGE = "//li[@class='error-msg']";
	static final String XPATH_REGISTER_SUCCESS_MESSAGE = "//li[@class='success-msg']";

	// CONSTANT: Xpath of login information
	static final String MY_DASHBOARD = "//h1[text()='My Dashboard']";
	static final String HELLO_MESSAGE = "//strong[text()='Hello, Automation Testing!']";
	static final String CONTACT_INFO = "//div[@class='box-content']//p[contains(text(),'Automation Testing')]";

	WebDriver driver;

	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void goToWebAndClickMyAccountLink() {
		driver.get(WEBSITE_URL);
		driver.findElement(By.xpath(XPATH_MY_ACCOUNT_LINK)).click();
	}

	@Test
	public void TC_01_LoginEmpty() {
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		Assert.assertEquals(driver.findElement(By.xpath(XPATH_REQUIRED_EMAIL_MESSAGE)).getText(), REQUIRED_ERROR_MESSAGE);
		Assert.assertEquals(driver.findElement(By.xpath(XPATH_REQUIRED_PASS_MESSAGE)).getText(), REQUIRED_ERROR_MESSAGE);
	}

	@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		Assert.assertEquals(driver.findElement(By.xpath(XPATH_EMAIL_INVALID_MESSAGE)).getText(), EMAIL_ERROR_MESSAGE);
	}

	@Test
	public void TC_03_LoginWithShortPassword() {
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(XPATH_INPUT_PASSWORD_LOGIN)).sendKeys("123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		Assert.assertEquals(driver.findElement(By.xpath(XPATH_SHORT_PASSWORD_MESSAGE)).getText(), SHORT_PASSWORD_ERROR_MESSAGE);
	}

	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(XPATH_INPUT_PASSWORD_LOGIN)).sendKeys("123123123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		Assert.assertEquals(driver.findElement(By.xpath(XPATH_INVALID_LOGIN_MESSAGE)).getText(), LOGIN_ERROR_MESSAGE);
	}

	@Test
	public void TC_05_LoginWithEmailValid() {
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("automation_13@gmail.com");
		driver.findElement(By.xpath(XPATH_INPUT_PASSWORD_LOGIN)).sendKeys("123123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		Assert.assertTrue(driver.findElement(By.xpath(MY_DASHBOARD)).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(HELLO_MESSAGE)).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(CONTACT_INFO)).isDisplayed());

		//If not logout in test case 5, when click to My Account link will redirect to detail account page In test case 6
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_LOGOUT_LINK)).click();
	}

	@Test
	public void TC_06_CreateAnAccount() {
		driver.findElement(By.xpath(XPATH_CREATE_ACCOUNT_BUTTON)).click();
		driver.findElement(By.xpath(XPATH_INPUT_FIRST_NAME)).sendKeys("THUONG");
		driver.findElement(By.xpath(XPATH_INPUT_LAST_NAME)).sendKeys("NGUYEN");
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL)).sendKeys(getRandomEmail());
		driver.findElement(By.xpath(XPATH_INPUT_PASSWORD)).sendKeys("abc123456");
		driver.findElement(By.xpath(XPATH_INPUT_CONFIRM_PASSWORD)).sendKeys("abc123456");
		driver.findElement(By.xpath(XPATH_REGISTER_BUTTON)).click();
		Assert.assertEquals(driver.findElement(By.xpath(XPATH_REGISTER_SUCCESS_MESSAGE)).getText(), REGISTER_SUCCESS_MESSAGE);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_LOGOUT_LINK)).click();

		// Wait browser redirect 20 seconds
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleIs(HOME_PAGE_TITLE));
		Assert.assertEquals(HOME_PAGE_TITLE, driver.getTitle());
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}

/*
 * The method create random email
 */
	public String getRandomEmail() {
		String randomChar = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder emailString = new StringBuilder();
		Random rnd = new Random();
		while (emailString.length() < 10) {
			int index = rnd.nextInt(randomChar.length());
			emailString.append(randomChar.charAt(index));
		}
		return emailString.toString().concat("@gmail.com");
	}
}
