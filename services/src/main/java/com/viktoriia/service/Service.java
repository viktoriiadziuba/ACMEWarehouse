package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.AbstractEntity;

public interface Service<T extends AbstractEntity> {
	
	void add(T entity);
	
	void delete(int id);
	
	List<T> getAll();
	
	T getById(int id);

}
