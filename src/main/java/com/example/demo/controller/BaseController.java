package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.gov.CentralWeatherResponseEntity;
import com.example.demo.service.gov.CentralWeatherCaller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BaseController {

	@Autowired
	private CentralWeatherCaller centralWeatherCaller;

	@RequestMapping("/")
	public String goToHome() {
		CentralWeatherResponseEntity entity = centralWeatherCaller.callCentralWeatherService(null);
		log.info(entity.toString());
		return "Home2";
	}

}
