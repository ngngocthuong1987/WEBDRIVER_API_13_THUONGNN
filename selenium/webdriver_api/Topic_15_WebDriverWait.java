package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_WebDriverWait {

	WebDriver driver;
	WebDriverWait explicitWait;

	static final String TC01_URL ="http://demo.guru99.com/v4/";

	By loginButtonBy = By.xpath("//input[@name='btnLogin']");
	By fileUploadBy = By.xpath("//a[text()='File Upload']");
	By emailTextBoxBy = By.xpath("//input[@name='emailid']");

	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Visible_Displayed() {
		driver.get(TC01_URL);

		//Pass - Element displayed in UI and have in DOM
		WebElement loginButton = driver.findElement(loginButtonBy);
		boolean loginButtonStatus = loginButton.isDisplayed();
		System.out.println("Login button status: " + loginButtonStatus);

		//Pass - Element do not displayed in UI and have in DOM
		WebElement fileUpload = driver.findElement(fileUploadBy);
		boolean fileUploadStatus = fileUpload.isDisplayed();
		System.out.println("File upload status: " + fileUploadStatus);

		//Fail - Element do not displayed in UI and do not have in DOM
		WebElement emailTextBox = driver.findElement(emailTextBoxBy);
		boolean emailTextBoxStatus = emailTextBox.isDisplayed();
		System.out.println("File upload status: " + emailTextBoxStatus);
	}

	@Test
	public void TC_02_Invisible() {
		driver.get(TC01_URL);

		//Fail - Element displayed in UI and have in DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loginButtonBy));

		//Pass - Element do not displayed in UI and have in DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(fileUploadBy));

		//Pass - Element do not displayed in UI and do not have in DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(emailTextBoxBy));
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
