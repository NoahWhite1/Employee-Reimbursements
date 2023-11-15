package dev.noah.services;

import java.util.List;
import java.util.Set;

import dev.noah.daos.EmployeeDAO;
import dev.noah.daos.EmployeeDAOMaria;
import dev.noah.daos.ReimbursementDAO;
import dev.noah.daos.ReimbursementDAOMaria;
import dev.noah.entities.Employee;
import dev.noah.entities.Reimbursement;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO edao = EmployeeDAOMaria.getEmployeeDAOMaria();
	private ReimbursementDAO rdao = ReimbursementDAOMaria.getReimbursementDAOmaria();
	
	
	
	public EmployeeServiceImpl() {
		super();
	}
	
	public EmployeeServiceImpl(EmployeeDAO edao, ReimbursementDAO rdao) {
		super();
		this.edao = edao;
		this.rdao = rdao;
	}
	
	
	@Override
	public Reimbursement createReimbursement(String purpose, double amount, Employee employee) {
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setPurpose(purpose);
		reimbursement.setAmount(amount);
		reimbursement.setCreatedBy(employee.geteId());
		return rdao.createReimbursement(reimbursement);
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		return edao.getEmployeeById(id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return edao.getAllEmployees();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return edao.updateEmployee(employee);
	}

}
