package dev.noah.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.noah.entities.Manager;
import dev.noah.utils.HibernateUtil;

public class ManagerDAOMaria implements ManagerDAO{

	private static ManagerDAO managerDAOMaria = null; 
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	
	private  ManagerDAOMaria() {
		
	};
	
	public static ManagerDAO getManagerDAOMaria() {
		
		if(managerDAOMaria == null) {
			managerDAOMaria = new ManagerDAOMaria();
			return managerDAOMaria;
		}
		return managerDAOMaria;
	}

	@Override
	public Manager getManagerById(int id) {
	
		try(Session s = sf.openSession()){
			
			Manager manager = s.get(Manager.class, id);
			return manager;
			
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Manager> getAllManagersById() {
		
		try(Session s = sf.openSession()){
			
			s.beginTransaction();
			Criteria c = s.createCriteria(Manager.class);
			List<Manager> managers = c.list();
			s.getTransaction().commit();
			return managers;	
			
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		return null;
				
	}

	@Override
	public Manager updateManager(Manager manager) {
		
		try(Session s = sf.openSession()){
			
			s.beginTransaction();
			s.update(manager);
			s.getTransaction().commit();
			return manager;
			
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
