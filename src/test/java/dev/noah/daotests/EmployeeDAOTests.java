package dev.noah.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.noah.daos.EmployeeDAO;
import dev.noah.daos.EmployeeDAOMaria;
import dev.noah.entities.Employee;
import dev.noah.utils.ConnectionUtility;

@TestMethodOrder(OrderAnnotation.class) 
class EmployeeDAOTests {

	public EmployeeDAO edao = EmployeeDAOMaria.getEmployeeDAOMaria();
		
	
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
		Employee employee = edao.getEmployeeById(2);
		Assertions.assertEquals(2, employee.geteId());
		System.out.println(employee);
	}
	
	@Test
	@Order(2)
	void posGetAllEmployees() {
		Assertions.assertEquals(5, edao.getAllEmployees().size());
	}
	
	@Test
	@Order(3)
	void posUpdateEmployee1() {
		Employee employee = edao.getEmployeeById(1);
		employee.setUsername("Todd");
		edao.updateEmployee(employee);
		Assertions.assertEquals("Todd", edao.getEmployeeById(1).getUsername());
	}
	
	@Test
	@Order(4)
	void posUpdateEmployee2() {
		Employee employee = edao.getEmployeeById(1);
		employee.setPassword("SuperStrongPassword");
		edao.updateEmployee(employee);
		Assertions.assertEquals("SuperStrongPassword", edao.getEmployeeById(1).getPassword());
	}
	
	@Test
	@Order(5)
	void posUpdateEmployee3() {
		Employee employee = edao.getEmployeeById(3);
		employee.setName("TestName");
		edao.updateEmployee(employee);
		Assertions.assertEquals("TestName", edao.getEmployeeById(3).getName());
	}
	
		
	@Test
	@Order(6)
	void negGetEmployeeId() {
		Assertions.assertEquals(null, edao.getEmployeeById(30));
	}
	

}
