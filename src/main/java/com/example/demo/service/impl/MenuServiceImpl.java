package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductCategory;
import com.example.demo.model.ProductDetial;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private ProductDetailRepository productDetailRepository;

	@Override
	public Page<ProductCategory> getAllProductCategory(ProductCategory productCategory, Pageable pageable) {
		Specification<ProductCategory> sp = new Specification<ProductCategory>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ProductCategory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isBlank(productCategory.getType())) {
					list.add(criteriaBuilder.equal(root.get("type").as(String.class), productCategory.getType()));
				}

				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}

		};
		return productCategoryRepository.findAll(sp, pageable);
	}

	@Override
	public Page<ProductDetial> getProductDetialByType(ProductCategory productCategory, Pageable pageable) {
		Specification<ProductDetial> sp = new Specification<ProductDetial>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ProductDetial> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				list.add(criteriaBuilder.equal(root.get("display").as(String.class), "1"));
				
				String type = "1";
				if (!StringUtils.isBlank(productCategory.getType())) {
					type = productCategory.getType();
				} else {
					productCategory.setType(type);
				}
				
				list.add(criteriaBuilder.equal(root.get("categoryType").as(String.class), type));

				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}

		};
		return productDetailRepository.findAll(sp, pageable);
	}
	
	@Override
	public List<ProductCategory> getCategories() {
		return productCategoryRepository.findByEnable("1");
	}
}
