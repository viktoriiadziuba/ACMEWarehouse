package com.viktoriia.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.viktoriia.entity.enums.UserRole;

@Entity
@Table(name = "user_role")
public class UserRoleEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 621817461360692909L;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return String.format("	[UserRole: "
				+ "id=%d "
				+ "role=%s]", 
				getId(), getRole());
	}	
	
}
