package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.AbstractEntity;

public interface Service<T extends AbstractEntity> {
	
	public T add(T entity);
	
	public void delete(int id);
	
	public List<T> getAll();
	
	public T getById(int id);

}
