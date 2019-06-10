package com.viktoriia.model;

import java.util.List;

import com.viktoriia.entity.GoodsEntity;

public interface GoodsService {

	void add(GoodsEntity goods);

	void update(GoodsEntity goods, String[] params);

	void delete(GoodsEntity goods);

	List<GoodsEntity> getAll();

}
