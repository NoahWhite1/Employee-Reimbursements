package dev.noah.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.noah.entities.Employee;
import dev.noah.utils.HibernateUtil;

public class EmployeeDAOMaria implements EmployeeDAO {

	private static EmployeeDAO employeeDAOMaria = null;
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	private EmployeeDAOMaria() {

	}

	public static EmployeeDAO getEmployeeDAOMaria() {

		if (employeeDAOMaria == null) {
			employeeDAOMaria = new EmployeeDAOMaria();
			return employeeDAOMaria;
		}
		return employeeDAOMaria;
	}

	@Override
	public Employee getEmployeeById(int id) {
	
		try (Session s = sf.openSession()) {

			Employee employee = s.get(Employee.class, id);
			return employee;

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Employee> getAllEmployees() {
		
		try (Session s = sf.openSession()) {

			s.beginTransaction();
			Criteria c = s.createCriteria(Employee.class);
			List<Employee> employees = c.list();
			return employees;
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
	
		try (Session s = sf.openSession()) {
			
			s.beginTransaction();
			s.update(employee);
			s.getTransaction().commit();
			return employee;
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
}
