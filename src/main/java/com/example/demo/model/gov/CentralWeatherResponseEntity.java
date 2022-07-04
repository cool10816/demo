package com.example.demo.model.gov;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CentralWeatherResponseEntity {
	@JsonProperty("success")
	private String success;

	@JsonProperty("records")
	private Records records;

}
