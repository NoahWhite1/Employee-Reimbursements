package dev.noah.services;

import java.util.List;
import java.util.Set;

import dev.noah.entities.Reimbursement;

public interface ReimbursementService {

	//Create
	Reimbursement createReimbursement(Reimbursement reimbursement);
	
	//Read
	Reimbursement getReimbursementById(int id);
	List<Reimbursement> getAllReimbursements();
	
	
	//Update
	Reimbursement updateReimbursement(Reimbursement reimbursement);
	
}
