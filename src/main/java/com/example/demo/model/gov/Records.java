package com.example.demo.model.gov;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Records {

	@JsonProperty("datasetDescription")
	private String datasetDescription;

	@JsonProperty("location")
	private List<Location> locations;

}
