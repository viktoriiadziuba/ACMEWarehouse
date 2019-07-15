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

import com.viktoriia.entity.User;
import com.viktoriia.entity.UserRoleEntity;
import com.viktoriia.entity.enums.UserRole;
import com.viktoriia.service.AbstractService;
import com.viktoriia.utils.HibernateSessionFactoryUtil;

@Stateless
public class UserService extends AbstractService {
	
	private EntityManager manager;
	
	public UserService() {
		
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
	
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean existsByUserName(String userName) {
		
		return false;
	}

	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existsByPhone(String phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public String signin(String username, String password, String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserRoleEntity> getAllUserRoles() {
		// TODO Auto-generated method stub
		return null;
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
