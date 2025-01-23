package com.sr.inventory_service.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="inventory_details")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="inventory_id")
	private int inventoryId;
	
	@Column(name="prod_quantity")
	private int productQuantity;
	
	@Column(name="in_store_id")
	private int inStoreId;
	
	@Column(name="in_product_id")
	private int inProductId;
	
	
	

}
