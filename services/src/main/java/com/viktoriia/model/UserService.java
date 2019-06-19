package com.viktoriia.model;

import java.util.List;

import com.viktoriia.entity.User;

public interface UserService {

	void add(User user);

	void update(User user);

	List<User> getAll();

	User findByUserName(String username);

	boolean existsByEmail(String email);

	boolean existsByUserName(String userName);

	String signin(String username, String password);

}
