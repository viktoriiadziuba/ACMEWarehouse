package com.viktoriia.model;

import java.util.List;

import com.viktoriia.entity.Storage;

public interface StorageService {

	void add(Storage storage);

	void update(Storage storage);

	void delete(Storage storage);

	List<Storage> getAll();

}
