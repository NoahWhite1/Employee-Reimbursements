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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.noah.entities.Reimbursement;
import dev.noah.services.EmployeeService;
import dev.noah.services.ManagerService;
import dev.noah.services.ReimbursementService;
import dev.noah.services.ReimbursementServiceImpl;
import dev.noah.utils.ConnectionUtility;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class) 
class ReimbursementServiceTests {

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
	void posGetReimbursementById() {
		Assertions.assertEquals(3, rserv.getReimbursementById(3).getCreatedBy());
	}
	
	@Test
	@Order(2)
	void posGetAllReimbursements() {
		Reimbursement reimbursement = rserv.getReimbursementById(3);
		rserv.updateReimbursement(reimbursement);
	}
	
	@Test
	@Order(3)
	void negGetReimbursementById() {
		Assertions.assertEquals(null, rserv.getReimbursementById(30));
	}
	
	
	@Test
	@Order(4)
	void updateReimbursement1() {
		Reimbursement reimbursement = rserv.getReimbursementById(3);
		reimbursement.setApprovedBy(3);
		rserv.updateReimbursement(reimbursement);
		Assertions.assertEquals(3, rserv.getReimbursementById(3).getApprovedBy());
	}
	
	@Test
	@Order(5)
	void mockPosGetReimbursement() {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setrId(1);
		Reimbursement reimbursement2 = new Reimbursement();
		reimbursement2.setrId(2);
		ReimbursementService rserv2 = Mockito.mock(ReimbursementService.class);
		Mockito.when(rserv2.getReimbursementById(2)).thenReturn(reimbursement);
		Reimbursement reimbursement3 = rserv2.getReimbursementById(2);
		Assertions.assertEquals(1, reimbursement3.getrId());
	}
	
	@Test
	@Order(6)
	void mockPosGetAllReimbursement() {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		Reimbursement reimbursement1 = new Reimbursement();
		Reimbursement reimbursement2 = new Reimbursement();
		Reimbursement reimbursement3 = new Reimbursement();
	
		reimbursements.add(reimbursement1);
		reimbursements.add(reimbursement2);
		reimbursements.add(reimbursement3);
		
		ReimbursementService rserv2 = Mockito.mock(ReimbursementService.class);
		Mockito.when(rserv2.getAllReimbursements()).thenReturn(reimbursements);
		int reimbursementSize = rserv2.getAllReimbursements().size();
		Assertions.assertEquals(3, reimbursementSize);
	}
	
	@Test
	@Order(7)
	void mockPosUpdateReimbursement() {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setNote("mocking");
		Reimbursement reimbursement2 = new Reimbursement();
		ReimbursementService rserv2 = Mockito.mock(ReimbursementService.class);
		Mockito.when(rserv2.updateReimbursement(reimbursement2)).thenReturn(reimbursement);
		Reimbursement reimbursement3 = rserv2.updateReimbursement(reimbursement2);
		Assertions.assertEquals("mocking", reimbursement3.getNote());
	}
	
	@Test
	@Order(8)
	void mockPosCreateReimbursement() {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setAmount(3);
		Reimbursement reimbursement2 = new Reimbursement();
		ReimbursementService rserv2 = Mockito.mock(ReimbursementService.class);
		Mockito.when(rserv2.createReimbursement(reimbursement2)).thenReturn(reimbursement);
		Reimbursement reimbursement3 = rserv2.createReimbursement(reimbursement2);
		Assertions.assertEquals(3, reimbursement3.getAmount());
	}


}
