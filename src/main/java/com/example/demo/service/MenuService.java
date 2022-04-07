package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.ProductCategory;
import com.example.demo.model.ProductDetial;

public interface MenuService {

	Page<ProductCategory> getAllProductCategory(ProductCategory productCategory, Pageable pageable);

	Page<ProductDetial> getProductDetialByType(ProductCategory productCategory, Pageable pageable);
	
	List<ProductCategory> getCategories();
}
