package dev.noah.steps;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.noah.daos.ReimbursementDAO;
import dev.noah.daos.ReimbursementDAOMaria;
import dev.noah.pages.EmployeeLoginPage;
import dev.noah.pages.EmployeePage;
import dev.noah.runners.ReimbursementRunner;

public class EmployeeSteps {
	
	public static EmployeeLoginPage loginPage = ReimbursementRunner.loginPage;
	public static EmployeePage epage = ReimbursementRunner.epage;
	public static WebDriver driver = ReimbursementRunner.driver;
	public static ReimbursementDAO rdao = ReimbursementDAOMaria.getReimbursementDAOmaria();
	private int rSize = 0;
	
	@Given("^The employee is on the employee login page$")
	public void the_employee_is_on_the_employee_login_page() throws Throwable {
	    driver.get("http://localhost:7000/employeelogin.html");
	}

	@When("^The employee inputs their login credentials$")
	public void the_employee_inputs_their_login_credentials() throws Throwable {
		loginPage.usernameInput.sendKeys("CT-5555");
		loginPage.passwordInput.sendKeys("pewpew");
	}

	@When("^The employee presses the login button$")
	public void the_employee_presses_the_login_button() throws Throwable {
		loginPage.loginButton.click();
	}

	@Then("^The employee should be in the employee page$")
	public void the_employee_should_be_in_the_employee_page() throws Throwable {
		Thread.sleep(2000);
	    Assertions.assertEquals("http://localhost:7000/employee.html", driver.getCurrentUrl());
	}

	@Given("^The employee is on the employee page$")
	public void the_employee_is_on_the_employee_page() throws Throwable {
	    driver.get("http://localhost:7000/employee.html");
	}

	@When("^The employee types the reason for reimbursement$")
	public void the_employee_types_the_reason_for_reimbursement() throws Throwable {
		epage.purposeInput.sendKeys("Testing");
	}

	@When("^The employee enters the amount for a reimbursement$")
	public void the_employee_enters_the_amount_for_a_reimbursement() throws Throwable {
	     epage.amountInput.sendKeys(String.valueOf(3333));
	}

	@When("^The employee clicks the submit button$")
	public void the_employee_clicks_the_submit_button() throws Throwable {
		rSize = rdao.getAllReimbursements().size();
		epage.submitButton.click();
		Thread.sleep(2000);
	}

	@Then("^The reimbursement is added to the database$")
	public void the_reimbursement_is_added_to_the_database() throws Throwable {
		Assertions.assertEquals(rSize+1, rdao.getAllReimbursements().size());
	}


	
}
