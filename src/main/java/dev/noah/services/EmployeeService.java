package dev.noah.services;

import java.util.List;
import java.util.Set;

import dev.noah.entities.Employee;
import dev.noah.entities.Reimbursement;

public interface EmployeeService {
	
	//Create
	Reimbursement createReimbursement(String purpose, double amount, Employee employee);
	
	//Read
	Employee getEmployeeById(int id);
	List<Employee> getAllEmployees();
	
	//Update
	Employee updateEmployee(Employee employee);
}
