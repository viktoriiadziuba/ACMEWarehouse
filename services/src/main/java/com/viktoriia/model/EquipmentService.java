package com.viktoriia.model;

import java.util.List;

import com.viktoriia.entity.EquipmentEntity;

public interface EquipmentService {

	void add(EquipmentEntity equipmentEntity);

	void update(EquipmentEntity equipmentEntity);

	void delete(EquipmentEntity equipmentEntity);

	List<EquipmentEntity> getAll();

}
