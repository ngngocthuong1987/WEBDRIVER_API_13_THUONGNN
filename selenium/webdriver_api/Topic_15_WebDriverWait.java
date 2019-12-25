package webdriver_api;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;


public class Topic_15_WebDriverWait {

	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebElement> fluentWait;

	static final String TC01_URL ="http://demo.guru99.com/v4/";

	By loginButtonBy = By.xpath("//input[@name='btnLogin']");
	By fileUploadBy = By.xpath("//a[text()='File Upload']");
	By emailTextBoxBy = By.xpath("//input[@name='emailid']");
	By signInButtonBy = By.xpath("//button[@id='SubmitLogin']");
	By messageErrorBy = By.xpath("//div[@class='alert alert-danger']");
	By messageErrorNotInDOMBy = By.xpath("//div[@class='alert alert-danger' and not (@id='create_account_error')]");

	By startButton = By.xpath("//button[text()='Start']");
	By resultText = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");
	By loadingIcon = By.xpath("//div[@id='loading']");

	By daySelect = By.xpath("//a[text()='26']");
	By daySelected = By.xpath("//a[text()='26']/parent::td[@class='rcSelected']");
	By textDaySelected = By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']");
	By loadingAjaxIcon = By.xpath("//div[@class='raDiv']");

	By countDown = By.xpath("//div[@id='javascript_countdown_time']");

	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
	public void TC_01_Invisible() {
		driver.get(TC01_URL);

		//Fail - Element displayed in UI and have in DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loginButtonBy));

		//Pass - Element do not displayed in UI and have in DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(fileUploadBy));

		//Pass - Element do not displayed in UI and do not have in DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(emailTextBoxBy));
	}

	@Test
	public void TC_01_Presence() {
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		//Pass - Element displayed in UI and have in DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(signInButtonBy));

		//Pass - Element do not displayed in UI and have in DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(messageErrorBy));

		//Fail - Element do not displayed in UI and do not have in DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(messageErrorNotInDOMBy));
	}

	@Test
	public void TC_02_ImplicitWait_Fail() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(resultText).isDisplayed());
	}

	@Test
	public void TC_02_ImplicitWait_Pass() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(resultText).isDisplayed());
	}

	@Test
	public void TC_03_ExplicitWait_Invisible_03_Seconds() {
		explicitWait = new WebDriverWait(driver, 3);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(startButton).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(resultText).isDisplayed());
	}

	@Test
	public void TC_03_ExplicitWait_Invisible_05_Seconds() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(startButton).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(resultText).isDisplayed());
	}

	@Test
	public void TC_04_ExplicitWait_Visible_03_Seconds() {
		explicitWait = new WebDriverWait(driver, 3);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(startButton).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(resultText));
		Assert.assertTrue(driver.findElement(resultText).isDisplayed());
	}

	@Test
	public void TC_04_ExplicitWait_Visible_05_Seconds() {
		explicitWait = new WebDriverWait(driver, 1);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(startButton).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(resultText));
		Assert.assertTrue(driver.findElement(resultText).isDisplayed());
	}

	@Test
	public void TC_05_ExplicitWait() {
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		Assert.assertEquals(driver.findElement(textDaySelected).getText(), "No Selected Dates to display.");

		driver.findElement(daySelect).click();
		Assert.assertTrue(driver.findElement(daySelected).isDisplayed());

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingAjaxIcon));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(daySelected));
		Assert.assertEquals(driver.findElement(textDaySelected).getText(), "Thursday, December 26, 2019");

	}

	@Test
	public void TC_06_FluentWait() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countDownElement = driver.findElement(countDown);
		explicitWait.until(ExpectedConditions.visibilityOf(countDownElement));

		Predicate<WebElement> fluentWaitCondition = element -> {return element.getText().endsWith("02");};

		fluentWait = new FluentWait<WebElement>(countDownElement);
		fluentWait.withTimeout(15, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(fluentWaitCondition);
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
