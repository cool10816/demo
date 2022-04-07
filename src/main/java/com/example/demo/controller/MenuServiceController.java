package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ProductCategory;
import com.example.demo.model.ProductDetial;
import com.example.demo.service.MenuService;
import com.example.demo.util.ConvertUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path = "/Product")
public class MenuServiceController {

	@Autowired
	MenuService menuService;

	@RequestMapping(value = "/getMenu")
	public String getMenu(Model model, @PageableDefault(value = 5) Pageable pageable, @ModelAttribute("productCategory") ProductCategory productCategory) {
		log.debug("{}", productCategory);
		Sort sort = Sort.by(Sort.Order.asc("type"), Sort.Order.desc("createdDate"), Sort.Order.asc("name"));
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		Page<ProductCategory> productCategories = menuService.getAllProductCategory(productCategory, pageable);

		model.addAttribute("page", productCategories.getNumber());
		model.addAttribute("pages", null);
		model.addAttribute("productCategory", productCategory);
		model.addAttribute("productCategories", productCategories.getContent());
		return "Menu";
	}

	@RequestMapping(value = "/getTypeMenu")
	public String getTypeMenu(@RequestParam(value = "type", required = true) String type, Model model) {
//		List<ProductDetial> list = menuService.getProductByType(type);
//		model.addAttribute("ProductDetailList", list);
		return "typeMenu";
	}

	@RequestMapping("/")
	public String getAllProduct(Model model, @PageableDefault(value = 5) Pageable pageable, @ModelAttribute("productCategory") ProductCategory productCategory) {
		Sort sort = Sort.by(Sort.Order.asc("categoryType"), Sort.Order.desc("createdDate"), Sort.Order.asc("name"));
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		Page<ProductDetial> detials = menuService.getProductDetialByType(productCategory, pageable);
		
		List<ProductCategory> productCategories = menuService.getCategories();
		List<Object[]> pages = ConvertUtil.test(detials.getNumber() + 1, detials.getTotalPages());
		model.addAttribute("page", detials.getNumber());
		model.addAttribute("pages", pages);
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("productCategory", productCategory);
		model.addAttribute("productDetials", detials.getContent());
		return "Menu2";
	}
}
