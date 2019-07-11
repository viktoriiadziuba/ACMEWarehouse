package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.GoodsTypeEntity;

public interface GoodsService {
	
	GoodsEntity getGoodsById(int id);

	List<GoodsEntity> getAllGoods();

	List<GoodsTypeEntity> getAllGoodsTypes();
}
