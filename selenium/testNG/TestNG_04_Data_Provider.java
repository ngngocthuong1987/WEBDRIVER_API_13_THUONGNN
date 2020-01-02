package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_04_Data_Provider {

	WebDriver driver;
	By emailTextBox = By.xpath("//input[@id='email']");
	By passwordTextBox = By.xpath("//input[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

	}

	@Test(dataProvider = "user_pass")
	public void TC_01_LoginToSystem(String user, String pass) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(emailTextBox).sendKeys(user);
		driver.findElement(passwordTextBox).sendKeys(pass);
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(user));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@DataProvider(name="user_pass")
	public Object[][] UserAndPassData() {
		return new Object[][] {
			{"automation_01@gmail.com", "abc123"}
			, {"automation_02@gmail.com", "abc123"}
			, {"automation_03@gmail.com", "abc123"}
		};
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
