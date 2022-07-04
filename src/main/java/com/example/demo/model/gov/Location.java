package com.example.demo.model.gov;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Location {

	@JsonProperty("locationName")
	private String locationName;

	@JsonProperty("weatherElement")
	private List<WeatherElement> weatherElements;
}
