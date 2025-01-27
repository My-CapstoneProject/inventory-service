package com.sr.inventory_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sr.inventory_service.pojo.StorePojo;

@FeignClient(name = "stores-service", url = "http://localhost:7001/api")  // URL of the Department Service
public interface StoreClient {

    // Endpoint to fetch department details by department ID
    @GetMapping("/stores/b/{sid}")
    StorePojo getStoreById(@PathVariable int sid);
}

