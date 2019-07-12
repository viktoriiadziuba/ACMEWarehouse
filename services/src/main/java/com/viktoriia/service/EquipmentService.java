package com.viktoriia.service;

import java.util.List;

import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.EquipmentTypeEntity;

public interface EquipmentService {

	void add(EquipmentEntity equipmentEntity);
	
	void delete(int id);
	
	EquipmentEntity getEquipmentById(int id);

	List<EquipmentEntity> getAllEquipment();
	
	List<EquipmentTypeEntity> getAllEquipmentTypes();

}
