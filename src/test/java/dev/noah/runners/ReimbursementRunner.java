package dev.noah.runners;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dev.noah.pages.EmployeeLoginPage;
import dev.noah.pages.EmployeePage;
import dev.noah.pages.ManagerLoginPage;
import dev.noah.pages.ManagerPage;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "dev.noah.steps")
public class ReimbursementRunner {

	public static WebDriver driver;
	public static EmployeeLoginPage loginPage;
	public static EmployeePage epage;
	public static ManagerPage mpage;
	public static ManagerLoginPage mlpage;
	
	@BeforeClass
	public static void setUp() {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		System.out.println(file.getAbsolutePath());
		driver = new ChromeDriver();
		loginPage = new EmployeeLoginPage(driver);
		epage = new EmployeePage(driver);
		mpage = new ManagerPage(driver);
		mlpage = new ManagerLoginPage(driver);
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
