package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.AbstractEntity;
import com.viktoriia.entity.GoodsEntity;

public interface WithGoodsService<T extends AbstractEntity> {
	
	T getById(int id);
	
	List<T> getAll();
	
	List<GoodsEntity> getWithGoods(int id);

}
