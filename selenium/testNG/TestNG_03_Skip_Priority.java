package testNG;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_03_Skip_Priority {

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("----- BEFORE CLASS -----");
	}

	@Test(groups = "login", priority = 1)
	public void createNewCustomer() {
		System.out.println("Test case 01");
	}

	@Test(groups = "login", priority = 2, dependsOnMethods = "createNewCustomer")
	public void editCustomer() {
		System.out.println("Test case 02");
		Assert.assertTrue(false);
	}

	@Test(groups = "register", priority = 3, dependsOnMethods = "editCustomer")
	public void newAccount() {
		System.out.println("Test case 03");
	}

	@Test(groups = "register", priority = 4, dependsOnMethods = "newAccount")
	public void editAccout() {
		System.out.println("Test case 04");
	}

	@Test(groups = "register", priority = 5, enabled = false, dependsOnMethods = "editAccout")
	public void deleteAccount() {
		System.out.println("Test case 05");
	}

	@Test(groups = "register", priority = 6)
	public void deleteCustomer() {
		System.out.println("Test case 06");
	}

}
