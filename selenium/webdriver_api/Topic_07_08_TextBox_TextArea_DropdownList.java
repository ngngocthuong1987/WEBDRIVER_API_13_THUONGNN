package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic_07_08_TextBox_TextArea_DropdownList {

	// Setting constant
	static final String WEBSITE_URL_TC01 = "http://demo.guru99.com/v4";
	static final String WEBSITE_URL_TC02 = "https://automationfc.github.io/basic-form/index.html";
	static final String WEBSITE_URL_JQUERY_TC03 = "http://jqueryui.com/resources/demos/selectmenu/default.html";
	static final String WEBSITE_URL_ANGULAR_TC03 = "https://material.angular.io/components/select/examples";
	static final String WEBSITE_URL_REACTJS_TC04 = "https://react.semantic-ui.com/modules/dropdown/";
	static final String WEBSITE_URL_VUEJS_TC05 = "https://mikerodham.github.io/vue-dropdowns/";
	static final String WEBSITE_URL_EDITABLE_TC06 = "http://indrimuska.github.io/jquery-editable-select/";
	static final String WEBSITE_URL_MULTI_SELECT_TC07 = "http://multiple-select.wenzhixin.net.cn/examples#basic.html";
	static final String HOME_PAGE_TITLE = "Guru99 Bank Manager HomePage";

	// Setting some By object of elements xpath
	By userIdInput = By.xpath("//input[@name='uid']");
	By passWordInput = By.xpath("//input[@name='password']");
	By loginButton = By.xpath("//input[@name='btnLogin']");
	By newCustomerLink = By.xpath("//a[@href='addcustomerpage.php']");
	By editCustomerLink = By.xpath("//a[@href='EditCustomer.php']");
	By customerNameInput = By.xpath("//input[@name='name']");
	By maleRadioButton = By.xpath("//input[@name='rad1' and @value='m']");
	By birthDayInput = By.xpath("//input[@id='dob']");
	By addressTextArea = By.xpath("//textarea[@name='addr']");
	By cityInput = By.xpath("//input[@name='city']");
	By stateInput = By.xpath("//input[@name='state']");
	By pinInput = By.xpath("//input[@name='pinno']");
	By mobileNumberInput = By.xpath("//input[@name='telephoneno']");
	By emailInput = By.xpath("//input[@name='emailid']");
	By createCustomerSubmitButton = By.xpath("//input[@name='sub']");
	By editCustomerSubmitButton = By.xpath("//input[@name='AccSubmit']");
	By customerIdInput = By.xpath("//input[@name='cusid']");
	By jobRole01Select = By.xpath("//select[@id='job1']");

	// Setting some By object for verify value
	By customerIdVerify = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By customerNameVerify = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By genderVerify = By.xpath("//td[text()='Gender']/following-sibling::td");
	By birthDayVerify = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By addressVerify = By.xpath("//td[text()='Address']/following-sibling::td");
	By cityVerify = By.xpath("//td[text()='City']/following-sibling::td");
	By stateVerify = By.xpath("//td[text()='State']/following-sibling::td");
	By pinVerify = By.xpath("//td[text()='Pin']/following-sibling::td");
	By mobileNumberVerify = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By emailVerify = By.xpath("//td[text()='Email']/following-sibling::td");

	// JQuery dropdown test case
	By jQueryDropdown = By.xpath("//span[@id='number-button']");
	By allItemsJQueryDropdown = By.xpath("//ul[@id='number-menu']/li/div");
	By valueItemJQueryDropdownExpected = By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']");

	// Angular dropdown test case
	By agularDropdown = By.xpath("//label/mat-label[text()='State']");
	By allItemsAgularDropdown = By.xpath("//div[contains(@class, 'mat-primary')]/mat-option");
	By valueItemAgularDropdownExpected = By.xpath("//div[contains(@class, 'mat-select-value')]//span[text()=\"New York\"]");

	// ReactJs dropdown test case
	By reactJsDropdown = By.xpath("//div[text()='Select Friend']/following-sibling::div");
	By allItemsReactJsDropdown = By.xpath("//div[@id='types-selection']/descendant::span");
	By valueItemReactJsDropdownExpected = By.xpath("//div[@id='types-selection']/descendant::div[@class='text']");

	// VueJs dropdown test case
	By vueJsDropdown = By.xpath("//li[@class='dropdown-toggle']");
	By allItemsVueJsDropdown = By.xpath("//ul[@class='dropdown-menu']/li");
	By valueItemVueJsDropdownExpected = By.xpath("//li[@class='dropdown-toggle']");

	// Editable dropdown test case
	By editableDropdown = By.xpath("//div[@id='default-place']/input");
	By allItemsEditableDropdown = By.xpath("//div[@id='default-place']/descendant::li");
	By valueItemEditableDropdownExpected = By.xpath("//div[@id='default-place']/descendant::li[contains(@class,'es-visible')]");

	// Multi select test case
	By iframe = By.xpath("//iframe");
	By multiSelectDropdown = By.xpath("//div[@id='example']/descendant::div[contains(@class, 'multiple-select')][1]/button[@class='ms-choice']");
	By allItemsMultiSelectDropdown = By.xpath("//div[@id='example']/descendant::div[contains(@class, 'multiple-select')][1]/div[contains(@class, 'ms-drop')]/descendant::span");
	By valueItemMultiSelectDropdownExpected = By.xpath("//div[@id='example']/descendant::div[contains(@class, 'multiple-select')][1]/button[@class='ms-choice']/span");
	By inputSelect = By.xpath("//div[@id='example']/descendant::div[contains(@class, 'multiple-select')][1]/div[contains(@class, 'ms-drop')]/descendant::input");

	// Setting varialbes
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor jsExecutor;
	String userName = "mngr232324";
	String passWord = "ErugEju";

	// Data for create new customer
	String customerName = "Nguyen Ngoc Thuong";
	String dateOfBirth = "02/16/1987";
	String addressNew = "K131 Ly Thai To";
	String cityNew = "Da Nang";
	String stateNew = "Thanh Khe";
	String pinNew = "123456";
	String mobileNumberNew = "0979161024";
	String emailNew = Utils.getRandomEmail();
	String passwordNew = "123456";

	// Data for edit customer
	String addressEdit = "245 Le Duan";
	String cityEdit= "Ho Chi Minh";
	String stateEdit = "Tan Binh";
	String pinEdit = "457832";
	String mobileNumberEdit = "0936123123";
	String emailEdit = Utils.getRandomEmail();


	@BeforeClass
	public void launchBrowser() {

		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void goToWebSite() {

	}

	@AfterMethod
	public void sleepBrowser() {
		// Sleep browser 3s
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_01_ProcessWithTextBoxAndTextArea() {

		// Go to page and login
		driver.get(WEBSITE_URL_TC01);
		driver.findElement(userIdInput).sendKeys(userName);
		driver.findElement(passWordInput).sendKeys(passWord);
		driver.findElement(loginButton).click();

		// Verify Homepage display
		Assert.assertTrue(driver.getTitle().equals(HOME_PAGE_TITLE));

		// Go to new customer page, create new customer and submit
		driver.findElement(newCustomerLink).click();
		driver.findElement(customerNameInput).sendKeys(customerName);
		driver.findElement(maleRadioButton).click();
		driver.findElement(birthDayInput).sendKeys(dateOfBirth);
		driver.findElement(addressTextArea).sendKeys(addressNew);
		driver.findElement(cityInput).sendKeys(cityNew);
		driver.findElement(stateInput).sendKeys(stateNew);
		driver.findElement(pinInput).sendKeys(pinNew);
		driver.findElement(mobileNumberInput).sendKeys(mobileNumberNew);
		driver.findElement(emailInput).sendKeys(emailNew);
		driver.findElement(passWordInput).sendKeys(passwordNew);
		driver.findElement(createCustomerSubmitButton).click();

		// Verify new informations are created
		String customerId = Utils.getTextElement(driver, customerIdVerify);
		Assert.assertTrue(Utils.getTextElement(driver, customerNameVerify).equals(customerName));
		Assert.assertTrue(Utils.getTextElement(driver, birthDayVerify).equals(Utils.changeFormatDate(dateOfBirth)));
		Assert.assertTrue(Utils.getTextElement(driver, addressVerify).equals(addressNew));
		Assert.assertTrue(Utils.getTextElement(driver, cityVerify).equals(cityNew));
		Assert.assertTrue(Utils.getTextElement(driver, stateVerify).equals(stateNew));
		Assert.assertTrue(Utils.getTextElement(driver, pinVerify).equals(pinNew));
		Assert.assertTrue(Utils.getTextElement(driver, mobileNumberVerify).equals(mobileNumberNew));
		Assert.assertTrue(Utils.getTextElement(driver, emailVerify).equals(emailNew));

		// Go to edit customer
		driver.findElement(editCustomerLink).click();
		driver.findElement(customerIdInput).sendKeys(customerId);
		driver.findElement(editCustomerSubmitButton).click();

		// Verify customer name and address
		Assert.assertTrue(driver.findElement(customerNameInput).getAttribute("value").equals(customerName));
		Assert.assertTrue(driver.findElement(addressTextArea).getAttribute("value").equals(addressNew));

		// Edit information of some fields not disable
		Utils.sendKeyElement(driver, addressTextArea, addressEdit);
		Utils.sendKeyElement(driver, cityInput, cityEdit);
		Utils.sendKeyElement(driver, stateInput, stateEdit);
		Utils.sendKeyElement(driver, pinInput, pinEdit);
		Utils.sendKeyElement(driver, mobileNumberInput, mobileNumberEdit);
		Utils.sendKeyElement(driver, emailInput, emailEdit);
		driver.findElement(createCustomerSubmitButton).click();

		// Verify new informations after edit
		Assert.assertTrue(Utils.getTextElement(driver, addressVerify).equals(addressEdit));
		Assert.assertTrue(Utils.getTextElement(driver, cityVerify).equals(cityEdit));
		Assert.assertTrue(Utils.getTextElement(driver, stateVerify).equals(stateEdit));
		Assert.assertTrue(Utils.getTextElement(driver, pinVerify).equals(pinEdit));
		Assert.assertTrue(Utils.getTextElement(driver, mobileNumberVerify).equals(mobileNumberEdit));
		Assert.assertTrue(Utils.getTextElement(driver, emailVerify).equals(emailEdit));
	}

	@Test
	public void TC_02_ProcessWithDropdownAndList() {
		driver.get(WEBSITE_URL_TC02);
		Select jobRole01 = new Select(driver.findElement(jobRole01Select));

		// Check dropdown is multi select
		Assert.assertFalse(jobRole01.isMultiple());

		// Select by method selectByVisibleText() and check
		jobRole01.selectByVisibleText("Automation Tester");
		Assert.assertEquals(jobRole01.getFirstSelectedOption().getText(), "Automation Tester");

		// Select by method selectByValue() and check
		jobRole01.selectByValue("manual");
		Assert.assertEquals(jobRole01.getFirstSelectedOption().getText(), "Manual Tester");

		// Select by method selectByIndex() and check
		jobRole01.selectByIndex(3);
		Assert.assertEquals(jobRole01.getFirstSelectedOption().getText(), "Mobile Tester");

		// Check size of dropdown is 5
		Assert.assertEquals(5, jobRole01.getOptions().size());
	}

	@Test
	public void TC_03_JQueryDropdown() {
		try {
			driver.get(WEBSITE_URL_JQUERY_TC03);
			selectItemInDropdown(jQueryDropdown, allItemsJQueryDropdown, "19");
			Assert.assertEquals(Utils.getTextElement(driver, valueItemJQueryDropdownExpected), "19");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_04_AngularDropdown() {
		try {
			driver.get(WEBSITE_URL_ANGULAR_TC03);
			selectItemInDropdown(agularDropdown, allItemsAgularDropdown, "New York");
			Assert.assertEquals(Utils.getTextElement(driver, valueItemAgularDropdownExpected), "New York");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_05_ReactJsDropdown() {
		try {
			driver.get(WEBSITE_URL_REACTJS_TC04);
			selectItemInDropdown(reactJsDropdown, allItemsReactJsDropdown, "Matt");
			Assert.assertEquals(Utils.getTextElement(driver, valueItemReactJsDropdownExpected), "Matt");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_06_VueJsDropdown() {
		try {
			driver.get(WEBSITE_URL_VUEJS_TC05);
			selectItemInDropdown(vueJsDropdown, allItemsVueJsDropdown, "Second Option");
			Assert.assertEquals(Utils.getTextElement(driver, valueItemVueJsDropdownExpected), "Second Option");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_07_EditableDropdown() {
		try {
			driver.get(WEBSITE_URL_EDITABLE_TC06);
			selectItemInEditableDropdown(editableDropdown, allItemsEditableDropdown, "Audi");
			Assert.assertEquals(Utils.getTextElement(driver, valueItemEditableDropdownExpected), "Audi");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_08_MultiSelectItem() {
		try {
			driver.get(WEBSITE_URL_MULTI_SELECT_TC07);
			driver.switchTo().frame(driver.findElement(iframe));

			// Select 5 items
			selectItemInDropdown(multiSelectDropdown, allItemsMultiSelectDropdown, "May");
			driver.findElement(multiSelectDropdown).click();
			selectItemInDropdown(multiSelectDropdown, allItemsMultiSelectDropdown, "April");
			driver.findElement(multiSelectDropdown).click();
			selectItemInDropdown(multiSelectDropdown, allItemsMultiSelectDropdown, "February");
			driver.findElement(multiSelectDropdown).click();
			selectItemInDropdown(multiSelectDropdown, allItemsMultiSelectDropdown, "August");
			driver.findElement(multiSelectDropdown).click();
			selectItemInDropdown(multiSelectDropdown, allItemsMultiSelectDropdown, "January");
			driver.findElement(multiSelectDropdown).click();

			// Validate item selected
			System.out.println("Item selected: " + Utils.getTextElement(driver, valueItemMultiSelectDropdownExpected));
			itemsSelected(inputSelect, valueItemMultiSelectDropdownExpected);

			// Refresh browser
			driver.navigate().refresh();
			Thread.sleep(3000);
			driver.switchTo().frame(driver.findElement(iframe));

			// Select 3 items
			selectItemInDropdown(multiSelectDropdown, allItemsMultiSelectDropdown, "March");
			driver.findElement(multiSelectDropdown).click();
			selectItemInDropdown(multiSelectDropdown, allItemsMultiSelectDropdown, "November");
			driver.findElement(multiSelectDropdown).click();
			selectItemInDropdown(multiSelectDropdown, allItemsMultiSelectDropdown, "July");
			driver.findElement(multiSelectDropdown).click();

			// Validate item selected
			System.out.println("Item selected: " + Utils.getTextElement(driver, valueItemMultiSelectDropdownExpected));
			itemsSelected(inputSelect, valueItemMultiSelectDropdownExpected);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * The method get expected item in dropdown
	 */
	public void selectItemInDropdown(By dropdownBy, By allItemInDropdownBy, String expectedItemValue) throws InterruptedException {
		// Click in dropdown for display all items
		WebElement paretnDropdown = driver.findElement(dropdownBy);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", paretnDropdown);
		Thread.sleep(2000);
		jsExecutor.executeScript("arguments[0].click();", paretnDropdown);
		Thread.sleep(2000);

		// Wait all items loaded
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemInDropdownBy));

		// Loop and get expected item
		List<WebElement> allItems = driver.findElements(allItemInDropdownBy);
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItemValue)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				Thread.sleep(1000);
				item.click();
				break;
			}
		}
	}

	/*
	 * The method get expected item in editable dropdown
	 */
	public void selectItemInEditableDropdown(By dropdownBy, By allItemInDropdownBy, String expectedItemValue) throws InterruptedException {
		// Click in dropdown for display all items
		WebElement paretnDropdown = driver.findElement(dropdownBy);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", paretnDropdown);
		Thread.sleep(2000);
		jsExecutor.executeScript("arguments[0].click();", paretnDropdown);
		Thread.sleep(2000);
		paretnDropdown.sendKeys(expectedItemValue);
		Thread.sleep(2000);

		// Wait all items loaded
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemInDropdownBy));

		// Loop and get expected item
		List<WebElement> allItems = driver.findElements(allItemInDropdownBy);
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItemValue)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				Thread.sleep(1000);
				item.click();
				break;
			}
		}
	}

	/*
	 * The method check items are selected
	 */
	public boolean itemsSelected (By allSelect, By valueExpect) {
		// Get all select filter the selected
		List<WebElement> selectionList = driver.findElements(allSelect);
		List<WebElement> itemSelected = selectionList.stream().filter(s -> s.isSelected()).collect((Collectors.toList()));

		if (itemSelected.size() > 3) {
			return driver.findElement(valueExpect).getText().equals(itemSelected.size() + " of 12 selected");
		} else {
			for (WebElement item : itemSelected) {
				if (!driver.findElement(valueExpect).getText().equals(item.getText())) {
					return false;
				}
			}
		}
		return true;
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
