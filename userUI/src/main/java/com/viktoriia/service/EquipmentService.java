package com.viktoriia.service;

import java.util.List;

import com.viktoriia.dao.EquipmentDAO;
import com.viktoriia.model.Equipment;

public class EquipmentService {

	EquipmentDAO equipmentDao = new EquipmentDAO();
	
	public List<Equipment> getAllEquipment() {
	List<Equipment> listEquipment = equipmentDao.getAllEquipment();
	return listEquipment;
	}
	
	public Equipment getEquipmentById(String id) {
		Equipment equipmentResponse = equipmentDao.getEquipmentById(id);
		return equipmentResponse;
	}
	
	public Equipment createEquipment(Equipment equipment) {
		Equipment equipmentResponse = equipmentDao.createEquipment(equipment);
		return equipmentResponse;
	}
	
	public void deleteEquipment(String id) {
		equipmentDao.deleteEquipment(id);
	}
	
	public Equipment updateEquipment(Equipment equipment) {
		Equipment equipmentResponse = equipmentDao.updateEquipment(equipment);
		return equipmentResponse;
	}
}
