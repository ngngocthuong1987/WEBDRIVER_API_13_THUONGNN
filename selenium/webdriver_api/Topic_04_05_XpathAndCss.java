package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_05_XpathAndCss {
	WebDriver driver;
	static final String REQUIRED_ERROR_MESSAGE = "This is a required field.";
	static final String EMAIL_ERROR_MESSAGE = "Please enter a valid email address. For example johndoe@domain.com.";
	static final String SHORT_PASSWORD_ERROR_MESSAGE = "Please enter 6 or more characters without leading or trailing spaces.";
	static final String REGISTER_SUCCESS_MESSAGE = "Thank you for registering with Main Website Store.";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_LoginEmpty() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a")).click();
		driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		String resultTextEmail = driver.findElement(By.xpath(".//*[@id='advice-required-entry-email']")).getText();
		String resultTextId = driver.findElement(By.xpath(".//*[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(resultTextEmail, REQUIRED_ERROR_MESSAGE);
		Assert.assertEquals(resultTextId, REQUIRED_ERROR_MESSAGE);
	}
	
	@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a")).click();
		driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		String resultText = driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(resultText, EMAIL_ERROR_MESSAGE);
	}
	
	@Test
	public void TC_03_LoginWithPasswordIncorrect() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a")).click();
		driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		String resultText = driver.findElement(By.xpath(".//*[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(resultText, SHORT_PASSWORD_ERROR_MESSAGE);
	}
	
	@Test
	public void TC_05_CreateAnAccount() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a")).click();
		driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='login-form']/div/div[1]/div[2]/a")).click();
		driver.findElement(By.xpath(".//*[@id='firstname']")).sendKeys("THUONG");
		driver.findElement(By.xpath(".//*[@id='lastname']")).sendKeys("NGUYEN");
		driver.findElement(By.xpath(".//*[@id='email_address']")).sendKeys(getRandomEmail());
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("abc123456");
		driver.findElement(By.xpath(".//*[@id='confirmation']")).sendKeys("abc123456");
		driver.findElement(By.xpath(".//*[@id='form-validate']/div[2]/button")).click();
		String resultText = driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span")).getText();
		Assert.assertEquals(resultText, REGISTER_SUCCESS_MESSAGE);
		driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a")).click();
		driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[5]/a")).click();
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
