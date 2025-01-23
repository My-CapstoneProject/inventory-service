package com.sr.inventory_service.pojo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryPojo {
	
private int inventoryId;
	
	private int productQuantity;
	
	
	private int inStoreId;
	
	
	private int inProductId;
	
	private StorePojo storePojo;

}
