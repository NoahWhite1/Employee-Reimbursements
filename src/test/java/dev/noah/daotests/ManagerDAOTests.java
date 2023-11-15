package dev.noah.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.noah.daos.ManagerDAO;
import dev.noah.daos.ManagerDAOMaria;
import dev.noah.entities.Manager;
import dev.noah.utils.ConnectionUtility;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class) 
class ManagerDAOTests {

	ManagerDAO mdao = ManagerDAOMaria.getManagerDAOMaria();
	
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
	void posGetManager() {
		Manager manager = mdao.getManagerById(3);
		Assertions.assertEquals(3, manager.getmId());
	}
	@Test
	@Order(2)
	void posGetAllManagers() {
		Assertions.assertEquals(5, mdao.getAllManagersById().size());
	}
	
	@Test
	@Order(3)
	void posUpdateManager1() {
		Manager manager = mdao.getManagerById(5);
		manager.setName("TestName");
		mdao.updateManager(manager);
		Assertions.assertEquals("TestName", mdao.getManagerById(5).getName());
	}
	
	@Test
	@Order(4)
	void posUpdateManager2() {
		Manager manager = mdao.getManagerById(2);
		manager.setUsername("TESTING");
		mdao.updateManager(manager);
		Assertions.assertEquals("TESTING", mdao.getManagerById(2).getUsername());
	}
	@Test
	@Order(5)
	void posUpdateManager3() {
		Manager manager = mdao.getManagerById(4);
		manager.setPassword("TestPassword");
		mdao.updateManager(manager);
		Assertions.assertEquals("TestPassword", mdao.getManagerById(4).getPassword());
	}
		
	@Test
	@Order(6)
	void negGetManager() {
		Manager manager = mdao.getManagerById(30);
		Assertions.assertEquals(null, manager);
	}

}
