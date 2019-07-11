package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.User;
import com.viktoriia.entity.UserRoleEntity;

public interface UserService {

	void add(User user);
	
	User getUserById(int id);

	List<User> getAll();

	User findByUserName(String username);

	boolean existsByEmail(String email);

	boolean existsByUserName(String userName);
	
	boolean existsByPhone(String phoneNumber);

	String signin(String username, String password, String authToken);
	
	List<UserRoleEntity> getAllUserRoles();

}
