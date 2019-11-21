package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
	static final String WEBSITE_URL_TC_04 = "https://automationfc.github.io/basic-form/index.html";
	static final String WEBSITE_URL_TC_07 = "http://the-internet.herokuapp.com";
	static final String HOME_PAGE_TITLE = "SELENIUM WEBDRIVER FORM DEMO";
	static final String VALUE_INPUT_TC01 = "Automation Testing";
	static final String ALERT_JS_MESSAGE = "I am a JS Alert";
	static final String RESULT_ALERT_JS_MESSAGE = "You clicked an alert successfully";
	static final String CONFIRM_JS_MESSAGE = "I am a JS Confirm";
	static final String CANCEL_CONFIRM_JS_MESSAGE = "You clicked: Cancel";
	static final String PROMPT_JS_MESSAGE = "I am a JS prompt";
	static final String AUTHEN_SUCCESS_MESSAGE = "Congratulations! You must have the proper credentials.";

	// Setting some By object of elements xpath
	By myAccountLink = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By createAccountButton = By.xpath("//a[@title='Create an Account']");
	By inputCheckBox = By.xpath("//label[contains(text(), 'Dual-zone')]/preceding-sibling::input");
	By inputRadioButton = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
	By buttonJsAlert = By.xpath("//button[(text()='Click for JS Alert')]");
	By buttonJsConfirmAlert = By.xpath("//button[text()='Click for JS Confirm']");
	By buttonJsPromptmAlert = By.xpath("//button[text()='Click for JS Prompt']");
	By resultText = By.xpath("//p[@id='result']");
	By basicAuthenLink = By.xpath("//a[@href='/basic_auth']");
	By authenText = By.xpath("//div[@id='content']/descendant::p");

	String urlExpected01 = "http://live.demoguru99.com/index.php/customer/account/login/";
	String urlExpected02 = "http://live.demoguru99.com/index.php/customer/account/create/";
	String inputPromptAlert = "Nguyen Ngoc Thuong";
	String userName = "admin";
	String password = "admin";

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
		Utils.clickElementByJs(jsExecutor, driver.findElement(myAccountLink));
		sleepBrowser();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(urlExpected01, url);

		Utils.clickElementByJs(jsExecutor, driver.findElement(createAccountButton));
		sleepBrowser();
		Assert.assertEquals(urlExpected02, driver.getCurrentUrl());
	}

	@Test
	public void TC_02_CheckBox() throws Exception {
		driver.get(WEBSITE_URL_TC_02);

		// Check checkbox
		Utils.clickElementByJs(jsExecutor, driver.findElement(inputCheckBox));
		sleepBrowser();
		Assert.assertTrue(driver.findElement(inputCheckBox).isSelected());

		// Uncheck checkbox
		Utils.clickElementByJs(jsExecutor, driver.findElement(inputCheckBox));
		sleepBrowser();
		Assert.assertFalse(driver.findElement(inputCheckBox).isSelected());
	}

	@Test
	public void TC_03_RadioButton() throws Exception {
		driver.get(WEBSITE_URL_TC_03);

		// Select radio button if not selected
		boolean isSelected = driver.findElement(inputRadioButton).isSelected();
		if (!isSelected) {
			Utils.clickElementByJs(jsExecutor, driver.findElement(inputRadioButton));
			sleepBrowser();
			Assert.assertTrue(driver.findElement(inputRadioButton).isSelected());
		}
	}

	@Test
	public void TC_04_AcceptAlert() throws Exception {
		driver.get(WEBSITE_URL_TC_04);
		Utils.scrollToElementByJs(jsExecutor, driver.findElement(buttonJsAlert));
		sleepBrowser();

		// Click button Js Alert
		Utils.clickElementByJs(jsExecutor, driver.findElement(buttonJsAlert));
		sleepBrowser();

		// Switch to Alert
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), ALERT_JS_MESSAGE);
		alert.accept();
		Assert.assertEquals(Utils.getTextElement(driver.findElement(resultText)), RESULT_ALERT_JS_MESSAGE);
	}

	@Test
	public void TC_05_ConfirmAlert() throws Exception {
		driver.get(WEBSITE_URL_TC_04);
		Utils.scrollToElementByJs(jsExecutor, driver.findElement(buttonJsConfirmAlert));
		sleepBrowser();

		// Click button Js Alert
		Utils.clickElementByJs(jsExecutor, driver.findElement(buttonJsConfirmAlert));
		sleepBrowser();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), CONFIRM_JS_MESSAGE);
		alert.dismiss();
		Assert.assertEquals(Utils.getTextElement(driver.findElement(resultText)), CANCEL_CONFIRM_JS_MESSAGE);
	}

	@Test
	public void TC_06_PromptAlert() throws Exception {
		driver.get(WEBSITE_URL_TC_04);
		Utils.scrollToElementByJs(jsExecutor, driver.findElement(buttonJsPromptmAlert));
		sleepBrowser();

		// Click button Alert
		Utils.clickElementByJs(jsExecutor, driver.findElement(buttonJsPromptmAlert));
		sleepBrowser();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), PROMPT_JS_MESSAGE);

		// Click button Alert
		alert.sendKeys(inputPromptAlert);
		sleepBrowser();
		alert.accept();
		Assert.assertEquals(Utils.getTextElement(driver.findElement(resultText)), "You entered: ".concat(inputPromptAlert));
	}

	@Test
	public void TC_07_AuthenticationAlert() throws Exception {
		driver.get(WEBSITE_URL_TC_07);
		String url = driver.findElement(basicAuthenLink).getAttribute("href");
		driver.get(Utils.byPassAuthencationAlert(url, userName, password));
		Assert.assertEquals(Utils.getTextElement(driver.findElement(authenText)), AUTHEN_SUCCESS_MESSAGE);
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
