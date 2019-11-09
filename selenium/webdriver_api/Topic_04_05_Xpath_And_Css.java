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

public class Topic_04_05_Xpath_And_Css {

	// Setting varriables
	WebDriver driver;
	String invalidEmail = "123434234@12312.123123";
	String validEmail = "automation@gmail.com";
	String shortPassword = "123";
	String invalidPassword = "123123123";
	String firstName = "THUONG";
	String lastName = "NGUYEN";
	String email = getRandomEmail();
	String validPassword = "123456789";

	// CONSTANT: Message expected
	static final String REQUIRED_ERROR_MESSAGE = "This is a required field.";
	static final String EMAIL_ERROR_MESSAGE = "Please enter a valid email address. For example johndoe@domain.com.";
	static final String SHORT_PASSWORD_ERROR_MESSAGE = "Please enter 6 or more characters without leading or trailing spaces.";
	static final String LOGIN_ERROR_MESSAGE = "Invalid login or password.";
	static final String REGISTER_SUCCESS_MESSAGE = "Thank you for registering with Main Website Store.";

	// CONSTANT: Website elements
	static final String WEBSITE_URL = "http://live.demoguru99.com/";
	static final String HOME_PAGE_TITLE = "Home page";

	// Setting some By object of elements xpath
	By accountLink = By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']");
	By myAccountLink = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By loginButton = By.xpath("//button[@id='send2']");
	By emailLoginInput = By.xpath("//input[@id='email']");
	By passwordLoginInput = By.xpath("//input[@id='pass']");
	By createAccountButton = By.xpath("//a[@title='Create an Account']");
	By firstNameInput = By.xpath("//input[@id='firstname']");
	By lastNameInput = By.xpath("//input[@id='lastname']");
	By emailInput = By.xpath("//input[@id='email_address']");
	By passWordInput = By.xpath("//input[@id='password']");
	By passWordConfirmInput = By.xpath("//input[@id='confirmation']");
	By registerButton = By.xpath("//button[@title='Register']");
	By logoutLink = By.xpath("//a[@title='Log Out']");
	By requiredEmailMessage = By.xpath("//div[@id='advice-required-entry-email']");
	By requiredPasswordMessage = By.xpath("//div[@id='advice-required-entry-pass']");
	By invalidEmailMessage = By.xpath("//div[@id='advice-validate-email-email']");
	By shortPasswordMessage = By.xpath("//div[@id='advice-validate-password-pass']");
	By invalidLoginMessage = By.xpath("//li[@class='error-msg']");
	By registerSuccessMessage = By.xpath("//li[@class='success-msg']");
	By myDashboard = By.xpath("//h1[text()='My Dashboard']");
	By helloMessage = By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']");
	By contactInfor = By.xpath("//div[@class='box-content']//p[contains(text(),'"+ firstName + " " + lastName+"')]");
	By emailInfor = By.xpath("//div[@class='box-content']//p[contains(text(),'"+ email +"')]");

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
		driver.findElement(myAccountLink).click();
	}

	@Test
	public void TC_01_LoginEmpty() {
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(requiredEmailMessage).getText(), REQUIRED_ERROR_MESSAGE);
		Assert.assertEquals(driver.findElement(requiredPasswordMessage).getText(), REQUIRED_ERROR_MESSAGE);
	}

	@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.findElement(emailLoginInput).sendKeys(invalidEmail);
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(invalidEmailMessage).getText(), EMAIL_ERROR_MESSAGE);
	}

	@Test
	public void TC_03_LoginWithShortPassword() {
		driver.findElement(emailLoginInput).sendKeys(validEmail);
		driver.findElement(passwordLoginInput).sendKeys(shortPassword);
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(shortPasswordMessage).getText(), SHORT_PASSWORD_ERROR_MESSAGE);
	}

	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.findElement(emailLoginInput).sendKeys(validEmail);
		driver.findElement(passwordLoginInput).sendKeys(invalidPassword);
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(invalidLoginMessage).getText(), LOGIN_ERROR_MESSAGE);
	}

	@Test
	public void TC_05_CreateAnAccount() {
		driver.findElement(createAccountButton).click();
		driver.findElement(firstNameInput).sendKeys(firstName);
		driver.findElement(lastNameInput).sendKeys(lastName);
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(passWordInput).sendKeys(validPassword);
		driver.findElement(passWordConfirmInput).sendKeys(validPassword);
		driver.findElement(registerButton).click();
		Assert.assertEquals(driver.findElement(registerSuccessMessage).getText(), REGISTER_SUCCESS_MESSAGE);
		driver.findElement(accountLink).click();
		driver.findElement(logoutLink).click();

		// Wait browser redirect 20 seconds
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleIs(HOME_PAGE_TITLE));
		Assert.assertEquals(HOME_PAGE_TITLE, driver.getTitle());
	}

	@Test
	public void TC_06_LoginWithEmailValid() {
		driver.findElement(emailLoginInput).sendKeys(email);
		driver.findElement(passwordLoginInput).sendKeys(validPassword);
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(myDashboard).isDisplayed());
		Assert.assertTrue(driver.findElement(helloMessage).isDisplayed());
		Assert.assertTrue(driver.findElement(contactInfor).isDisplayed());
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
