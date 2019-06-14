package com.viktoriia.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.entity.Storage;

@XmlRootElement
public class StorageService {

	public static List<Storage> storages = new ArrayList<>();

	public void add(Storage storage) {
		storages.add(storage);
	}

	@XmlElement(name = "listStorages")
	public List<Storage> getAll() {
		return storages;
	}
}
