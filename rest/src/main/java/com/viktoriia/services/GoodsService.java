package com.viktoriia.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.entity.GoodsEntity;

@XmlRootElement
public class GoodsService {
	
	public static List<GoodsEntity> goodsList = new ArrayList<GoodsEntity>();

	public void add(GoodsEntity goods) {
		goodsList.add(goods);

	}
	
	@XmlElement(name = "listGoods")
	public List<GoodsEntity> getAll() {
		return goodsList;
	}

}
