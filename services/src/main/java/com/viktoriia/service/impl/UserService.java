package com.viktoriia.service.impl;

import java.security.NoSuchAlgorithmException;    
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.DepartmentEntity;
import com.viktoriia.entity.Employee;
import com.viktoriia.entity.Person;
import com.viktoriia.entity.User;
import com.viktoriia.entity.UserRoleEntity;
import com.viktoriia.entity.enums.Department;
import com.viktoriia.entity.enums.UserRole;
import com.viktoriia.service.AbstractService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

@Stateless
public class UserService extends AbstractService {
		
	public UserService() {
		
	}

	public void signup(User user) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		List<UserRoleEntity> userRoles = getAllUserRoles();
		for(UserRoleEntity role : userRoles) {
			if(role.getRole().name() == user.getRole().getRole().name()) {
				user.setRole(role);
			}
		}
		
		user.setPassword(hashPassword(user.getPassword().toCharArray()));
		
		EmployeeService employeeService = new EmployeeService();
		employeeService.add(user.getEmployee());
		session.save(user);
				
		tx1.commit();
		session.close();		
	}

	public List<User> getAllUsers(){  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
	    try
	    {
	        return session.createCriteria(User.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
	    	session.close();
	    }
	}

	public boolean existsByEmail(String email) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<User> users = (ArrayList<User>) getAllUsers();
		for(User user : users) {
			if(user.getPerson().getEmail().equals(email)) {
				if(session.get(User.class, user.getId()) != null) {
					return true;

				}
			}
		}
		tx1.commit();
		session.close();
		return false;
	}

	public boolean existsByUserName(String username) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<User> users = (ArrayList<User>) getAllUsers();
		for(User user : users) {
			if(user.getUserName().equals(username)) {
				if(session.get(User.class, user.getId()) != null)
				return true;
			}
		}
		tx1.commit();
		session.close();
		return false;
	}

	public User getUserById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<User> users = (ArrayList<User>) getAllUsers();
		for(User user : users) {
			if(user.getId() == id) {
				session.get(User.class, id);
				return user;
			}
		}
		tx1.commit();
		session.close();
		return null;
	}

	public boolean existsByPhone(String phoneNumber) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		ArrayList<User> users = (ArrayList<User>) getAllUsers();
		for(User user : users) {
			if(user.getPerson().getPhoneNumber().equals(phoneNumber)) {
				if(session.get(User.class, user.getId()) != null)
				return true;
			}
		}
		tx1.commit();
		session.close();
		return false;
	}

	public List<UserRoleEntity> getAllUserRoles() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		try {
	        return session.createCriteria(UserRoleEntity.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    } finally {
	    	tx1.commit();
	    	session.close();
	    }
	}
	
	public User signin(String username, String password) {
		ArrayList<User> users = (ArrayList<User>) getAllUsers();
		for(User user : users) {
			if(user.getUserName().equals(username) && user.getPassword().equals(hashPassword(password.toCharArray()))) {
				return user;
			}
		}
		return null;
	}
	
	public UserRole authByRole(UserRole role) {
		ArrayList<User> users = (ArrayList<User>) getAllUsers();
		for(User user : users) {
			if(user.getRole().getRole().equals(role)) {
				return user.getRole().getRole();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		UserRoleEntity role = new UserRoleEntity();
		role.setRole(UserRole.CHIEF);
		
		DepartmentEntity department = new DepartmentEntity();
		department.setDepartment(Department.CHIEF_DEPARTMENT);
		
		Person person = new Person();
		person.setDateOfBirth(LocalDate.now());
		person.setEmail("antonina1@ukr.net");
		person.setFullName("Antonina");
		person.setPhoneNumber("0978455666");
		
		Employee employee = new Employee();
		employee.setDepartment(department);
		employee.setPerson(person);
		
		User user = new User();
		user.setPassword("12345");
		user.setUserName("antonina");
		user.setRole(role);
		user.setPerson(employee.getPerson());
		user.setEmployee(employee);
		
		UserService service = new UserService();
		//service.signup(user);
		System.out.println(service.signin("antonina", "12345"));
		
	}
	
	public static String hashPassword(final char[] password) {
		try {
			byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
											.generateSecret(new PBEKeySpec(password, "SaltPasswordHere".getBytes(), 33/*iteration*/, 256/*key length*/))
											.getEncoded();
			return Base64.getEncoder().encodeToString(result);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static void insertAllUserRoles() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		
		UserRoleEntity role1 = new UserRoleEntity();
		role1.setRole(UserRole.ADMIN);
		UserRoleEntity role2 = new UserRoleEntity();
		role2.setRole(UserRole.CHIEF);
		UserRoleEntity role3 = new UserRoleEntity();
		role3.setRole(UserRole.EQUIPMENT_MANADER);
		UserRoleEntity role4 = new UserRoleEntity();
		role4.setRole(UserRole.HR_MANAGER);
		UserRoleEntity role5 = new UserRoleEntity();
		role5.setRole(UserRole.SHIPMENT_MANAGER);
		UserRoleEntity role6 = new UserRoleEntity();
		role6.setRole(UserRole.STORAGE_SUPERVIZOR);
		
		session.save(role1);
		session.save(role2);
		session.save(role3);
		session.save(role4);
		session.save(role5);
		session.save(role6);
		
		tx1.commit();
		session.close();
	}
}
