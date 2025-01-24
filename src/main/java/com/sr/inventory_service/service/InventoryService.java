package com.sr.inventory_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.inventory_service.dao.InventoryRepo;
import com.sr.inventory_service.entity.Inventory;


@Service
public class InventoryService {
	
	@Autowired
	InventoryRepo inventoryRepository;
	
	public List<Inventory> getAllInventorys(){
		
		return inventoryRepository.findAll();
		
	}
	
	public Optional<Inventory> getAInventory(int inventoryId) {
		return inventoryRepository.findById(inventoryId);
	}
	
	public Inventory addInventory(Inventory newInventory) {
		return inventoryRepository.saveAndFlush(newInventory);
	}
	
	public Inventory updateInventory(Inventory editInventory) {
		return inventoryRepository.save(editInventory);
	}
	
	public void deleteInventory(int inventoryId) {
		inventoryRepository.deleteById(inventoryId);
	}

}
