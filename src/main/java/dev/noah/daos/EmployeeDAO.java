package dev.noah.daos;

import java.util.List;
import java.util.Set;

import dev.noah.entities.Employee;

public interface EmployeeDAO {

	//Read
	Employee getEmployeeById(int id);
	List<Employee> getAllEmployees();
	
	//Update
	Employee updateEmployee(Employee employee);

}
