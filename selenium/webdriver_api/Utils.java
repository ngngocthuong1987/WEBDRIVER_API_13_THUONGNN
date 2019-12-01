package webdriver_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

	/*
	 * The method create random email
	 */
	public static  String getRandomEmail() {
		String randomChar = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder emailString = new StringBuilder();
		Random rnd = new Random();
		while (emailString.length() < 10) {
			int index = rnd.nextInt(randomChar.length());
			emailString.append(randomChar.charAt(index));
		}
		return emailString.toString().concat("@gmail.com");
	}

	/*
	 * The method change format datetime from mm/dd/yyyy to yyyy-mm-dd
	 */
	public static String changeFormatDate(String date) {
		String newDateTime = null;
		try {
			SimpleDateFormat oldFormat = new SimpleDateFormat("mm/dd/yyyy");;
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-mm-dd");
			newDateTime =  newFormat.format(oldFormat.parse(date)).toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDateTime;
	}

	/*
	 * The method get text of web element
	 */
	public static String getTextElement(WebElement element) {
		return element.getText();
	}

	/*
	 * The method get text of web element
	 */
	public static void sendKeyElement(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	/*
	 * The method click Web Element by javascript
	 */
	public static void clickElementByJs(JavascriptExecutor jsExecutor, WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	/*
	 * The method scroll to Web Element by javascript
	 */
	public static void scrollToElementByJs(JavascriptExecutor jsExecutor, WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/*
	 * The method by pass authencation
	 */
	public static String byPassAuthencationAlert(String url, String userName, String password) {
		String [] splitUrl = url.split("//");
		return splitUrl[0].concat("//")
						.concat(userName)
						.concat(":")
						.concat(password)
						.concat("@")
						.concat(splitUrl[1]);
	}

	/*
	 * Wait browser in some seconds
	 */
	public static void sleepBrowser() {
		// Sleep browser 3s
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Read file from file path
	 */
	public static String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	/*
	 * Drag and drop
	 */
	public static void drag_the_and_drop_html5_by_xpath(WebDriver driver, String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/*
	 * Switch to another window by page tile
	 */
	public static void switchToWindowByTile(WebDriver driver, String title) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}
	}

	/*
	 * Close all window with out parrent window
	 */
	public static void closeAllWindowsWithoutParent(WebDriver driver, String parrentWindow) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			if (!runWindow.equals(parrentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
	}
}
