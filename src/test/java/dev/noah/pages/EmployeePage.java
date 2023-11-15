package dev.noah.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeePage {
	
	WebDriver driver;
	
	public EmployeePage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="purposeInput")
	public WebElement purposeInput;
	
	@FindBy(id = "amountInput")
	public WebElement amountInput;
	
	@FindBy(id = "submitButton")
	public WebElement submitButton;
}
