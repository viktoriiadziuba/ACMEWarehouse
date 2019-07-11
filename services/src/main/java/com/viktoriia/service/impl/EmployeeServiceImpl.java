package com.viktoriia.service.impl;

import java.io.Serializable; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.DepartmentEntity;
import com.viktoriia.entity.Employee;
import com.viktoriia.entity.Person;
import com.viktoriia.entity.enums.Department;
import com.viktoriia.service.EmployeeService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class EmployeeServiceImpl implements Serializable, EmployeeService {
	
	private static final long serialVersionUID = -4934208404889582098L;
	
	public EmployeeServiceImpl() { 

	}

	@Override
	public void add(Employee employee) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		List<DepartmentEntity> departments = getAllDepartments();
		for(DepartmentEntity dep : departments) {
			if(employee.getDepartment().getDepartment().name() == dep.getDepartment().name()) {
				employee.setDepartment(dep);
			} 
		}
		session.save(employee.getPerson());
		session.save(employee);
		
		tx1.commit();
		session.close();
	}
	
	public static void main(String[] args) throws Exception {
		DepartmentEntity dep = new DepartmentEntity();
		dep.setDepartment(Department.HR_DEPARTMENT);
		
		Person p = new Person();
		p.setEmail("allina21@email.com");
		p.setDateOfBirth(LocalDate.now());
		p.setPhoneNumber("+3809795021");
		p.setFullName("Allina");
		
		Employee e = new Employee();
		e.setPerson(p);
		e.setDepartment(dep);
		
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		//System.out.println(service.getAllDepartments());
		//service.add(e);
	}

	@Override
	public void delete(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<Employee> employees = (ArrayList<Employee>) getAllEmployees();
		for(Employee empl : employees) {
			if(empl.getId() == id) {
			session.delete(empl);
			} 
		}
		
		tx1.commit();
		session.close();
	}
	
	@Override
	public List<Employee> getAllEmployees(){  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
	    try {
	        return session.createCriteria(Employee.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
	    	session.close();
	    }
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<Employee> employees = (ArrayList<Employee>) getAllEmployees();
		for(Employee empl : employees) {
			if(empl.getId() == id) {
			session.get(Employee.class, id);
			return empl;
			}
		}
		
		tx1.commit();
		session.close();
		return null;
	}

	@Override
	public List<DepartmentEntity> getAllDepartments() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		try
	    {
	        return session.createCriteria(DepartmentEntity.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
	    	session.close();
	    }
	}
	
	public static void insertAllDepartments() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		DepartmentEntity dep1 = new DepartmentEntity();
		dep1.setDepartment(Department.CHIEF_DEPARTMENT);
		DepartmentEntity dep2 = new DepartmentEntity();
		dep2.setDepartment(Department.EQUIPMENT_DEPARTMENT);
		DepartmentEntity dep3 = new DepartmentEntity();
		dep3.setDepartment(Department.HR_DEPARTMENT);
		DepartmentEntity dep4 = new DepartmentEntity();
		dep4.setDepartment(Department.SHIPMENT_DEPARTMENT);
		DepartmentEntity dep5 = new DepartmentEntity();
		dep5.setDepartment(Department.MANAGMENT_DEPARTMENT);
				
		session.save(dep1);
		session.save(dep2);
		session.save(dep3);
		session.save(dep4);
		session.save(dep5);
		
		tx1.commit();
		session.close();
	}

}
