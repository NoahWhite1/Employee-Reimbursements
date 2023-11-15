package dev.noah.controllers;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dev.noah.entities.Employee;
import dev.noah.entities.Reimbursement;
import dev.noah.services.EmployeeService;
import dev.noah.services.EmployeeServiceImpl;
import io.javalin.http.Handler;

public class EmployeeController {

	public static Gson gson = new Gson();
	public static EmployeeService eserv = new EmployeeServiceImpl();

	public static Handler getEmployee = (ctx) -> {
		String id = ctx.pathParam(":eId");
		Employee employee = eserv.getEmployeeById(Integer.parseInt(id));

		if (employee == null) {
			ctx.status(404);
			ctx.result("No employee was found.");
		} else {
			ctx.status(200);
			ctx.result(gson.toJson(employee));
		}

	};

	public static Handler getAllEmployees = (ctx) -> {
		List<Employee> employees = eserv.getAllEmployees();

		if (employees == null) {
			ctx.status(404);
			ctx.result("No employees were found.");
		} else {
			ctx.status(200);
			ctx.result(gson.toJson(employees));
		}
	};

	public static Handler updateEmployee = (ctx) -> {
		Employee employee = gson.fromJson(ctx.body(), Employee.class);
		eserv.updateEmployee(employee);

		if (employee == null) {
			ctx.status(404);
			ctx.result("Something went wrong with updating the employee!");
		} else {
			ctx.status(200);
			ctx.result(gson.toJson(employee));
		}
	};

	public static Handler createReimbursement = (ctx) -> {

		JsonObject reimbursementJSON = gson.fromJson(ctx.body(), JsonObject.class);
		String purpose = reimbursementJSON.get("purpose").toString();
		purpose = purpose.replace("\"","");
		double amount = reimbursementJSON.get("amount").getAsDouble();
		int employeeNum = reimbursementJSON.get("employee").getAsInt();
		Employee employee = eserv.getEmployeeById(employeeNum);	

		Reimbursement reimbursement = eserv.createReimbursement(purpose, amount, employee);
		
		if (reimbursement == null) {
			ctx.status(404);
			ctx.result("Something went wrong with reimbursement creation!");
		} else {
			ctx.status(201);
			ctx.result(gson.toJson(reimbursement));
		}
	};
	
}
