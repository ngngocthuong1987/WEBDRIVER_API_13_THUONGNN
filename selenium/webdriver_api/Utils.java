package webdriver_api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
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
}
