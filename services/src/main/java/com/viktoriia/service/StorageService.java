package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.Storage;

public interface StorageService {
	
	Storage getStorageById(int id);
	
	List<Storage> getAllStorages();

	List<GoodsEntity> getStorageGoods(int storageId);
	
}
