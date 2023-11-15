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

import dev.noah.daos.ReimbursementDAO;
import dev.noah.daos.ReimbursementDAOMaria;
import dev.noah.entities.Reimbursement;
import dev.noah.utils.ConnectionUtility;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class ReimbursementDAOTests {

	ReimbursementDAO rdao = ReimbursementDAOMaria.getReimbursementDAOmaria();
	
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
	void posGetReimbursement() {
		Reimbursement reimbursement = rdao.getReimbursementById(3);
		Assertions.assertEquals(3, reimbursement.getrId());
	}
	
	@Test
	@Order(2)
	void posGetAllReimbursements(){
		Assertions.assertEquals(5, rdao.getAllReimbursements().size());
	}

	@Test
	@Order(3)
	void posUpdateReimbursement1() {
		Reimbursement reimbursement = rdao.getReimbursementById(3);
		reimbursement.setAmount(20);
		rdao.updateReimbursement(reimbursement);
		Assertions.assertEquals(20, rdao.getReimbursementById(3).getAmount());
	}
	
	@Test
	@Order(4)
	void posUpdateReimbursement2() {
		Reimbursement reimbursement = rdao.getReimbursementById(1);
		reimbursement.setApproved(true);
		rdao.updateReimbursement(reimbursement);
		Assertions.assertEquals(true, rdao.getReimbursementById(1).isApproved());
	}
	
	@Test
	@Order(5)
	void posUpdateReimbursement3() {
		Reimbursement reimbursement = rdao.getReimbursementById(2);
		reimbursement.setCreatedBy(3);
		rdao.updateReimbursement(reimbursement);
		Assertions.assertEquals(3, rdao.getReimbursementById(2).getCreatedBy());
	}
	
	@Test
	@Order(6)
	void posUpdateReimbursement4() {
		Reimbursement reimbursement = rdao.getReimbursementById(2);
		reimbursement.setNote("TestNote");
		rdao.updateReimbursement(reimbursement);
		Assertions.assertEquals("TestNote", rdao.getReimbursementById(2).getNote());
	}
	
	@Test
	@Order(7)
	void posUpdateReimbursement5() {
		Reimbursement reimbursement = rdao.getReimbursementById(2);
		reimbursement.setPurpose("TestPurpose");
		rdao.updateReimbursement(reimbursement);
		Assertions.assertEquals("TestPurpose", rdao.getReimbursementById(2).getPurpose());
	}
	
		
	@Test
	@Order(8)
	void negGetReimbursement() {
		Assertions.assertEquals(null, rdao.getReimbursementById(50));
	}
	
	@Test
	@Order(9)
	void posCreateReimbursement() {
		Reimbursement reimbursement = new Reimbursement(0,"",22,2,3,false,"");
		rdao.createReimbursement(reimbursement);
		Assertions.assertEquals(reimbursement.getrId(), rdao.getReimbursementById(6).getrId());
	}
}
