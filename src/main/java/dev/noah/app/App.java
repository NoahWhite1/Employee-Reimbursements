package dev.noah.app;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.noah.controllers.EmployeeController;
import dev.noah.controllers.ManagerController;
import dev.noah.controllers.ReimbursementController;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {

		Javalin app = Javalin.create(config ->{
				
				config.addStaticFiles("FrontEnd");
				
				
		}).start(7000);
		
		app.post("/reimbursements", ReimbursementController.createReimbursement); // create a reimbursement
		app.post("/employees/reimbursements", EmployeeController.createReimbursement); // Create reimbursements through employee login
		// Read
		app.get("/employees", EmployeeController.getAllEmployees); // Find all employees
		app.get("/employees/:eId", EmployeeController.getEmployee); // Find an Employee
		app.get("/managers", ManagerController.getAllManagers); // Find all managers
		app.get("/managers/:mId", ManagerController.getManager); // Find a manager
		app.get("reimbursements", ReimbursementController.getAllReimbursements); // Find all reimbursements
		app.get("/reimbursements/:rId", ReimbursementController.getReimbursement); // Find a reimbursement
		// Update
		app.put("/employees", EmployeeController.updateEmployee); // Update an employee
		app.put("/managers", ManagerController.updateManager); // Update a manager
		app.put("/reimbursements", ReimbursementController.updateReimbursement); // Update a reimbursement
		app.put("/managers/reimbursements", ManagerController.updateReimbursement); // Allows managers to update reimbursements
	}
}
