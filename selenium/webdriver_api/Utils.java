package webdriver_api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
	public static String getTextElement(WebDriver driver, By by) {
		return driver.findElement(by).getText();
	}

	/*
	 * The method get text of web element
	 */
	public static void sendKeyElement(WebDriver driver, By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}

}
