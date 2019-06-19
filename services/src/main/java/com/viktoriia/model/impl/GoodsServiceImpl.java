package com.viktoriia.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.model.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	private List<GoodsEntity> goodsList = new ArrayList<GoodsEntity>();

	public void add(GoodsEntity goods) {
		goodsList.add(goods);

	}

	public void update(GoodsEntity goods) {
		goodsList.add(goods);
	}

	public void delete(GoodsEntity goods) {
		goodsList.remove(goods);

	}

	public List<GoodsEntity> getAll() {
		return goodsList;
	}

}