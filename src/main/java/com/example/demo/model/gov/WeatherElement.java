package com.example.demo.model.gov;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WeatherElement {

	@JsonProperty("elementName")
	private String elementName;
	
	@JsonProperty("time")
	private List<Time> times;

}
