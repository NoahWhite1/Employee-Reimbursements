package dev.noah.daos;


import java.util.List;

import dev.noah.entities.Manager;

public interface ManagerDAO {
	
	
	//Read
	Manager getManagerById(int id);
	List<Manager> getAllManagersById();
	//Update
	Manager updateManager(Manager manager);
	
}
