package com.example.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisLettuceConnector {
	private RedisAdvancedClusterCommands<String, String> redisAdvancedClusterCommands = null;
	private RedisCommands<String, String> redisCommands = null;

//	@Bean
//	public RedisAdvancedClusterCommands<String, String> initalRedisConnctor() {
//		if (null == this.redisAdvancedClusterCommands) {
//			RedisClusterClient redisClient = RedisClusterClient.create("redis://asdf1234@localhost:6379");
//			StatefulRedisClusterConnection<String, String> connection = redisClient.connect();
//			this.redisAdvancedClusterCommands = connection.sync();
//		}
//		return redisAdvancedClusterCommands;
//	}

	@Bean
	public RedisCommands<String, String> inital() {
		if (null == redisCommands) {
			RedisClient redisClient = RedisClient.create("redis://asdf1234@localhost:6379");
			StatefulRedisConnection<String, String> connection = redisClient.connect();
			this.redisCommands = connection.sync();
		}
		return redisCommands;
	}

	public String get(String key) {
		try {
			return null == key ? null : this.redisCommands.get(key);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

//	public String remove(String key) {
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
}
