package com.viktoriia.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.viktoriia.model.Equipment;
//Map instead of DB
public class EquipmentDAO {

	static HashMap<String, Equipment> equipmentMap = new HashMap<String, Equipment>();
	
	public List<Equipment> getAllEquipment() {
		List<Equipment> equipmentList = new ArrayList<Equipment>(equipmentMap.values());
		return equipmentList;
	}
	
	public Equipment createEquipment(Equipment equipment) {
		equipmentMap.put(equipment.getId(), equipment);
		return equipmentMap.get(equipment.getId());		
	}
	
	public Equipment getEquipmentById(String id) {
		return equipmentMap.get(id);
	}
	
	public void deleteEquipment(String id) {
		equipmentMap.remove(id);
	}
	
	public Equipment updateEquipment(Equipment equipment) {
		if (equipmentMap.containsKey(equipment.getId())){
			equipmentMap.put(equipment.getId(), equipment);
		} else {
			//Thre isn't such equipment
		}
		return equipment;
	} 
}
