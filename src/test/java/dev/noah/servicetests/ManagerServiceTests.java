package dev.noah.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.noah.daos.ManagerDAO;
import dev.noah.daos.ManagerDAOMaria;
import dev.noah.daos.ReimbursementDAO;
import dev.noah.daos.ReimbursementDAOMaria;
import dev.noah.entities.Employee;
import dev.noah.entities.Manager;
import dev.noah.entities.Reimbursement;
import dev.noah.services.EmployeeService;
import dev.noah.services.ManagerService;
import dev.noah.services.ManagerServiceImpl;
import dev.noah.services.ReimbursementService;
import dev.noah.services.ReimbursementServiceImpl;
import dev.noah.utils.ConnectionUtility;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;

@TestMethodOrder(OrderAnnotation.class) 
class ManagerServiceTests {

	public ManagerService mserv = new ManagerServiceImpl();
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
	void posGetManagerById() {
		Assertions.assertEquals(4, mserv.getManagerById(4).getmId());
	}
	
	@Test
	@Order(2)
	void negGetManagerById() {
		Assertions.assertEquals(null, mserv.getManagerById(70));
	}
	
	@Test
	@Order(3)
	void posGetAllManagers() {
		Assertions.assertEquals(5, mserv.getAllManagers().size());
	}
	
	@Test
	@Order(4)
	void posUpdateManager1() {
		Manager manager = mserv.getManagerById(2);
		manager.setName("testName");
		mserv.updateManager(manager);
		Assertions.assertEquals("testName", mserv.getManagerById(2).getName());
	}
	
	@Test
	@Order(5)
	void posUpdateReimbursement() {
		
		Reimbursement reimbursement = rserv.getReimbursementById(3);
		reimbursement.setApproved(true);
		reimbursement.setApprovedBy(3);
		reimbursement.setNote("YESYESYESYES");
		mserv.updateReimbursement(reimbursement);
		
		Assertions.assertEquals(reimbursement.getApprovedBy(), rserv.getReimbursementById(3).getApprovedBy());
	}
	
	@Test
	@Order(5)
	void mockPosUpdateReimbursement() {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setApproved(true);
		reimbursement.setNote("mocktest");
		Reimbursement reimbursement2 = new Reimbursement();
		ManagerService mserv2 = Mockito.mock(ManagerService.class);

		
		Mockito.when(mserv2.updateReimbursement(reimbursement2)).thenReturn(reimbursement);
		Reimbursement reimbursement3 = mserv2.updateReimbursement(reimbursement2);
		Assertions.assertEquals(true, reimbursement3.isApproved());
		Assertions.assertEquals("mocktest", reimbursement3.getNote());
	}
	
	
	@Test
	@Order(6)
	void mockPosGetManager() {
		Manager manager = new Manager();
		manager.setmId(1);
		Manager manager2 = new Manager();
		manager2.setmId(2);
		ManagerService mserv2 = Mockito.mock(ManagerService.class);
		Mockito.when(mserv2.getManagerById(2)).thenReturn(manager);
		Manager manager3 = mserv2.getManagerById(2);
		Assertions.assertEquals(1, manager3.getmId());
	}
	
	@Test
	@Order(7)
	void mockPosGetAllManagers() {
		
		List<Manager> managers = new ArrayList<Manager>();
		Manager manager1 = new Manager();
		Manager manager2 = new Manager();
		Manager manager3 = new Manager();
	
		managers.add(manager1);
		managers.add(manager2);
		managers.add(manager3);
		
		ManagerService mserv2 = Mockito.mock(ManagerService.class);
		Mockito.when(mserv2.getAllManagers()).thenReturn(managers);
		int employeeSize = mserv2.getAllManagers().size();
		Assertions.assertEquals(3, employeeSize);
	}
	
	@Test
	@Order(8)
	void mockPosUpdateManager() {
		Manager manager = new Manager();
		manager.setName("mockname");
		Manager manager2 = new Manager();
		ManagerService mserv2 = Mockito.mock(ManagerService.class);
		Mockito.when(mserv2.updateManager(manager2)).thenReturn(manager);
		Manager manager3 = mserv2.updateManager(manager2);
		Assertions.assertEquals("mockname", manager3.getName());
	}
	
}
