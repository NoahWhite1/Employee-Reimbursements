package dev.noah.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jetty.websocket.common.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.noah.daos.EmployeeDAO;
import dev.noah.daos.ReimbursementDAO;
import dev.noah.entities.Employee;
import dev.noah.entities.Reimbursement;
import dev.noah.services.EmployeeService;
import dev.noah.services.EmployeeServiceImpl;
import dev.noah.services.ReimbursementService;
import dev.noah.services.ReimbursementServiceImpl;
import dev.noah.utils.ConnectionUtility;
import dev.noah.utils.HibernateUtil;

import org.mockito.ArgumentMatchers;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class) 
class EmployeeServiceTests {

	public EmployeeService eserv = new EmployeeServiceImpl();
	public ReimbursementService rserv = new ReimbursementServiceImpl();
	
	@BeforeAll
	static void Setup() { 
		try(Connection con = ConnectionUtility.getConnection()){
			String sql = "CALL set_up_p1db";
			CallableStatement cs = con.prepareCall(sql);
			cs.execute();
			sql = "CALL insert_into_p1db";
			cs = con.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterAll
	static void tearDown() {
		try(Connection con = ConnectionUtility.getConnection()){
			String sql = "CALL tear_down_p1db";
			CallableStatement cs = con.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void posGetEmployeeById() {
		Assertions.assertEquals(3, eserv.getEmployeeById(3).geteId());
	}
	
	@Test
	@Order(2)
	void posGetAllEmployees() {
		List<Employee> employees = eserv.getAllEmployees();
		Assertions.assertEquals(5, employees.size());
	}
	
	@Test
	@Order(3)
	void posUpdateEmployee() {
		Employee employee = eserv.getEmployeeById(3);
		employee.setPassword("Cheese");
		eserv.updateEmployee(employee);
		Assertions.assertEquals("Cheese", employee.getPassword());
	}
	
	
	@Test
	@Order(4)
	void posCreateReimbursement() {
		Employee employee = eserv.getEmployeeById(3);
		Reimbursement reimbursement = eserv.createReimbursement("yes", 22, employee);
		Assertions.assertEquals(reimbursement.getrId(), rserv.getReimbursementById(6).getrId());
	}
	
	
	
	@Test
	@Order(5)
	void mockPosCreateReimbursement() {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setAmount(10);
		Reimbursement reimbursement2 = new Reimbursement();
		EmployeeService eserv2 = Mockito.mock(EmployeeService.class);
		
		String s = "";
		double d = 100;
		Employee employee = new Employee();
		
		Mockito.when(eserv2.createReimbursement(s,d,employee)).thenReturn(reimbursement);
		Reimbursement reimbursement3 = eserv2.createReimbursement(s,d,employee);
		Assertions.assertEquals(10, reimbursement3.getAmount());
	}
	
	
	@Test
	@Order(6)
	void mockPosGetEmployee() {
		Employee employee = new Employee();
		employee.seteId(1);
		Employee employee2 = new Employee();
		employee2.seteId(2);
		EmployeeService eserv2 = Mockito.mock(EmployeeService.class);
		Mockito.when(eserv2.getEmployeeById(2)).thenReturn(employee);
		Employee employee3 = eserv2.getEmployeeById(2);
		Assertions.assertEquals(1, employee3.geteId());
	}
	
	@Test
	@Order(7)
	void mockPosGetAllEmployees() {
		
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee1 = new Employee();
		Employee employee2 = new Employee();
		Employee employee3 = new Employee();
	
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
		
		EmployeeService eserv2 = Mockito.mock(EmployeeService.class);
		Mockito.when(eserv2.getAllEmployees()).thenReturn(employees);
		int employeeSize = eserv2.getAllEmployees().size();
		Assertions.assertEquals(3, employeeSize);
	}
	
	@Test
	@Order(8)
	void mockPosUpdateEmployee() {
		Employee employee = new Employee();
		employee.setName("mockname");
		Employee employee2 = new Employee();
		EmployeeService eserv2 = Mockito.mock(EmployeeService.class);
		Mockito.when(eserv2.updateEmployee(employee2)).thenReturn(employee);
		Employee employee3 = eserv2.updateEmployee(employee2);
		Assertions.assertEquals("mockname", employee3.getName());
	}
	
	

	
	
}
