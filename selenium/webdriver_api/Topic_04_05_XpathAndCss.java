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
import org.testng.annotations.Test;

public class Topic_04_05_XpathAndCss {

	// Message constant
	static final String REQUIRED_ERROR_MESSAGE = "This is a required field.";
	static final String EMAIL_ERROR_MESSAGE = "Please enter a valid email address. For example johndoe@domain.com.";
	static final String SHORT_PASSWORD_ERROR_MESSAGE = "Please enter 6 or more characters without leading or trailing spaces.";
	static final String LOGIN_ERROR_MESSAGE = "Invalid login or password.";
	static final String REGISTER_SUCCESS_MESSAGE = "Thank you for registering with Main Website Store.";
	static final String HOME_PAGE_TITLE = "Home page";

	// XPath of link, button
	static final String XPATH_ACCOUNT_LINK = "//div[@class='account-cart-wrapper']//span[text()='Account']";
	static final String XPATH_MY_ACCOUNT_LINK = "//div[@id='header-account']//a[@title='My Account']";
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

	// XPath of message
	static final String XPATH_REQUIRED_EMAIL_MESSAGE = "//div[@id='advice-required-entry-email']";
	static final String XPATH_REQUIRED_PASS_MESSAGE = "//div[@id='advice-required-entry-pass']";
	static final String XPATH_EMAIL_INVALID_MESSAGE = "//div[@id='advice-validate-email-email']";
	static final String XPATH_SHORT_PASSWORD_MESSAGE = "//div[@id='advice-validate-password-pass']";
	static final String XPATH_INVALID_LOGIN_MESSAGE = "//li[@class='error-msg']";
	static final String XPATH_REGISTER_SUCCESS_MESSAGE = "//li[@class='success-msg']";

	static final String WEBSITE_URL = "http://live.demoguru99.com/";
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_LoginEmpty() {
		driver.get(WEBSITE_URL);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_MY_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		Assert.assertEquals(REQUIRED_ERROR_MESSAGE, driver.findElement(By.xpath(XPATH_REQUIRED_EMAIL_MESSAGE)).getText());
		Assert.assertEquals(REQUIRED_ERROR_MESSAGE, driver.findElement(By.xpath(XPATH_REQUIRED_PASS_MESSAGE)).getText());
	}

	@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.get(WEBSITE_URL);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_MY_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		Assert.assertEquals(EMAIL_ERROR_MESSAGE, driver.findElement(By.xpath(XPATH_EMAIL_INVALID_MESSAGE)).getText());
	}

	@Test
	public void TC_03_LoginWithShortPassword() {
		driver.get(WEBSITE_URL);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_MY_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(XPATH_INPUT_PASSWORD_LOGIN)).sendKeys("123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		Assert.assertEquals(SHORT_PASSWORD_ERROR_MESSAGE, driver.findElement(By.xpath(XPATH_SHORT_PASSWORD_MESSAGE)).getText());
	}

	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.get(WEBSITE_URL);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_MY_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(XPATH_INPUT_PASSWORD_LOGIN)).sendKeys("123123123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		Assert.assertEquals(LOGIN_ERROR_MESSAGE, driver.findElement(By.xpath(XPATH_INVALID_LOGIN_MESSAGE)).getText());
	}

	@Test
	public void TC_05_CreateAnAccount() {
		driver.get(WEBSITE_URL);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_MY_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_CREATE_ACCOUNT_BUTTON)).click();
		driver.findElement(By.xpath(XPATH_INPUT_FIRST_NAME)).sendKeys("THUONG");
		driver.findElement(By.xpath(XPATH_INPUT_LAST_NAME)).sendKeys("NGUYEN");
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL)).sendKeys(getRandomEmail());
		driver.findElement(By.xpath(XPATH_INPUT_PASSWORD)).sendKeys("abc123456");
		driver.findElement(By.xpath(XPATH_INPUT_CONFIRM_PASSWORD)).sendKeys("abc123456");
		driver.findElement(By.xpath(XPATH_REGISTER_BUTTON)).click();
		Assert.assertEquals(REGISTER_SUCCESS_MESSAGE, driver.findElement(By.xpath(XPATH_REGISTER_SUCCESS_MESSAGE)).getText());
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_LOGOUT_LINK)).click();

		// Wait browser redirect 20 seconds
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleIs(HOME_PAGE_TITLE));
		Assert.assertEquals(HOME_PAGE_TITLE, driver.getTitle());
	}

	@AfterClass
	public void afterClass() {
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
