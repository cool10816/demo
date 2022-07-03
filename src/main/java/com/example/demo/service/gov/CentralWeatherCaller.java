package com.example.demo.service.gov;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.weather.WeatherConfiguration;
import com.example.demo.model.gov.CentralWeatherRequestEntity;
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

	public void callCentralWeatherService(CentralWeatherRequestEntity reqEntity) {
		synchronized (this) {
			if (null == this.httpClent) {
				this.httpClent = new OkHttpClient.Builder().callTimeout(5000, TimeUnit.MILLISECONDS)
						.readTimeout(3000, TimeUnit.MILLISECONDS).build();
			}
		}

		Response rsp = null;

		try {
//			RequestBody body = RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsBytes(reqEntity));

			Request req = new Request.Builder().url("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-F17DD75A-4167-4A04-A461-5A97AD5D3F1C&limit=10&offset=0&format=JSON&locationName=宜蘭縣&elementName=")
					.addHeader(weatherConfiguration.getAuthorizationKey(), weatherConfiguration.getAuthorizationValue())
					.method("GET", null)
					.build();

			rsp = httpClent.newCall(req).execute();

			if (rsp.isSuccessful()) {
				log.info(rsp.body().string());
			}
			log.error("<<< {} >>> Unable to retrieve Central Weather Service for parameters [{}]: {}.",
					this.getClass().getSimpleName());

		} catch (Exception e) {
			log.error("<<< {} >>> Unexpected exception occurred while retrieving Cental Weather Service for parameters [{}]: {}.", this.getClass().getSimpleName(), ExceptionStackIllustrtor.illusustrateExceptionStack(e));
		} finally {
			if (null != rsp) {
				rsp.close();
			}
		}
	}
}
