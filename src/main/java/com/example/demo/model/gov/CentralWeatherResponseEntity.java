package com.example.demo.model.gov;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CentralWeatherResponseEntity {
	@JsonProperty("success")
	private String success;

	@JsonProperty("records")
	private Record record;

}
