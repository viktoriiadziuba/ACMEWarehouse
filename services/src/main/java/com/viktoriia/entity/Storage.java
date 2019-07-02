package com.viktoriia.entity;

import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "storage")
public class Storage extends AbstractEntity {
	
	@Column(nullable = false)
	private String address;
	
	@OneToMany(mappedBy = "storage", orphanRemoval = true)
	private List<GoodsEntity> goods;

	public Storage() {
		
	}
	
	public void addGoods(GoodsEntity goodsEntity) {
		goods.add(goodsEntity);
	}

	public void removeGoods(GoodsEntity goodsEntity) {
		goods.remove(goodsEntity);
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<GoodsEntity> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsEntity> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Storage [address=" + address + "]";
	}
	
}