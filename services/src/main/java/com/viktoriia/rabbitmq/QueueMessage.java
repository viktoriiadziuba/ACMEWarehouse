package com.viktoriia.rabbitmq;

import java.io.Serializable;

import com.viktoriia.entity.AbstractEntity;

public class QueueMessage implements Serializable {

	private static final long serialVersionUID = -2890687862821180912L;
	
	private Class<?> className;
	private CRUDOperation operation;
	private AbstractEntity entity;
	
	public Class<? extends AbstractEntity> getClassEntity() {
		return entity.getClass();
	}
	public void setClassEntity(Class<?> className) {
		this.className = className;
	}
	public CRUDOperation getOperation() {
		return operation;
	}
	public void setOperation(CRUDOperation operation) {
		this.operation = operation;
	}
	public AbstractEntity getEntity() {
		return entity;
	}
	public void setEntity(AbstractEntity entity) {
		this.entity = entity;
	}
	
	
	@Override
	public String toString() {
		return String.format("[QueueMessage: "
				+ "className=%s "
				+ "operation=%s "
				+ "%s]", 
				className, operation, entity);
	}
}
