package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

	// XPath of link, button
	static final String XPATH_ACCOUNT_LINK = ".//*[@id='header']/div/div[2]/div/a";
	static final String XPATH_MY_ACCOUNT_LINK = ".//*[@id='header-account']/div/ul/li[1]/a";
	static final String XPATH_LOGIN_BUTTON = ".//*[@id='send2']";
	static final String XPATH_INPUT_EMAIL_LOGIN = ".//*[@id='email']";
	static final String XPATH_INPUT_PASSWORD_LOGIN = ".//*[@id='pass']";
	static final String XPATH_CREATE_ACCOUNT_BUTTON = ".//*[@id='login-form']/div/div[1]/div[2]/a";
	static final String XPATH_INPUT_FIRST_NAME = ".//*[@id='firstname']";
	static final String XPATH_INPUT_LAST_NAME = ".//*[@id='lastname']";
	static final String XPATH_INPUT_EMAIL = ".//*[@id='email_address']";
	static final String XPATH_INPUT_PASSWORD = ".//*[@id='password']";
	static final String XPATH_INPUT_CONFIRM_PASSWORD = ".//*[@id='confirmation']";
	static final String XPATH_REGISTER_BUTTON = ".//*[@id='form-validate']/div[2]/button";
	static final String XPATH_LOGOUT_LINK = ".//*[@id='header-account']/div/ul/li[5]/a";

	// XPath of message
	static final String XPATH_REQUIRED_EMAIL_MESSAGE = ".//*[@id='advice-required-entry-email']";
	static final String XPATH_REQUIRED_PASS_MESSAGE = ".//*[@id='advice-required-entry-pass']";
	static final String XPATH_EMAIL_INVALID_MESSAGE = ".//*[@id='advice-validate-email-email']";
	static final String XPATH_SHORT_PASSWORD_MESSAGE = ".//*[@id='advice-validate-password-pass']";
	static final String XPATH_INVALID_LOGIN_MESSAGE = ".//*[@id='top']/body/div/div/div[2]/div/div/div/ul/li/ul/li/span";
	static final String XPATH_REGISTER_SUCCESS_MESSAGE = ".//*[@id='top']/body/div/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span";

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
		String resultTextEmail = driver.findElement(By.xpath(XPATH_REQUIRED_EMAIL_MESSAGE)).getText();
		String resultTextId = driver.findElement(By.xpath(XPATH_REQUIRED_PASS_MESSAGE)).getText();
		Assert.assertEquals(resultTextEmail, REQUIRED_ERROR_MESSAGE);
		Assert.assertEquals(resultTextId, REQUIRED_ERROR_MESSAGE);
	}

	@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.get(WEBSITE_URL);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_MY_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		String resultText = driver.findElement(By.xpath(XPATH_EMAIL_INVALID_MESSAGE)).getText();
		Assert.assertEquals(resultText, EMAIL_ERROR_MESSAGE);
	}

	@Test
	public void TC_03_LoginWithShortPassword() {
		driver.get(WEBSITE_URL);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_MY_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(XPATH_INPUT_PASSWORD_LOGIN)).sendKeys("123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		String resultText = driver.findElement(By.xpath(XPATH_SHORT_PASSWORD_MESSAGE)).getText();
		Assert.assertEquals(resultText, SHORT_PASSWORD_ERROR_MESSAGE);
	}

	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.get(WEBSITE_URL);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_MY_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_INPUT_EMAIL_LOGIN)).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(XPATH_INPUT_PASSWORD_LOGIN)).sendKeys("123123123");
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		String resultText = driver.findElement(By.xpath(XPATH_INVALID_LOGIN_MESSAGE)).getText();
		Assert.assertEquals(resultText, LOGIN_ERROR_MESSAGE);
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
		String resultText = driver.findElement(By.xpath(XPATH_REGISTER_SUCCESS_MESSAGE)).getText();
		Assert.assertEquals(resultText, REGISTER_SUCCESS_MESSAGE);
		driver.findElement(By.xpath(XPATH_ACCOUNT_LINK)).click();
		driver.findElement(By.xpath(XPATH_LOGOUT_LINK)).click();
		String homepageTitle = driver.getTitle();
		Assert.assertEquals(homepageTitle, "Home page");
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
			int index = (int) (rnd.nextFloat() * randomChar.length());
			emailString.append(randomChar.charAt(index));
		}
		return emailString.toString().concat("@gmail.com");
	}
}
