package dev.noah.daos;


import java.util.List;

import dev.noah.entities.Reimbursement;

public interface ReimbursementDAO {

	//Create
	Reimbursement createReimbursement(Reimbursement reimbursement);
	
	//Read
	Reimbursement getReimbursementById(int id);
	List<Reimbursement> getAllReimbursements();
	
	//Update
	Reimbursement updateReimbursement(Reimbursement reimbursement);	
}
