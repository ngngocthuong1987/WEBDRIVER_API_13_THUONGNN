package webdriver_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
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

public class Topic_14_Upload_File {

	WebDriver driver;

	String projectPath = System.getProperty("user.dir");

	String fileImage01 = "1.jpeg";
	String fileImage02 = "2.jpeg";
	String fileImage03 = "3.jpg";

	String filePath01 = projectPath + "\\fileUpload\\" + fileImage01;
	String filePath02 = projectPath + "\\fileUpload\\" + fileImage02;
	String filePath03 = projectPath + "\\fileUpload\\" + fileImage03;

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
	public void TC_01_Upload_By_Sendkeys() throws InterruptedException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		WebElement inputFile = driver.findElement(By.xpath("//input[@type='file']"));

		//Upload files
		inputFile.sendKeys(filePath01 + "\n" + filePath02 + "\n" + filePath03);

		Thread.sleep(2000);

		//Verify 3 images display
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ fileImage01 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ fileImage02 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ fileImage03 +"']")).isDisplayed());

		//Click buttons upload
		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement button: startButtons) {
			button.click();
			Thread.sleep(2000);
		}

		//Verify 3 images upload
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ fileImage01 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ fileImage02 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ fileImage03 +"']")).isDisplayed());
	}

	@Test
	public void TC_02_Upload_By_Auto_IT() throws InterruptedException, IOException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		WebElement addFileButton = driver.findElement(By.xpath("//span[contains(@class, 'fileinput-button')]"));
		addFileButton.click();

		String multiFiles = filePath01 + "," + filePath02 + "," + filePath03;
		Runtime.getRuntime().exec(new String[] {"libraries/chromeAutoIT.exe", multiFiles});

		Thread.sleep(5000);

		//Verify 3 images display
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ fileImage01 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ fileImage02 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ fileImage03 +"']")).isDisplayed());

		//Click buttons upload
		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement button: startButtons) {
			button.click();
			Thread.sleep(2000);
		}

		//Verify 3 images upload
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ fileImage01 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ fileImage02 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ fileImage03 +"']")).isDisplayed());
	}

	@Test
	public void TC_03_Upload_By_Robot_Class() throws AWTException, InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement addFileButton = driver.findElement(By.xpath("//span[contains(@class, 'fileinput-button')]"));
		addFileButton.click();

		StringSelection select = new StringSelection(filePath01);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		Robot robot = new Robot();
		Thread.sleep(2000);

		//Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		//Press Ctrl + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		//Release Ctrl + V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);

		//Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(2000);

		//Verify image display
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ fileImage01 +"']")).isDisplayed());

		//Click buttons upload
		WebElement uploadButton = driver.findElement(By.xpath("//button[@class='btn btn-primary start']"));
		uploadButton.click();
		Thread.sleep(2000);

		//Verify image upload
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ fileImage01 +"']")).isDisplayed());
	}

	@AfterClass
	public void terminateBrowser() {
		driver.quit();
	}
}
