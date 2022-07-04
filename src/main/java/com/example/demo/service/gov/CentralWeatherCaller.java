package com.example.demo.service.gov;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.weather.WeatherConfiguration;
import com.example.demo.model.gov.CentralWeatherRequestEntity;
import com.example.demo.model.gov.CentralWeatherResponseEntity;
import com.example.demo.util.ExceptionStackIllustrtor;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@Service
public class CentralWeatherCaller {
	private OkHttpClient httpClent = null;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WeatherConfiguration weatherConfiguration;

	public CentralWeatherResponseEntity callCentralWeatherService(CentralWeatherRequestEntity reqEntity) {
		synchronized (this) {
			if (null == this.httpClent) {
				this.httpClent = new OkHttpClient.Builder().callTimeout(5000, TimeUnit.MILLISECONDS)
						.readTimeout(3000, TimeUnit.MILLISECONDS).build();
			}
		}

		Response rsp = null;

		try {
//			RequestBody body = RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsBytes(reqEntity));

			Request req = new Request.Builder().url("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?locationName=宜蘭縣,花蓮縣")
					.addHeader(weatherConfiguration.getAuthorizationKey(), weatherConfiguration.getAuthorizationValue())
					.method("GET", null)
					.build();

			rsp = httpClent.newCall(req).execute();

			if (rsp.isSuccessful()) {
				String value = rsp.body().string();
				return objectMapper.readValue(value, CentralWeatherResponseEntity.class);
			}
			log.error("<<< {} >>> Unable to retrieve Central Weather Service for parameters [{}]: {}.",
					this.getClass().getSimpleName());
			return null;

		} catch (Exception e) {
			log.error("<<< {} >>> Unexpected exception occurred while retrieving Cental Weather Service for parameters [{}]: {}.", this.getClass().getSimpleName(), ExceptionStackIllustrtor.illusustrateExceptionStack(e));
			return null;
		} finally {
			if (null != rsp) {
				rsp.close();
			}
		}
	}
}
