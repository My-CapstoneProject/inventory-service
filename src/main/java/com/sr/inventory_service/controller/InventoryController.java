package com.sr.inventory_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.inventory_service.dao.InventoryRepo;
import com.sr.inventory_service.entity.Inventory;
import com.sr.inventory_service.pojo.InventoryPojo;
import com.sr.inventory_service.pojo.OrderProductPojo;
import com.sr.inventory_service.pojo.ProductPojo;
import com.sr.inventory_service.pojo.StorePojo;
import com.sr.inventory_service.service.InventoryService;
import com.sr.inventory_service.service.ProductClient;
import com.sr.inventory_service.service.StoreClient;

@RestController
@RequestMapping("/api")
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
    InventoryRepo inventoryRepository;
	
	@Autowired
	StoreClient storeClient;
	
	@Autowired
	ProductClient productClient;
	
	@GetMapping("/inventorys")
	public ResponseEntity<List<Inventory>> getAllInventorys(){
		return new ResponseEntity<List<Inventory>>(inventoryService.getAllInventorys(),HttpStatus.OK);
	}
//	@GetMapping("/inventorys/{inid}")
//	public ResponseEntity<Optional<Inventory>> getAInventory(@PathVariable int inid){
//		return new ResponseEntity<Optional<Inventory>>(inventoryService.getAInventory(inid),HttpStatus.OK);
//	}
	@PostMapping("/inventorys")
	public ResponseEntity<Inventory> addInventory(@RequestBody Inventory newInventory){
		return new ResponseEntity<Inventory>(inventoryService.addInventory(newInventory),HttpStatus.OK);
	}
	@PutMapping("/inventorys")
	public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory editInventory){
		return new ResponseEntity<Inventory>(inventoryService.updateInventory(editInventory),HttpStatus.OK);
	}
	
	@DeleteMapping("/inventorys/{inid}")
	public ResponseEntity<Void> deleteInventory(@PathVariable int inid){
		inventoryService.deleteInventory(inid);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/inventorys/{inid}")
	public ResponseEntity<InventoryPojo> getAInventory(@PathVariable int inid) {
	    Optional<Inventory> inOptional = inventoryService.getAInventory(inid);
	    
	    if (inOptional.isPresent()) {
	        Inventory inventory = inOptional.get();  // Unwrap the Optional
	        
	        InventoryPojo inPojo = new InventoryPojo();
	        
	        // Now use Feign client to fetch department by departmentId
	        StorePojo store = storeClient.getStoreById(inventory.getInStoreId());
	        ProductPojo product = productClient.getProductById(inventory.getInProductId());
	        
	        
	        // Set the department to the employee
	        inPojo.setInventoryId(inventory.getInventoryId());
	        inPojo.setProductQuantity(inventory.getProductQuantity());
	        inPojo.setInStoreId(inventory.getInStoreId());
	        inPojo.setInProductId(inventory.getInProductId());
	        inPojo.setStorePojo(store);
	        inPojo.setProductPojo(product);
	        
	        return new ResponseEntity<>(inPojo, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Employee not found
	    }
	}

	@PostMapping("/update-sales")
    public ResponseEntity<String> updateSales(@RequestBody List<OrderProductPojo> orderProducts) {
        try {
            for (OrderProductPojo orderProduct : orderProducts) {
                // Find inventory record by product ID
                Inventory inventory = inventoryRepository.findByInProductId(orderProduct.getProductId())
                        .orElseThrow(() -> new RuntimeException(
                                "Product with ID " + orderProduct.getProductId() + " not found in inventory"));

                // Deduct the sales quantity from the current product quantity
                int updatedQuantity = inventory.getProductQuantity() - orderProduct.getSalesQuantity();
                if (updatedQuantity < 0) {
                    return ResponseEntity.badRequest().body("Not enough stock for product ID: " + orderProduct.getProductId());
                }

                inventory.setProductQuantity(updatedQuantity);

                // Save updated inventory
                inventoryRepository.save(inventory);
            }

            return ResponseEntity.ok("Inventory updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to update inventory.");
        }
    }
}