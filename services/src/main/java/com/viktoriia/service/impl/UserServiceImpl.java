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
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viktoriia.entity.DepartmentEntity;
import com.viktoriia.entity.Employee;
import com.viktoriia.entity.Person;
import com.viktoriia.entity.User;
import com.viktoriia.entity.UserRoleEntity;
import com.viktoriia.entity.enums.Department;
import com.viktoriia.entity.enums.UserRole;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

@Stateless
public class UserServiceImpl {
	
	private EntityManager manager;
	
	public UserServiceImpl() {
		
	}

	public void add(User user) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(user.getEmployee());
		session.save(user.getEmployee().getPerson());
		session.save(user.getEmployee().getDepartment());
		session.save(user.getRole());
		session.save(user);
				
		tx1.commit();
		session.close();		
	}

	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	public List<User> getAllUsers(){  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
	    try
	    {
	        return session.createCriteria(User.class).list();
	    } catch (Exception e) {
	        return new ArrayList<>();
	    }
	}
	
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean existsByUserName(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	public String signin(String username, String password) {
		TypedQuery<String> loginQuery = manager.createNamedQuery("User.login", String.class);
		loginQuery.setParameter("username", username);
		loginQuery.setParameter("password", hashPassword(password.toCharArray()));
		
		try {
			String role = loginQuery.getSingleResult();
			return role;
		} catch (Exception e) {
			return null;
		}
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
	
	public static void main(String[] args) {
		DepartmentEntity dep = new DepartmentEntity();
		dep.setDepartment(Department.EQUIPMENT_DEPARTMENT);
		
		Person person = new Person();
		person.setDateOfBirth(LocalDate.now());
		person.setEmail("vova@email.com");
		person.setFullName("VOVA");
		person.setPhoneNumber("+380952066363");
		
		Employee empl = new Employee();
		empl.setDepartment(dep);
		empl.setPerson(person);
		
		UserRoleEntity role = new UserRoleEntity();
		role.setRole(UserRole.EQUIPMENT_MANADER);
		
		User user = new User();
		user.setEmployee(empl);
		user.setRole(role);
		user.setPassword("1234");
		user.setPerson(person);
		user.setUserName("vova.V");
		
		UserServiceImpl service = new UserServiceImpl();
		//service.add(user);
		System.out.println(service.getAllUsers());
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
