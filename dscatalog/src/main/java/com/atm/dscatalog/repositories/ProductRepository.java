package com.atm.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atm.dscatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}

