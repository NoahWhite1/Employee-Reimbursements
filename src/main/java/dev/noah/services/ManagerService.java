package dev.noah.services;

import java.util.List;
import java.util.Set;

import dev.noah.entities.Manager;
import dev.noah.entities.Reimbursement;

public interface ManagerService {

	//read
	Manager getManagerById(int id);
	List<Manager> getAllManagers();
	
	//update
	Manager updateManager(Manager manager);
	Reimbursement updateReimbursement(Reimbursement reimbursement);
}
