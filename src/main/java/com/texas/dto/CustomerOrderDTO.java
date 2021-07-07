package com.texas.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.texas.enums.OrderStatus;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(fluent=true, chain=true)
public class CustomerOrderDTO implements Serializable {

	private long id;
	@JsonProperty(value="dishes")
	private List<DishDTO> dishes;
	
    private UserDTO user;
	
	private OrderStatus orderStatus;
	
	private LocationsDTO location;
	@JsonProperty(value="reviews")
	private String reviews;
	
	private BillingDTO bill;
	@JsonProperty(value="createdAt")
	private Timestamp createdAt;
}
