package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_01_Annotation {

	@Test(dataProvider = "dp")
	public void TC_01(Integer n, String s) {
		System.out.println("Test case 01 with data provider");
	}

	@Test
	public void TC_02() {
		System.out.println("Test case 02");
	}

	@Test
	public void TC_03() {
		System.out.println("Test case 03");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("----- BEFORE METHOD -----");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("----- AFTER METHOD -----");
	}


	@DataProvider
	public Object[][] dp() {

		System.out.println("----- DATA PROVIDER -----");

		return new Object[][] {
			new Object[] { 1, "a" },
			new Object[] { 2, "b" },
		};
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("----- BEFORE CLASS -----");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("----- AFTER CLASS -----");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("----- BEFORE TEST -----");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("----- AFTER TEST -----");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("----- BEFORE SUITE -----");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("----- AFTER SUITE -----");
	}
}
