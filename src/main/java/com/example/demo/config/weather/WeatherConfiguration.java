package com.example.demo.config.weather;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "gov.weather")
public class WeatherConfiguration {
	private String authorizationKey;
	private String authorizationValue;
	private String url;
}
