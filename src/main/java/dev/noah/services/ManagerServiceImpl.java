package dev.noah.services;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.noah.daos.ManagerDAO;
import dev.noah.daos.ManagerDAOMaria;
import dev.noah.daos.ReimbursementDAO;
import dev.noah.daos.ReimbursementDAOMaria;
import dev.noah.entities.Manager;
import dev.noah.entities.Reimbursement;
import dev.noah.utils.HibernateUtil;
import io.javalin.http.sse.SseClient;

public class ManagerServiceImpl implements ManagerService {

	private  ManagerDAO mdao = ManagerDAOMaria.getManagerDAOMaria();
	private  ReimbursementDAO rdao = ReimbursementDAOMaria.getReimbursementDAOmaria();
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	
	public ManagerServiceImpl() {
		
	}
	
	public ManagerServiceImpl(ManagerDAO mdao, ReimbursementDAO rdao) {
		super();
		this.rdao = rdao;
		this.mdao = mdao;
	}
	
	
	@Override
	public Manager getManagerById(int id) {
		return mdao.getManagerById(id);

	}

	@Override
	public List<Manager> getAllManagers() {
		return mdao.getAllManagersById();
	}

	@Override
	public Manager updateManager(Manager manager) {
		return mdao.updateManager(manager);
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		return rdao.updateReimbursement(reimbursement);		
	}



}
