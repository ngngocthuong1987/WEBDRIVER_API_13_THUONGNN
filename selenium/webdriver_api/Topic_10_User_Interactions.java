package webdriver_api;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interactions  {

	// Setting variables
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;

	String workingDir = System.getProperty("user.dir");
	String jsFilePath = workingDir + "\\libraries\\drag_and_drop_helper.js";
	String jQueryFilePath = workingDir + "\\libraries\\jquery_load_helper.js";

	String textClick = "Polo Ralph Lauren";
	String expectedTc04 = "Hello Automation Guys!";

	// Setting constant
	static final String WEBSITE_URL_TC_01 = "http://www.myntra.com/";
	static final String WEBSITE_URL_TC_02 = "https://jqueryui.com/resources/demos/selectable/display-grid.html";
	static final String WEBSITE_URL_TC_04 = "https://automationfc.github.io/basic-form/index.html";
	static final String WEBSITE_URL_TC_05 = "http://swisnl.github.io/jQuery-contextMenu/demo.html";
	static final String WEBSITE_URL_TC_06 = "https://demos.telerik.com/kendo-ui/dragdrop/angular";
	static final String WEBSITE_URL_TC_07 = "http://the-internet.herokuapp.com/drag_and_drop";

	/* Setting some By object of elements xpath */
	// Test case 01
	By discoverLink = By.xpath("//header//a[text()='Discover']");
	By brandLink = By.xpath("//a[text()='"+ textClick + "']");
	By breadCrumb = By.xpath("//span[text()='Home']//ancestor::li//following-sibling::li/span");
	By title = By.xpath("//span[text()='Home']//ancestor::div[@class=' row-base']//following-sibling::div/div/h1");

	// Test case 02 & 03
	By selectList = By.xpath("//ol[@id='selectable']/li");
	By selectedList = By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']");

	// Test case 04
	By buttonDoubleClick = By.xpath("//button[(text()='Double click me')]");
	By textResult = By.xpath("//p[@id='demo']");

	// Test case 05
	By rightClickMeButton = By.xpath("//span[text()='right click me']");
	By quitText = By.xpath("//span[text()='Quit']");
	By quitTextHover = By.xpath("//li[contains(@class, 'context-menu-visible') and contains(@class, 'context-menu-hover')]/span[text()='Quit']");

	// Test case 06
	By circleSource = By.xpath("//div[@id='draggable']");
	By circleTarget = By.xpath("//div[@id='droptarget']");

	// Test case 07
	String sourceCss = "#column-a";
	String targetCss = "#column-b";
	String sourceXpath = "//div[@id='column-a']";
	String targetXpath = "//div[@id='column-b']";


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

		action = new Actions(driver);

		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Move_Mouse_To_Element() {
		driver.get(WEBSITE_URL_TC_01);
		Utils.sleepBrowser();

		// Hover mouse
		action.moveToElement(driver.findElement(discoverLink)).perform();
		Utils.sleepBrowser();

		// Click link
		action.click(driver.findElement(brandLink)).perform();
		Utils.sleepBrowser();

		Assert.assertTrue(driver.findElement(breadCrumb).getText().contains(textClick));
		Assert.assertTrue(driver.findElement(title).getText().contains(textClick));
	}

	@Test
	public void TC_02_Click_And_Hold_Element() {
		driver.get(WEBSITE_URL_TC_02);
		Utils.sleepBrowser();

		// Click and hold 1 to 4
		List<WebElement> listItem = driver.findElements(selectList);
		action.clickAndHold(listItem.get(0)).moveToElement(listItem.get(3)).release().perform();
		Utils.sleepBrowser();

		List<WebElement> numberSelected = driver.findElements(selectedList);
		Assert.assertEquals(numberSelected.size(), 4);

	}

	@Test
	public void TC_03_Click_And_Select_Element() {
		driver.get(WEBSITE_URL_TC_02);
		Utils.sleepBrowser();

		// Click and hold 1, 3, 6, 12
		List<WebElement> listItem = driver.findElements(selectList);
		action.keyDown(Keys.CONTROL);
		action.click(listItem.get(0)).perform();
		action.click(listItem.get(2)).perform();
		action.click(listItem.get(5)).perform();
		action.click(listItem.get(11)).perform();
		Utils.sleepBrowser();

		List<WebElement> numberSelected = driver.findElements(selectedList);
		Assert.assertEquals(numberSelected.size(), 4);

	}

	@Test
	public void TC_04_Double_Click() {
		driver.get(WEBSITE_URL_TC_04);
		Utils.sleepBrowser();

		Utils.scrollToElementByJs(jsExecutor, driver.findElement(buttonDoubleClick));
		action.doubleClick(driver.findElement(buttonDoubleClick)).perform();
		Utils.sleepBrowser();

		Assert.assertEquals(Utils.getTextElement(driver.findElement(textResult)), expectedTc04);
	}

	@Test
	public void TC_05_Right_Click_To_Element() {
		driver.get(WEBSITE_URL_TC_05);
		Utils.sleepBrowser();

		action.contextClick(driver.findElement(rightClickMeButton)).perform();
		Utils.sleepBrowser();

		action.moveToElement(driver.findElement(quitText)).perform();
		Assert.assertTrue(driver.findElement(quitTextHover).isDisplayed());
		action.click(driver.findElement(quitTextHover)).perform();
		Utils.sleepBrowser();

		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test
	public void TC_06_Drag_And_Drop_Element() {
		driver.get(WEBSITE_URL_TC_06);
		Utils.sleepBrowser();

		action.dragAndDrop(driver.findElement(circleSource), driver.findElement(circleTarget)).perform();
		Utils.sleepBrowser();
		Assert.assertEquals(driver.findElement(circleTarget).getText(), "You did great!");

	}

	@Test
	public void TC_07_Drag_And_Drop_Element_HTML5_Js() throws IOException {
		driver.get(WEBSITE_URL_TC_07);

		String javaScript = Utils.readFile(jsFilePath);

		// Drag and drop A to B
		javaScript = javaScript + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(javaScript);
		Utils.sleepBrowser();

		// Drag and drop B to A
		jsExecutor.executeScript(javaScript);
		Utils.sleepBrowser();
	}

	@Test
	public void TC_07_Drag_And_Drop_Element_HTML5_JQuery() throws AWTException {
		driver.get(WEBSITE_URL_TC_07);

		// Drag and drop A to B
		Utils.drag_the_and_drop_html5_by_xpath(driver, sourceXpath, targetXpath);
		Utils.sleepBrowser();

		// Drag and drop B to A
		Utils.drag_the_and_drop_html5_by_xpath(driver, sourceXpath, targetXpath);
		Utils.sleepBrowser();
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}