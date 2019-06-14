package com.viktoriia.model.impl;

import java.util.ArrayList;

import java.util.List;

import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.model.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService {

	private List<EquipmentEntity> equipmentList = new ArrayList<EquipmentEntity>();

	public void add(EquipmentEntity equipment) {
		equipmentList.add(equipment);
	}

	public void update(EquipmentEntity equipment) {
		equipmentList.add(equipment);

	}

	public void delete(EquipmentEntity equipment) {
		equipmentList.remove(equipment);

	}

	public List<EquipmentEntity> getAll() {
		return equipmentList;
	}

}
