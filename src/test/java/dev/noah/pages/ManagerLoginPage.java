package dev.noah.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerLoginPage {

	WebDriver driver;
	
	public ManagerLoginPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "loginButton")
	public WebElement loginButton;
	 
	@FindBy(id = "usernameLogin")
	public WebElement usernameInput;

	@FindBy(id = "passwordLogin")
	public WebElement passwordInput;
}
