package webdriver_api;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Topic_00_Template {
	WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logReport;

	@BeforeClass
	public void beforeClass() {
		String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());;
		htmlReporter  = new ExtentHtmlReporter(".\\report\\ReportTestCase01_".concat(date).concat(".html"));
		extent = new ExtentReports();  //create object of ExtentReports
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setCSS(".r-img {width: 50%;}");
		htmlReporter.config().setAutoCreateRelativePathMedia(true);

		extent.setSystemInfo("Application Name", "Test Report");
		extent.setSystemInfo("User Name", "Thuong Nguyen Ngoc");
		extent.setSystemInfo("Envirnoment", "Production");

		System.setProperty("webdriver.chrome.driver", "libraries/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://vnexpress.net/");
	}

	@BeforeMethod
	public void createReportBeforeTestCase(Method method)
	{
		logReport = extent.createTest(method.getName());
	}

	@Test
	public void TC_01() {
		Assert.assertEquals("AAAA", driver.getCurrentUrl());
	}

	@Test
	public void TC_02() {
		Assert.assertEquals("https://vnexpress.net/", driver.getCurrentUrl());
	}

	@Test
	public void TC_03() {
		driver.findElement(By.xpath("//a[text()='Thể thao']")).click();
		Assert.assertEquals("https://vnexpress.net/", driver.getCurrentUrl());
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception
	{
		switch (result.getStatus()) {
		case ITestResult.FAILURE:
			logReport.log(Status.FAIL, MarkupHelper.createLabel("TEST CASE FAIL: ".concat(result.getName()), ExtentColor.RED));
			logReport.log(Status.FAIL, MarkupHelper.createLabel("ERROR CONTENT: " + result.getThrowable(), ExtentColor.RED));
			logReport.log(Status.INFO, MarkupHelper.createLabel("SCREENSHOT: ", ExtentColor.CYAN));
			logReport.fail("", MediaEntityBuilder.createScreenCaptureFromPath(captureImage(driver)).build());
			break;

		case ITestResult.SKIP:
			logReport.log(Status.SKIP, MarkupHelper.createLabel("TEST CASE SKIP: ".concat(result.getName()), ExtentColor.ORANGE));
			break;

		case ITestResult.SUCCESS:
			logReport.log(Status.PASS, MarkupHelper.createLabel("TEST CASE PASS: ".concat(result.getName()), ExtentColor.GREEN));
			break;

		default:
			break;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	public static String captureImage(WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		return scrFile.getAbsolutePath();
	}
}
