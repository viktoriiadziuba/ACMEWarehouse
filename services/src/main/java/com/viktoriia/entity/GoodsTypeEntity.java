package com.viktoriia.entity;

import java.io.Serializable; 

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.viktoriia.entity.enums.GoodsType;

@Entity
@Table(name = "goods_type")
public class GoodsTypeEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -2658311211677842839L;

	@Enumerated(EnumType.STRING)
	private GoodsType type;
	

	public GoodsType getType() {
		return type;
	}

	public void setType(GoodsType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("	[GoodsType: "
				+ "id=%d "
				+ "type=%s]", 
				getId(), getType()); 
	}
	
	
	
}
