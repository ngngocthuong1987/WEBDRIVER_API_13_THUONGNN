package testNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_02_Group_Priority {

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("----- BEFORE CLASS -----");
	}

	@Test(groups = "login")
	public void TC_01() {
		System.out.println("Test case 01");
	}

	@Test(groups = "login")
	public void TC_02() {
		System.out.println("Test case 02");
	}

	@Test(groups = "register")
	public void TC_03() {
		System.out.println("Test case 03");
	}

	@Test(groups = "register")
	public void TC_04() {
		System.out.println("Test case 04");
	}

	@Test(groups = "register")
	public void TC_05() {
		System.out.println("Test case 05");
	}

	@Test(groups = "login")
	public void TC_06() {
		System.out.println("Test case 06");
	}

}
