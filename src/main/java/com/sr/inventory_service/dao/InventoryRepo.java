package com.sr.inventory_service.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sr.inventory_service.entity.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
	Optional<Inventory> findByInProductId(int inProductId);

}
