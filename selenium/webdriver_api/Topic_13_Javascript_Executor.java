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

public class Topic_13_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	//Setting URL
	static final String WEBSITE_URL_TC_01 = "http://live.demoguru99.com/";

	//Setting some variables


	/*Setting some By object of elements xpath */
	//Test case 01
	By mobileLink = By.xpath("//a[text()='Mobile']");
	By buttonAddToCart = By.xpath("//h2/a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
	By successMessage = By.xpath("//li[@class='success-msg']//span");
	By customerServiceLink = By.xpath("//a[text()='Customer Service']");
	By inputNewsLetter = By.xpath("//input[@id='newsletter']");
	By lastText = By.xpath("//dd[last()]");

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

		//Get domain
		String domain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(domain, "live.demoguru99.com");

		//Get Url
		String url = (String) jsExecutor.executeScript("return document.URL");
		Assert.assertEquals(url, "http://live.demoguru99.com/");

		//Open MOBILE page
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(mobileLink));

		//Click button add to cart
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(buttonAddToCart));

		//Get success message
		String message = (String) jsExecutor.executeScript("return arguments[0].innerHTML", driver.findElement(successMessage));
		Assert.assertEquals(message, "Samsung Galaxy was added to your shopping cart.");

		//Open customer service page
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(customerServiceLink));
		String title = (String) jsExecutor.executeScript("return document.title");
		Assert.assertEquals(title, "Customer Service");

		//Scroll to newsletter input
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(inputNewsLetter));

		//Get text
		String text = (String) jsExecutor.executeScript("return arguments[0].innerHTML", driver.findElement(lastText));
//		Assert.assertTrue(text.contentEquals("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
