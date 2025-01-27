package com.sr.inventory_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sr.inventory_service.pojo.ProductPojo;
import com.sr.inventory_service.pojo.StorePojo;

@FeignClient(name = "product-service", url = "http://localhost:8001/api/products")
public interface ProductClient {
	
	@GetMapping("/{productId}")
    ProductPojo getProductById(@PathVariable int productId);

}
