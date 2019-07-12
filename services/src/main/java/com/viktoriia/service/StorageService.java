package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Storage;

public interface StorageService {

	void add(Storage storage);

	void delete(int id);

	Storage getStorageById(int id);
	
	List<Storage> getAllStorages();

	List<GoodsEntity> getStorageGoods(int storageId);
	
	GoodsEntity addStorageWithGoods(GoodsEntity entity);
	
	void deleteStorageWithGoods(int storageId);
	
}
