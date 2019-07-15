package com.viktoriia.service.impl;

import java.io.Serializable;   
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.DepartmentEntity;
import com.viktoriia.entity.Employee;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.enums.Department;
import com.viktoriia.rabbitmq.CRUDOperation;
import com.viktoriia.rabbitmq.QueueConsumer;
import com.viktoriia.rabbitmq.QueueMessage;
import com.viktoriia.rabbitmq.QueueProducer;
import com.viktoriia.utils.HibernateSessionFactoryUtil;
import com.viktoriia.service.Service;

public class EmployeeService implements Serializable, Service<Employee> {
	
	private static final long serialVersionUID = -4934208404889582098L;
	
	public EmployeeService() { 
		
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
		Shipment shipment = new Shipment();
		shipment.setId(8);
	
		QueueMessage message = new QueueMessage();
		message.setClassName(Shipment.class.toString());
		message.setOperation(CRUDOperation.READ_BY_FIELD);
		message.setEntity(shipment);
		
		QueueProducer producer = new QueueProducer("queue");
		producer.sendMessage(message);

		
		QueueConsumer consumer = new QueueConsumer("queue");
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		
		EmployeeService service = new EmployeeService();
		//System.out.println(service.getAllDepartments());
		//service.add(e);
	}

	@Override
	public void delete(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<Employee> employees = (ArrayList<Employee>) getAll();
		for(Employee empl : employees) {
			if(empl.getId() == id) {
			session.delete(empl);
			} 
		}
		
		tx1.commit();
		session.close();
	}
	
	@Override
	public List<Employee> getAll(){  
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
	public Employee getById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<Employee> employees = (ArrayList<Employee>) getAll();
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
