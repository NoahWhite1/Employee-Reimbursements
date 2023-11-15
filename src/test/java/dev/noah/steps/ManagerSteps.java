package dev.noah.steps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.noah.daos.ReimbursementDAO;
import dev.noah.daos.ReimbursementDAOMaria;
import dev.noah.entities.Reimbursement;
import dev.noah.pages.ManagerLoginPage;
import dev.noah.pages.ManagerPage;
import dev.noah.runners.ReimbursementRunner;

public class ManagerSteps {

	public static WebDriver driver = ReimbursementRunner.driver;
	public static ManagerPage mpage = ReimbursementRunner.mpage;
	public static ManagerLoginPage mlpage = ReimbursementRunner.mlpage;
	public static ReimbursementDAO rdao = ReimbursementDAOMaria.getReimbursementDAOmaria();
	
	
	@Given("^The manager is on the manager page$")
	public void the_manager_is_on_the_manager_page() throws Throwable {
		driver.get("http://localhost:7000/managerlogin.html");
		mlpage.usernameInput.sendKeys("Hermit");
		mlpage.passwordInput.sendKeys("smallBoi");
		mlpage.loginButton.click();
		
	}

	@When("^The manager enters a reimbursement ID$")
	public void the_manager_enters_a_reimbursement_ID() throws Throwable {
		Thread.sleep(2000);
	    mpage.reimbursementId.sendKeys(String.valueOf(rdao.getAllReimbursements().size())); //we will always get the last value
	}

	@When("^The manager checks reimbursement status as approved or not$")
	public void the_manager_checks_reimbursement_status_as_approved_or_not() throws Throwable {
	    mpage.checkbox.click();
	    
	}

	@When("^The manager enters a note or not$")
	public void the_manager_enters_a_note_or_not() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    mpage.purpose.sendKeys("Updated in cucumber");
	}

	@When("^The manager presses the submit button$")
	public void the_manager_presses_the_submit_button() throws Throwable {
	    mpage.submitButton.click();
	    Thread.sleep(3000);
	}

	@Then("^The reimbursement is updated in the database$")
	public void the_reimbursement_is_updated_in_the_database() throws Throwable {
		Reimbursement reimbursement = rdao.getAllReimbursements().get(rdao.getAllReimbursements().size()-1); //we will always get the last value
		Assertions.assertEquals("Updated in cucumber", reimbursement.getNote());
	}
}
