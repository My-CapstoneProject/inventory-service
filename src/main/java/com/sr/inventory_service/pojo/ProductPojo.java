package com.sr.inventory_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPojo {
	
	private int productId;
	private String productName;
	private int productPrice;
	private String productBrand;
	private int userId;
	

}
