package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.RedisLettuceConnector;

@Controller
public class BaseController {

	@Autowired
	RedisLettuceConnector redisLettuceConnector;
	
	@RequestMapping("/")
	public String goToHome() {
		System.out.println(redisLettuceConnector.get("a"));
		return "Home2";
	}
	
	
}
