package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis.lettuce")
public class RedisConfiguration {
	private String nodes;
	private String username;
	private String password;
	private String keyPrefix;

	private long opeartiontimeoutms;
	private long keyexpirationms;
}
