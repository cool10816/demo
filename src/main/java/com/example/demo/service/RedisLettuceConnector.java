package com.example.demo.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.demo.config.RedisConfiguration;
import com.example.demo.util.ExceptionStackIllustrtor;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisLettuceConnector {

	private static final String REDIS_URI_SEPARATOR = "@";
	private static final String REDIS_URI_PREFIX = "redis://";
	// private static final String NODE_CONFIGURATION_SEPARATOR = ",";
	private static final String AUTHENTICA_INFORMATION_SEPARATOR = ":";

	@Autowired
	private RedisConfiguration redisConfiguration;

	private String strRedisKeyPrefix;
//	private RedisAdvancedClusterCommands<String, String> redisAdvancedClusterCommands = null;
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

//	@Bean
//	public RedisCommands<String, String> inital() {
//		if (null == redisCommands) {
//			StringBuilder uri = new StringBuilder(REDIS_URI_PREFIX)
//					.append(this.redisConfiguration.getUsername())
//					.append(AUTHENTICA_INFORMATION_SEPARATOR)
//					.append(this.redisConfiguration.getPassword())
//					.append(REDIS_URI_SEPARATOR)
//					.append(this.redisConfiguration.getNodes());
//
//			RedisClient redisClient = RedisClient.create(uri.toString());
//			StatefulRedisConnection<String, String> connection = redisClient.connect();
//			this.redisCommands = connection.sync();
//			this.redisCommands.setTimeout(Duration.ofMillis(this.redisConfiguration.getOpeartionTimeout_ms()));
//			
//			this.strRedisKeyPrefix = new StringBuilder(this.redisConfiguration.getKeyPrefix())
//					.append(AUTHENTICA_INFORMATION_SEPARATOR).toString();
//		}
//		return redisCommands;
//	}

	public long removeValue(String strKey) {
		try {
			if (null == strKey)
				return 0;
			return this.redisCommands.del(new StringBuilder(this.strRedisKeyPrefix).append(strKey).toString());
		} catch (Exception e) {
			this.recordRedisOperationError("DEL", e);
			return 0;
		}
	}

	public String getValue(String strKey) {
		try {
			if (null == strKey)
				return null;
			return this.redisCommands.get(new StringBuilder(this.strRedisKeyPrefix).append(strKey).toString());
		} catch (Exception e) {
			this.recordRedisOperationError("GET", e);
			return null;
		}
	}

	public boolean detectExistence(String strKey) {
		try {
			if (null == strKey)
				return false;
			return this.redisCommands.exists(new StringBuilder(this.strRedisKeyPrefix).append(strKey).toString()) > 0;
		} catch (Exception e) {
			this.recordRedisOperationError("EXISTS", e);
			return false;
		}
	}

	public boolean putValue(String strKey, String strValue, boolean isValueOverwritten) {
		if (null == strKey || null == strValue || (!isValueOverwritten && this.detectExistence(strKey)))
			return false;
		String strOperatingKey = new StringBuilder(this.strRedisKeyPrefix).append(strKey).toString();

		try {
			this.redisCommands.set(strOperatingKey, strValue);
			return this.redisCommands.expire(strOperatingKey, this.redisConfiguration.getKeyexpirationms());
		} catch (Exception e) {
			this.recordRedisOperationError("SET & EXPIRE", e);
			return false;
		}
	}

	private void recordRedisOperationError(String strCommandName, Exception ex) {
		log.error("<<< {} >>> Unexoected exception occurred while operating REDIS with command : {}.",
				this.getClass().getSimpleName(), strCommandName,
				ExceptionStackIllustrtor.illusustrateExceptionStack(ex));
	};
}
