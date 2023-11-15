package dev.noah.services;

import java.util.List;
import java.util.Set;

import dev.noah.daos.ReimbursementDAO;
import dev.noah.daos.ReimbursementDAOMaria;
import dev.noah.entities.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {

	private static ReimbursementDAO rdao = ReimbursementDAOMaria.getReimbursementDAOmaria();
	
	
	
	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		return rdao.createReimbursement(reimbursement);
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		return rdao.getReimbursementById(id);
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		 return rdao.getAllReimbursements();
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		return rdao.updateReimbursement(reimbursement);

	}

}
