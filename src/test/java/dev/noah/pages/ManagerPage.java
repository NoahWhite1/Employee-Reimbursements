package dev.noah.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage {
WebDriver driver;
	
	public ManagerPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "reimbursementIdInput")
	public WebElement reimbursementId;
	
	@FindBy(id = "purpose")
	public WebElement purpose;
	
	@FindBy(id = "checkboxInput")
	public WebElement checkbox;
	
	@FindBy(id = "submitButton")
	public WebElement submitButton;
}
