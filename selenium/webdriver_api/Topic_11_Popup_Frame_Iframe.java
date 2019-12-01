package webdriver_api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_Popup_Frame_Iframe {

	WebDriver driver;

	//Setting URL
	static final String WEBSITE_URL_TC_01 = "https://www.hdfcbank.com/";
	static final String WEBSITE_URL_TC_02 = "https://netbanking.hdfcbank.com/netbanking";

	/*Setting some By object of elements xpath */
	//Test case 01
	By popup = By.xpath("//img[contains(@class, 'popupbanner')]");
	By buttonClosePopup = By.xpath("//img[@class='popupCloseButton']");
	By imageSlider = By.xpath("//div[contains(@class, 'be-ex-hero-carousel slick-slide') and @role='option']//descendant::img");

	//Test case 02
	By loginFrame = By.xpath("//frame[@name='login_page']");
	By inputId = By.xpath("//input[@name='fldLoginUserId']");
	By inputPassword = By.xpath("//input[@name='fldPassword']");
	By loginContinueButton = By.xpath("//table[@class='label']//img[@alt='continue']");
	By footerFrame = By.xpath("//frame[@name='footer']");
	By textPrivacyPolicy = By.xpath("//a[text()='Privacy Policy']");

	String userId = "selenium_online";

	@BeforeClass
	public void launchBrowser() {
		// Setting disable notification in Chrome
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);

		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Popup() {
		driver.get(WEBSITE_URL_TC_01);

		//Check popup display and close
		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(buttonClosePopup).click();
			Assert.assertFalse(driver.findElement(popup).isDisplayed());
		}

		//Check the number image in slider is 6
		List<WebElement> imageSliderLst = driver.findElements(imageSlider);
		Assert.assertEquals(imageSliderLst.size(), 6);
	}

	@Test
	public void TC_02_Frame() {
		driver.get(WEBSITE_URL_TC_02);

		//Switch to login frame and process
		driver.switchTo().frame(driver.findElement(loginFrame));
		driver.findElement(inputId).sendKeys(userId);
		driver.findElement(loginContinueButton).click();
		Assert.assertTrue(driver.findElement(inputPassword).isDisplayed());

		//Switch to top page
		driver.switchTo().defaultContent();

		//Switch to login frame and process
		driver.switchTo().frame(driver.findElement(footerFrame));
		Assert.assertTrue(driver.findElement(textPrivacyPolicy).isDisplayed());
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
