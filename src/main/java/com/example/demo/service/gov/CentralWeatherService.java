package com.example.demo.service.gov;

import java.util.concurrent.ForkJoinPool;

import org.springframework.stereotype.Service;

@Service
public class CentralWeatherService {
	private ForkJoinPool fjp = null;
	
	public void getWeatherForecast() {
		synchronized (this) {
			if(null == fjp) this.fjp = ForkJoinPool.commonPool();
		}
	}
}
