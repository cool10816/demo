package com.example.demo.model.gov;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Time {

	@JsonProperty("startTime")
	private String startTime;

	@JsonProperty("endTime")
	private String endTime;
}
