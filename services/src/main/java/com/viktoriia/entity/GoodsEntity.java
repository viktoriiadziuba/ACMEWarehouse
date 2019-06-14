package com.viktoriia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.entity.enums.GoodsType;

@XmlRootElement
@Entity
@Table(name = "goods")
public class GoodsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Enumerated(EnumType.STRING)
	private GoodsType type;
	
	@Column(columnDefinition = "TEXT")
	private String description;

	public GoodsEntity() {
		
	}

	public String getId() {
		return id;
	}

	public GoodsType getType() {
		return type;
	}

	public void setType(GoodsType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
