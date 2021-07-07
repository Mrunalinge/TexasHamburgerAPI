package com.texas.dto;

import java.io.Serializable;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
//@Accessors(fluent=true, chain=true)
public class LocationsDTO implements Serializable {
	
    private long id;
	@JsonProperty(value="city")
	private String city;
	@JsonProperty(value="streetAddress")
	private String streetAddress;
	@JsonProperty(value="zipCode")
	private String zipCode;
	@JsonProperty(value="startTime")
	private Time startTime;
    @JsonProperty(value="endTime")
	private Time endTime;
	
	private CustomerOrderDTO order;
	
	private MenuDTO menu;
	
}
