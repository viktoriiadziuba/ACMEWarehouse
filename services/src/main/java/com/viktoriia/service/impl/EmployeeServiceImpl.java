package com.viktoriia.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.Employee;
import com.viktoriia.rabbitmq.receive.DBEmployeeReceiver;
import com.viktoriia.rabbitmq.send.Sender;
import com.viktoriia.service.EmployeeService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

public class EmployeeServiceImpl implements EmployeeService {

	private Sender sender;
	
	public EmployeeServiceImpl() throws Exception {
		this.sender = new Sender();
		
		DBEmployeeReceiver.receiveEmployee();
	}
	
	public void add(Employee employee) {
		try {
			sender.sendObject(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		session.save(employee.getPerson());
		session.save(employee.getDepartment());
		session.save(employee);
		
		tx1.commit();
		session.close();
	}
	
//	public static void main(String[] args) throws Exception {
//		DepartmentEntity dep = new DepartmentEntity();
//		dep.setDepartment(Department.CHIEF_DEPARTMENT);
//		System.out.println(dep.toString());
//		
//		Person p = new Person();
//		p.setEmail("alina@email.com");
//		p.setDateOfBirth(LocalDate.now());
//		p.setPhoneNumber("+380964504243");
//		p.setFullName("Alina");
//		
//		Employee e = new Employee();
//		e.setPerson(p);
//		e.setDepartment(dep);
//		
//		Sender sender = new Sender();
//		sender.sendEmployee(e);
//		
//		EmployeeServiceImpl s = new EmployeeServiceImpl();
//	}

	public void update(Employee employee) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(employee.getPerson());
		session.update(employee.getDepartment());
		session.update(employee);
		tx1.commit();
		session.close();

	}

	public void delete(Employee employee) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(employee);
		tx1.commit();
		session.close();

	}

	public List<Employee> getAll() {
		String query = "SELECT * FROM public.employee";
		List<Employee> employees = (List<Employee>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(query).list();
		return employees;
	}
}
