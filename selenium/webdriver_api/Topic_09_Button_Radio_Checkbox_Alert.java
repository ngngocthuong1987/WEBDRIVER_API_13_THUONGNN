package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_09_Button_Radio_Checkbox_Alert {

	// Setting constant
	static final String WEBSITE_URL_TC_01 = "http://live.guru99.com/";
	static final String WEBSITE_URL_TC_02 = "http://demos.telerik.com/kendo-ui/styling/checkboxes";
	static final String WEBSITE_URL_TC_03 = "https://demos.telerik.com/kendo-ui/styling/radios";
	static final String HOME_PAGE_TITLE = "SELENIUM WEBDRIVER FORM DEMO";
	static final String VALUE_INPUT_TC01 = "Automation Testing";

	// Setting some By object of elements xpath
	By myAccountLink = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By createAccountButton = By.xpath("//a[@title='Create an Account']");
	By inputCheckBox = By.xpath("//label[contains(text(), 'Dual-zone')]/preceding-sibling::input");
	By inputRadioButton = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");

	String urlExpected01 = "http://live.demoguru99.com/index.php/customer/account/login/";
	String urlExpected02 = "http://live.demoguru99.com/index.php/customer/account/create/";

	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get(WEBSITE_URL_TC_01);
		clickElementByJs(driver.findElement(myAccountLink));
		sleepBrowser();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(urlExpected01, url);

		clickElementByJs(driver.findElement(createAccountButton));
		sleepBrowser();
		Assert.assertEquals(urlExpected02, driver.getCurrentUrl());
	}

	@Test
	public void TC_02_CheckBox() throws Exception {
		driver.get(WEBSITE_URL_TC_02);

		// Check checkbox
		clickElementByJs(driver.findElement(inputCheckBox));
		sleepBrowser();
		Assert.assertTrue(driver.findElement(inputCheckBox).isSelected());

		// Uncheck checkbox
		clickElementByJs(driver.findElement(inputCheckBox));
		sleepBrowser();
		Assert.assertFalse(driver.findElement(inputCheckBox).isSelected());
	}

	@Test
	public void TC_03_RadioButton() throws Exception {
		driver.get(WEBSITE_URL_TC_03);

		// Select radio button if not selected
		boolean isSelected = driver.findElement(inputRadioButton).isSelected();
		if (!isSelected) {
			clickElementByJs(driver.findElement(inputRadioButton));
			sleepBrowser();
			Assert.assertTrue(driver.findElement(inputRadioButton).isSelected());
		}
	}

	/*
	 * The method click Web Element by javascript
	 */
	public void clickElementByJs(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	/*
	 * Wait browser in some seconds
	 */
	public void sleepBrowser() {
		// Sleep browser 2s
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
