package dev.noah.daos;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.noah.entities.Reimbursement;
import dev.noah.utils.HibernateUtil;

public class ReimbursementDAOMaria implements ReimbursementDAO {

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private  static ReimbursementDAO reimbursementDAOMaria = null;
	
	private ReimbursementDAOMaria() {
		
	};
	
	public static ReimbursementDAO getReimbursementDAOmaria() {
		if(reimbursementDAOMaria == null) {
			reimbursementDAOMaria = new ReimbursementDAOMaria();
			return reimbursementDAOMaria;
		}
		return reimbursementDAOMaria;
	}
	
	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		
		try(Session s = sf.openSession()){
			
			s.beginTransaction();
			s.save(reimbursement);
			s.getTransaction().commit();
			return reimbursement;
			
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		try(Session s = sf.openSession()){
		
			Reimbursement reimbursement = s.get(Reimbursement.class, id);
			return reimbursement;
		
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		try(Session s = sf.openSession()){
		
			Criteria c = s.createCriteria(Reimbursement.class);
			List<Reimbursement> reimbursements = c.list();
			return reimbursements;
			
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		try(Session s = sf.openSession()){
			
			s.beginTransaction();
			s.update(reimbursement);
			s.getTransaction().commit();
			return reimbursement;
		
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}
