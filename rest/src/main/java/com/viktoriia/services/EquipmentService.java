package com.viktoriia.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.viktoriia.entity.EquipmentEntity;

@XmlRootElement
public class EquipmentService {
	
	public static List<EquipmentEntity> equipmentList = new ArrayList<EquipmentEntity>();

	public void add(EquipmentEntity equipment) {
		equipmentList.add(equipment);
	}

	@XmlElement(name = "listEquipment")
	public List<EquipmentEntity> getAll() {
		return equipmentList;
	}

}
