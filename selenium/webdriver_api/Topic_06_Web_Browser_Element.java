package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic_06_Web_Browser_Element {

	// Setting constant
	static final String WEBSITE_URL = "https://automationfc.github.io/basic-form/index.html";
	static final String HOME_PAGE_TITLE = "SELENIUM WEBDRIVER FORM DEMO";
	static final String VALUE_INPUT_TC01 = "Automation Testing";

	// Setting some By object of elements xpath
	By emailTextBox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.xpath("//input[@id='under_18']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By jobRoleSelect = By.xpath("//select[@id='job1']");
	By developmentCheckBox = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By disableTextBox = By.xpath("//input[@id='password']");
	By disableRadio = By.xpath("//input[@id='radio-disabled']");
	By disableTextArea = By.xpath("//textarea[@id='bio']");
	By disableSelect = By.xpath("//select[@id='job2']");
	By disableCheckBox = By.xpath("//input[@id='check-disbaled']");
	By disableSlider = By.xpath("//input[@id='slider-2']");

	WebDriver driver;

	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void goToWebSite() {
		driver.get(WEBSITE_URL);
	}

	@AfterMethod
	public void sleepBrowser() {
		// Sleep browser 5s
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_01_CheckElementDisplay() {

		// Check some elements display
		boolean isInputDisplayed = driver.findElement(emailTextBox).isDisplayed();
		boolean isSelectDisplayed = driver.findElement(ageUnder18Radio).isDisplayed();
		boolean isTextAreaDisplayed = driver.findElement(educationTextArea).isDisplayed();

		// Do some process if some elements displayed
		if (isInputDisplayed && isSelectDisplayed && isTextAreaDisplayed) {
			driver.findElement(emailTextBox).sendKeys(VALUE_INPUT_TC01);
			driver.findElement(educationTextArea).sendKeys(VALUE_INPUT_TC01);
			driver.findElement(ageUnder18Radio).click();
		}
	}

	@Test
	public void TC_02_CheckElementEnableOrDisable() {

		// Check elements enable
		boolean isEmailEnable = driver.findElement(emailTextBox).isEnabled();
		boolean isAgeEnable = driver.findElement(ageUnder18Radio).isEnabled();
		boolean isEducationEnable = driver.findElement(educationTextArea).isEnabled();
		boolean isJobRoleEnable = driver.findElement(jobRoleSelect).isEnabled();
		boolean isInterestEnable = driver.findElement(developmentCheckBox).isEnabled();
		boolean isSliderEnable = driver.findElement(slider01).isEnabled();

		// Check elements disable
		boolean isPassWordDisable = driver.findElement(disableTextBox).isEnabled();
		boolean isAgeDisble = driver.findElement(disableRadio).isEnabled();
		boolean isEducationDisable = driver.findElement(disableTextArea).isEnabled();
		boolean isJobRoleDisable = driver.findElement(disableSelect).isEnabled();
		boolean isInterestDisable = driver.findElement(disableCheckBox).isEnabled();
		boolean isSliderDisable = driver.findElement(disableSlider).isEnabled();

		// Print if some elements enable
		if (isEmailEnable && isAgeEnable && isEducationEnable
				&& isJobRoleEnable && isInterestEnable && isSliderEnable) {
			System.out.println("Element is Enable");
		}

		// Print if some elements disable
		if (!(isPassWordDisable && isAgeDisble && isEducationDisable
				&& isJobRoleDisable && isInterestDisable && isSliderDisable)) {
			System.out.println("Element is Disable");
		}
	}

	@Test
	public void TC_03_CheckElementSelect() {

		// Click select elements
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckBox).click();

		// Check elements are selected
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckBox).isSelected());

		// Click not select elements
		driver.findElement(developmentCheckBox).click();

		// Check element not selected
		Assert.assertFalse(driver.findElement(developmentCheckBox).isSelected());
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
