package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductDetial;

@Repository
public interface ProductDetailRepository extends BaseRepository<ProductDetial>, JpaRepository<ProductDetial, Long> {

	List<ProductDetial> findByCategoryType(String category);

	List<ProductDetial> findByCategoryTypeAndDisplay(String category, String display);
}
