package com.viktoriia.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.viktoriia.entity.Storage;
import com.viktoriia.model.StorageService;

public class StorageServiceImpl implements StorageService {

	private List<Storage> storages = new ArrayList<Storage>();

	public void add(Storage employee) {
		storages.add(employee);
	}

	public void update(Storage storage) {
		storages.add(storage);

	}

	public void delete(Storage storage) {
		storages.remove(storage);

	}

	public List<Storage> getAll() {
		return storages;
	}

}

