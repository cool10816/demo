package com.example.demo.model.gov;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Parameter {

	@JsonProperty("parameterName")
	private String parameterName;

	@JsonProperty("parameterValue")
	private String parameterValue;
	
	@JsonProperty("parameterUnit")
	private String parameterUnit;
}
