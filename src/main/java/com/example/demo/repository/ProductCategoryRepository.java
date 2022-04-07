package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductCategory;

@Repository
public interface ProductCategoryRepository extends BaseRepository<ProductCategory>, JpaRepository<ProductCategory, Long> {

	ProductCategory findByType(String type);
	
	List<ProductCategory> findByEnable(String enable);
}
