package webdriver_api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Window_Tab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	//Setting URL
	static final String WEBSITE_URL_TC_01 = "https://automationfc.github.io/basic-form/index.html";
	static final String WEBSITE_URL_TC_02 = "https://www.hdfcbank.com/";
	static final String WEBSITE_URL_TC_03 = "http://live.demoguru99.com/";

	//Setting some variables
	String parrentWindowTitle = "SELENIUM WEBDRIVER FORM DEMO";
	String googleTitle = "Google";
	String facebookTitle = "Facebook - Đăng nhập hoặc đăng ký";
	String tikiTitle = "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn";
	String comparePageTitle = "Products Comparison List - Magento Commerce";

	/*Setting some By object of elements xpath */
	//Test case 01
	By googleLink = By.xpath("//a[text()='GOOGLE']");
	By facebookLink = By.xpath("//a[text()='FACEBOOK']");
	By tikiLink = By.xpath("//a[text()='TIKI']");

	//Test case 02
	By popup = By.xpath("//img[contains(@class, 'popupbanner')]");
	By buttonClosePopup = By.xpath("//img[@class='popupCloseButton']");

	//Test case 03
	By mobileLink = By.xpath("//a[text()='Mobile']");
	By sonyXperiaCompareLink = By.xpath("//h2/a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']");
	By samsungGalaxyCompareLink = By.xpath("//h2/a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']");
	By sonyXperiaAddedText = By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']");
	By samsungGalaxyAddedText = By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']");
	By compareButton = By.xpath("//button[@title='Compare']");

	@BeforeClass
	public void launchBrowser() {
		// Setting disable notification in Chrome
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);

		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver(options);
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Windows_Tab() {
		driver.get(WEBSITE_URL_TC_01);
		Utils.scrollToElementByJs(jsExecutor, driver.findElement(googleLink));
		String parrentWindow = driver.getWindowHandle();
		Utils.sleepBrowser();

		//Process with google page
		driver.findElement(googleLink).click();
		Utils.switchToWindowByTile(driver, googleTitle);
		Assert.assertEquals(driver.getTitle(), googleTitle);

		Utils.switchToWindowByTile(driver, parrentWindowTitle);

		//Process with facebook page
		driver.findElement(facebookLink).click();
		Utils.switchToWindowByTile(driver, facebookTitle);
		Assert.assertEquals(driver.getTitle(), facebookTitle);

		Utils.switchToWindowByTile(driver, parrentWindowTitle);

		//Process with tiki page
		driver.findElement(tikiLink).click();
		Utils.switchToWindowByTile(driver, tikiTitle);
		Assert.assertEquals(driver.getTitle(), tikiTitle);

		Utils.closeAllWindowsWithoutParent(driver, parrentWindow);
		Utils.switchToWindowByTile(driver, parrentWindowTitle);
		Assert.assertEquals(driver.getTitle(), parrentWindowTitle);
	}

	@Test
	public void TC_02_Windows_Tab_Frame() {
		driver.get(WEBSITE_URL_TC_02);
		if(driver.findElement(popup).isDisplayed()) {
			driver.findElement(buttonClosePopup).click();
		}
		//Website has change, wait update test case
	}

	@Test
	public void TC_03_Windows() {
		driver.get(WEBSITE_URL_TC_03);
		driver.findElement(mobileLink).click();
		String parrentWindow = driver.getWindowHandle();
		String parrentTitle = driver.getTitle();

		//Add Sony Xperia and verify text
		driver.findElement(sonyXperiaCompareLink).click();
		Assert.assertTrue(driver.findElement(sonyXperiaAddedText).isDisplayed());

		//Add Samsung Galaxy and verify text
		driver.findElement(samsungGalaxyCompareLink).click();
		Assert.assertTrue(driver.findElement(samsungGalaxyAddedText).isDisplayed());

		driver.findElement(compareButton).click();

		Utils.switchToWindowByTile(driver, comparePageTitle);
		Assert.assertEquals(driver.getTitle(), comparePageTitle);

		Utils.closeAllWindowsWithoutParent(driver, parrentWindow);
		Utils.switchToWindowByTile(driver, parrentTitle);
		Assert.assertEquals(driver.getTitle(), parrentTitle);
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
