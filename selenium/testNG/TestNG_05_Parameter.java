package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_05_Parameter {

	WebDriver driver;
	By emailTextBox = By.xpath("//input[@id='email']");
	By passwordTextBox = By.xpath("//input[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("chrome_headless")) {
			System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("headless");
			driver = new ChromeDriver(chromeOptions);
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

	}

	@Test(invocationCount = 3)
	public void TC_01_LoginToSystem() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(emailTextBox).sendKeys("automation_01@gmail.com");
		driver.findElement(passwordTextBox).sendKeys("abc123");
		driver.findElement(loginButton).click();

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
